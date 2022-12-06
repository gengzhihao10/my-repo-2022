package my.repo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import javax.swing.plaf.BorderUIResource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

@Slf4j
public class Test {

    @Autowired
    private JedisPool jedisPool;


    public static void main(String[] args) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("")
//        String s = "2022-11-03 13:16:21";
//        Date date =

//         Lock lock1 = new RedisDistributeLock("1",);
//        lock1.lock();
//        try {
//            //do something
//        } catch (Exception e) {
//            log.info("发生异常",e);
//        } finally {
//            lock1.unlock();
//        }
    }

    private static void redistest() {

    }

}
