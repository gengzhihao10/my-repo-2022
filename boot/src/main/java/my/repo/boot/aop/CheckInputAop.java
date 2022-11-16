package my.repo.boot.aop;


import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import my.repo.api.base.BaseInput;
import my.repo.common.exception.NullInputException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Slf4j
@Component
//如果有多个 可以定义来控制顺序 数字越小执行顺序靠前
@Order(10)
public class CheckInputAop {


    @Pointcut("within(my.repo.api.service.*)")
    public void checkControllerInput2() {
    }

    @Pointcut("@annotation(my.repo.boot.anotation.CheckInput)")
    public void checkControllerInput3() {
    }

    @Before(value = "checkControllerInput3()")
    public void  checkInput(JoinPoint joinPoint) throws Throwable {
        log.info("开始aop校验入参非空");
        Object[] objects = joinPoint.getArgs();
        List<Object> objectList = Arrays.asList(objects);
        if (CollectionUtil.isEmpty(objectList)){
            return;
        }
        Object input = objectList.get(0);

        BaseInput baseInput = null;

        try {
            if (input instanceof BaseInput){
                baseInput = (BaseInput)input;
                baseInput.check();
            }
        } catch (NullInputException e) {
            log.error("检查入参切面异常 ",e);
        }

    }
}

