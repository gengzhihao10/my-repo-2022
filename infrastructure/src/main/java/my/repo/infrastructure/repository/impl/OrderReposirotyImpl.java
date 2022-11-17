package my.repo.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.repo.infrastructure.DO.OrderDO;
import my.repo.infrastructure.mapper.OrderMapper;
import my.repo.infrastructure.repository.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderReposirotyImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderRepository {
}
