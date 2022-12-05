package my.repo.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

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

    @Autowired
    private Jedis jedis;

    volatile Thread curThread;

    volatile int count;

    private String lockKey;

    private String lockValue;

    AtomicInteger atomicInteger = new AtomicInteger();

    protected volatile boolean isOpenExpirationRenewal = true;

    //构造函数
    public RedisDistributeLock(String lockKey) {
        this(lockKey,UUID.randomUUID().toString()+Thread.currentThread().getId());

    }

    public RedisDistributeLock(String lockKey, String lockValue) {
        super(lockKey);
        this.lockValue = lockValue;
    }

    
    /**
     * @author gengzhihao
     * @date 2022/12/5 17:05
     * @description 获得分布式锁。
    **/
    @Override
    public void lock() {
        //通过CAS实现可重入锁
        while (!atomicInteger.compareAndSet(0, 1)) {
            if (curThread == Thread.currentThread()) {
                count += 1;
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
                return;
            }
            Thread.yield();//让出cpu,但是还在竞争队列里
        }
        curThread = Thread.currentThread();
        count = 1;
    }


    /**
     * @author gengzhihao
     * @date 2022/12/5 17:06
     * @description 释放分布式锁。
    **/
    @Override
    public void unlock() {
        if (curThread != Thread.currentThread()) {
            return;
        }
        count -= 1;
        if (count == 0) {
            curThread = null;
            atomicInteger.set(0);
        }
        // 使用lua脚本进行原子删除操作
        String checkAndDelScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                "return redis.call('del', KEYS[1]) " +
                "else " +
                "return 0 " +
                "end";
        jedis.eval(checkAndDelScript, 1, lockKey, lockValue);
        isOpenExpirationRenewal = false;
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
                jedis.eval(checkAndExpireScript, 1, lockKey, lockValue, "30");

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
