package my.repo.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.repo.infrastructure.DO.UserDO;
import my.repo.infrastructure.mapper.UserMapper;
import my.repo.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl extends ServiceImpl<UserMapper, UserDO> implements UserRepository {
}
