package my.repo.present.controller;

import lombok.extern.slf4j.Slf4j;
import my.repo.api.base.RestResponse;
import my.repo.api.order.input.RestOrderCommandInput;
import my.repo.common.utils.JsonUtil;
import my.repo.common.utils.RedisDistributeLock;
import my.repo.common.utils.RedisUtil;
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
        //获取jedis
        Jedis jedis = jedisPool.getResource();
        Lock lock1 = new RedisDistributeLock("1",jedis);
        lock1.lock();


        try {
            //do something
            log.info("做点什么");
            log.info("做点什么");
            log.info("做点什么");
            log.info("做点什么");
            log.info("做点什么");
        } catch (Exception e) {
            log.info("发生异常",e);
        } finally {
            lock1.unlock();
            //归还jedis
            if (jedis != null){
                jedis.close();
            }
        }

    }
}
