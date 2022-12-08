package my.repo.boot.aop;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import my.repo.api.consts.BaseConsts;
import my.repo.common.utils.RedisDistributeLock;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.locks.Lock;

import static my.repo.api.consts.BaseConsts.TOKEN_FIELD_NAME;

@Aspect
@Slf4j
@Component
//如果有多个 可以定义来控制顺序 数字越小执行顺序靠前
@Order(10)
public class IdempotentByTokenAop {

    @Autowired
    private JedisPool jedisPool;

    @Pointcut("@annotation(my.repo.api.anotation.IdempotentByToken)")
    public void idempotentByToken() {
    }

    @Around(value = "idempotentByToken()")
    public void  idempotentByToken(ProceedingJoinPoint joinPoint){
        ApiOperation apiOperation = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(ApiOperation.class);
        // 获取request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String token = request.getHeader(BaseConsts.TOKEN_FIELD_NAME);
        Jedis jedis = jedisPool.getResource();
        Lock lock = new RedisDistributeLock(token,jedis);
        try {
            joinPoint.proceed();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            lock.unlock();
            //归还jedis
            if (jedis != null){
                jedis.close();
            }
        }
    }
}
