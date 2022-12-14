package my.repo.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import my.repo.infrastructure.DO.OrderDO;

import java.util.Date;


/**
 * @author gengzhihao
 * @date 2022/11/17 10:24
 * @description 订单
**/

public interface OrderRepository extends IService<OrderDO> {

    boolean updateByIdAndUpdateTime(OrderDO orderDO, Date updateTime);
}
