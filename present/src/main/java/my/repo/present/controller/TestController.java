package my.repo.present.controller;

import lombok.extern.slf4j.Slf4j;
import my.repo.api.order.input.RestOrderCommandInput;
import my.repo.common.utils.*;
import my.repo.present.feign.SSOFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sso.demo.api.base.SSOResponse;
import sso.demo.api.token.input.RestGenerateTokenCommand;
import sso.demo.api.token.output.TokenCommandOutput;

import java.util.concurrent.locks.Lock;

@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SSOFeignClient ssoFeignClient;

    @Autowired
    private JedisPool jedisPool;

//    @Autowired
//    private ExpirationRenewalTask expirationRenewalTask;


//    @Autowired
//    private ScheduleExpirationRenewalServiceTest test;

    @PostMapping("/feign")
    public SSOResponse<TokenCommandOutput> test(@RequestBody RestGenerateTokenCommand restTokenCommand){
        SSOResponse<TokenCommandOutput> response = ssoFeignClient.generateToken(restTokenCommand);
        return response;
    }

    @PostMapping("/redis")
    public void redis(@RequestBody RestOrderCommandInput restOrderCommandInput){
//        Jedis jedis = jedisPool.getResource();
//        String checkAndExpireScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
//                "return redis.call('expire',KEYS[1],ARGV[2]) " +
//                "else " +
//                "return 0 end";
//        Object result = jedis.eval(checkAndExpireScript, 1, "test-key", "test-value", "30");
//        log.info("测试结果为 {}", JsonUtil.obj2Str(result));


//        //定义线程池
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(0, 10,
//                1, TimeUnit.SECONDS,
//                new SynchronousQueue<>());
//
//        //添加10个线程获取锁
//        for (int i = 0; i < 10; i++) {
//            pool.submit(() -> {
//                Jedis jedis = null;
//                try {
//                    jedis = jedisPool.getResource();
//                    DistributeLock lock = new RedisDistributeLock("1",jedis);
//                    lock.lock();
//
//                    //模拟业务执行15秒
//                    lock.sleepBySencond(15);
//
//                    lock.unlock();
//                } catch (Exception e){
//                    log.info("发生异常 ",e);
//                }finally {
//                    //归还jedis
//                    if (jedis != null){
//                        jedis.close();
//                    }
//                }
//            });
//        }
//
//        //当线程池中的线程数为0时，退出
//        while (pool.getPoolSize() != 0) {}

//        TestApi testApi = new TestApi();
//        testApi.test();

//        expirationRenewalService.scheduleExpirationRenewal(true);
        //获取jedis
        Jedis jedis = jedisPool.getResource();
//        expirationRenewalService.scheduleExpirationRenewal(jedis,"test-key","test-value");
        Lock lock1 = new RedisDistributeLock("1",jedis);
        lock1.lock();
        lock1.lock();

        try {
            //do something
            log.info("做点什么");
        } catch (Exception e) {
            log.info("发生异常",e);
        } finally {
            lock1.unlock();
            lock1.unlock();
            lock1.unlock();
            //归还jedis
            if (jedis != null){
                jedis.close();
            }
        }

    }
}
