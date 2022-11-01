package my.repo.business.converter;

import my.repo.api.user.input.RestUserCommand;
import my.repo.infrastructure.DO.UserDO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserConverter {

//    UserCommand convert1(RestUserCommand restUserCommand);

    UserDO convert(RestUserCommand restUserCommand);
}
