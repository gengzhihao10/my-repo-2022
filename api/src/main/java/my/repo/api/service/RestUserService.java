package my.repo.api.service;

import my.repo.api.base.RestResponse;
import my.repo.api.user.input.RestUserCommand;
import my.repo.api.user.output.UserOutput;

public interface RestUserService {

    RestResponse<UserOutput> insertUser(RestUserCommand restUserCommand);
}
