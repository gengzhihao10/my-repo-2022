package my.repo.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import my.repo.infrastructure.DO.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderMapper extends BaseMapper<OrderDO> {
}
