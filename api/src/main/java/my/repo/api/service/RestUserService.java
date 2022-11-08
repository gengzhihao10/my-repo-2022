package my.repo.api.service;

import my.repo.api.base.RestResponse;
import my.repo.api.user.input.RestUserCommand;
import my.repo.api.user.output.UserOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


public interface RestUserService {


    RestResponse<UserOutput> insertUser(RestUserCommand restUserCommand);
}
