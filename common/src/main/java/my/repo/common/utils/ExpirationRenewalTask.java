package my.repo.common.utils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Slf4j
@Setter
//@Component(value = ConfigurableBeanFactory.)
public class ExpirationRenewalTask {

    private volatile boolean isOpenExpirationRenewal = true;

    /**
     * 开启定时刷新
     */
    @Async("threadPoolTaskExecutor")
    public void scheduleExpirationRenewal(Jedis jedis,String lockKey,String lockValue) {
        //todo 这里应该引入一个线程池，来控制线程的获取和归还
//        Thread renewalThread = new Thread(new ExpirationRenewal());
//        renewalThread.start();

//        threadPoolTaskExecutor.submit(
//                () -> {
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
        log.info("跳出循环！！！");
//                }
//        );

    }

    public void sleepBySencond(int sencond){
        try {
            Thread.sleep(sencond*1000);
        } catch (InterruptedException e) {
            log.warn("分布式锁: 睡眠异常 ",e);
        }
    }


}
