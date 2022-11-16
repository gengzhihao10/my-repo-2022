package my.repo.boot.aop;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import my.repo.api.base.BaseInput;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
@Component
//如果有多个 可以定义来控制顺序 数字越小执行顺序靠前
@Order(5)
public class TokenAop {

    @Pointcut("@annotation(my.repo.boot.anotation.NeedToken)")
    public void needToken() {
    }

    @Around(value = "needToken()")
    public void  checkInput(ProceedingJoinPoint joinPoint) {

        ApiOperation apiOperation = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(ApiOperation.class);
        // 获取request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String token = request.getHeader("token");

    }
}
