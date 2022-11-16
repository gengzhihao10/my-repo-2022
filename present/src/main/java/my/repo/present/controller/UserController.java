//package my.repo.present.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import my.repo.api.base.RestResponse;
//import my.repo.api.service.RestUserService;
//import my.repo.api.user.input.RestUserCommand;
//import my.repo.api.user.output.UserOutput;
//import my.repo.business.service.UserServcie;
//import my.repo.common.utils.RedisUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import sso.demo.api.interfaces.TokenApiService;
//
//@Api(value = "用户操作接口")
//@RequestMapping("/user")
//@RestController
//public class UserController implements RestUserService{
//
//    @Autowired
//    private UserServcie userServcie;
//
//    @ApiOperation(value = "新增用户")
//    @PostMapping("/insert")
//    @Override
//    public RestResponse<UserOutput> insertUser(@RequestBody RestUserCommand restUserCommand){
//        return userServcie.insertUser(restUserCommand);
//    }
//}
