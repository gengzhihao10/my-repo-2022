package my.repo.present.controller;

import my.repo.present.feign.SSOFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sso.demo.api.base.RestResponse;
import sso.demo.api.token.input.RestTokenCommand;
import sso.demo.api.token.output.TokenCommandOutput;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private SSOFeignClient ssoFeignClient;

    @PostMapping("/feign")
    public RestResponse<TokenCommandOutput> test(@RequestBody RestTokenCommand restTokenCommand){
        RestResponse<TokenCommandOutput> response = ssoFeignClient.generateToken(restTokenCommand);
        return response;
    }
}