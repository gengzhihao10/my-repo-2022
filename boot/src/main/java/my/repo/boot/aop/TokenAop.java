package my.repo.boot.aop;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import my.repo.api.base.BaseInput;
import my.repo.api.consts.BaseConsts;
import my.repo.api.consts.PageUrlConsts;
import my.repo.present.feign.SSOFeignClient;
import org.apache.commons.lang3.StringUtils;
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
import sso.demo.api.base.SSOResponse;
import sso.demo.api.consts.enums.SSOResponseCodeEnum;
import sso.demo.api.token.input.RestCheckTokenCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Aspect
@Slf4j
@Component
//如果有多个 可以定义来控制顺序 数字越小执行顺序靠前
@Order(5)
public class TokenAop {

    @Autowired
    private SSOFeignClient ssoFeignClient;

    private static final String USERNAME_FIELD_NAME = "userName";

    @Pointcut("@annotation(my.repo.api.anotation.NeedToken)")
    public void needToken() {
    }

    @Before(value = "needToken()")
    public void  checkToken(JoinPoint joinPoint) throws Throwable {

        ApiOperation apiOperation = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(ApiOperation.class);
        // 获取request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        String token = request.getHeader(BaseConsts.TOKEN_FIELD_NAME);
        String userName = request.getHeader(USERNAME_FIELD_NAME);
        //token为空，直接重定向到登陆页
        if (StringUtils.isBlank(token) || StringUtils.isBlank(userName)){
            log.info("token与用户名不能为空");
            request.getRequestDispatcher(PageUrlConsts.REDIRECT_HTML).forward(request,response);
            return;
        }
        //token不为空，校验token
        SSOResponse<SSOResponseCodeEnum> checkResult = null;
        RestCheckTokenCommand restCheckTokenCommand = new RestCheckTokenCommand();
        restCheckTokenCommand.setToken(token);
        restCheckTokenCommand.setUserName(userName);
        try {
            checkResult =  ssoFeignClient.checkToken(restCheckTokenCommand);
        } catch (Exception e) {
            log.error("调用sso校验token异常",e);
            request.getRequestDispatcher(PageUrlConsts.REDIRECT_HTML).forward(request,response);
            return;
        }
        if (ObjectUtil.isNull(checkResult)
                || !SSOResponseCodeEnum.SUCCESS.getCode().equals(checkResult.getCode())){
            log.error("调用sso校验token失败");
            request.getRequestDispatcher(PageUrlConsts.REDIRECT_HTML).forward(request,response);
            return;
        }
    }
}
