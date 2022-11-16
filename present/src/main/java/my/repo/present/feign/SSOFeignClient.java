package my.repo.present.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import sso.demo.api.interfaces.TokenApiService;


/**
 * @author gengzhihao
 * @date 2022/11/16 17:16
 * @description sso单点登陆系统token接口feignclient
**/

@FeignClient(name = "ssoFeignClient",url = "${feignclient.sso}")
public interface SSOFeignClient extends TokenApiService {
}
