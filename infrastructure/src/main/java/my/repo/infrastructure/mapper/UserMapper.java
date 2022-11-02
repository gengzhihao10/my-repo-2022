package my.repo.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.repo.infrastructure.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<UserDO> {

}
