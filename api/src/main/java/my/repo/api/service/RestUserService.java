package my.repo.api.service;

import my.repo.api.base.RestResponse;
import my.repo.api.user.input.RestUserCommand;
import my.repo.api.user.output.UserOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/user")
public interface RestUserService {

//    @PostMapping("/insert")
    RestResponse<UserOutput> insertUser(RestUserCommand restUserCommand);
}
