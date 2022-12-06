package my.repo.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static my.repo.common.consts.RedisDistributeConsts.*;

/**
 * @author gengzhihao
 * @date 2022/12/5 14:17
 * @description 基于redis的分布式锁。实现了可重入锁、锁具有过期时间、锁的过期时间大于业务执行时间、释放锁操作具备原子性等特性
 **/
@Slf4j
public class RedisDistributeLock extends DistributeLock{

    private Jedis jedis;

    volatile Thread curThread;

    volatile int count;

    private String lockKey;

    private String lockValue;

    AtomicInteger atomicInteger = new AtomicInteger();

    protected volatile boolean isOpenExpirationRenewal = true;

    //构造函数
    public RedisDistributeLock(String lockKey,Jedis jedis) {
        this(lockKey,UUID.randomUUID().toString()+Thread.currentThread().getId(),jedis);

    }

    public RedisDistributeLock(String lockKey, String lockValue,Jedis jedis) {
        this.lockKey = lockKey;
        this.lockValue = lockValue;
        this.jedis = jedis;
    }

    
    /**
     * @author gengzhihao
     * @date 2022/12/5 17:05
     * @description 获得分布式锁。
    **/
    @Override
    public void lock() {
        log.info("分布式锁 lock开始 lockKey为 {}",lockKey);
        //通过CAS实现可重入锁
        //atomicInteger表示是否有过加锁行为，没有加锁过的为默认值0，不走while中的逻辑；加锁过的为1，走while中的逻辑。
        while (!atomicInteger.compareAndSet(0, 1)) {
            //情况1，如果来加锁的线程B，发现已经加锁了，并且不是最开始加锁的线程A，那么不增加可重入计数count。
            //情况2，如果来加锁的线程A，发现已经加锁了，并且是最开始加锁的线程A，那么增加可重入计数count，不对其持有的锁做改动
            if (curThread == Thread.currentThread()) {
                count += 1;
                return;
            }
            //情况1里的B线程，重新回去竞争锁
            Thread.yield();//让出cpu,但是还在竞争队列里
        }
        //情况2，没有加锁过的线程A，走这里的逻辑
        //指定当前线程curThread和可重入次数count
        curThread = Thread.currentThread();
        count = 1;
        //加锁，lock
        while (true) {
            String result = jedis.set(lockKey, lockValue, NOT_EXIST, SECONDS, 30);
            if (OK.equals(result)) {
                log.info("线程id: {} ，加锁成功!时间: {}", Thread.currentThread().getId() ,LocalTime.now());

                //开启定时刷新过期时间
                isOpenExpirationRenewal = true;
                scheduleExpirationRenewal();
                break;
            }
            log.info("线程id: {}, 获取锁失败，休眠10秒，时间: {}" ,Thread.currentThread().getId(),LocalTime.now());
            //休眠10秒
            sleepBySencond(10);
        }
        log.info("分布式锁 lock结束 lockKey为 {},lockValue为 {}",lockKey,lockValue);
    }


    /**
     * @author gengzhihao
     * @date 2022/12/5 17:06
     * @description 释放分布式锁。
    **/
    @Override
    public void unlock() {
        log.info("分布式锁 unlock开始 lockKey为 {}",lockKey);
        //不是当前线程，不能解锁
        if (curThread != Thread.currentThread()) {
            return;
        }
        //重入次数-1
        count -= 1;
        //如果重入次数为0，则应该进行解锁操作
        if (count == 0) {
            curThread = null;
            atomicInteger.set(0);
            // 使用lua脚本进行原子删除操作
            String checkAndDelScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "return redis.call('del', KEYS[1]) " +
                    "else " +
                    "return 0 " +
                    "end";
            jedis.eval(checkAndDelScript, 1, lockKey, lockValue);
            isOpenExpirationRenewal = false;
            log.info("分布式锁 unlock结束 lockKey为 {}, lockValue为 {}",lockKey,lockValue);
        }
    }



    /**
     * 开启定时刷新
     */
    protected void scheduleExpirationRenewal() {
        //todo 这里应该引入一个线程池，来控制线程的获取和归还
        Thread renewalThread = new Thread(new ExpirationRenewal());
        renewalThread.start();
    }

    /**
     * 刷新key的过期时间
     */
    private class ExpirationRenewal implements Runnable {
        @Override
        public void run() {
            while (isOpenExpirationRenewal) {
                log.info("执行延迟失效时间中...");

                String checkAndExpireScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                        "return redis.call('expire',KEYS[1],ARGV[2]) " +
                        "else " +
                        "return 0 end";
                long result = (long)jedis.eval(checkAndExpireScript, 1, lockKey, lockValue, "30");
                log.info("执行失效时间结果为 {}",result);
                if (1L == result){
                    log.info("分布式锁延时30秒, key为 {}",lockKey);
                }else if (0L == result){
                    log.info("分布式锁延时失败");
                }

                //休眠10秒
                sleepBySencond(10);
            }
        }
    }
    

    private void sleepBySencond(int sencond){
        try {
            Thread.sleep(sencond*1000);
        } catch (InterruptedException e) {
            log.warn("分布式锁: 睡眠异常 ",e);
        }
    }
}
