package my.repo.infrastructure.repository.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import my.repo.api.consts.BaseConsts;
import my.repo.infrastructure.DO.OrderDO;
import my.repo.infrastructure.mapper.OrderMapper;
import my.repo.infrastructure.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderReposirotyImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean updateByIdAndUpdateTime(OrderDO orderDO, Date updateTime){
        if (ObjectUtil.isNull(orderDO) || null == orderDO.getId() || null == updateTime){
            return false;
        }

        LambdaQueryWrapper<OrderDO> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(OrderDO::getId,orderDO.getId());
        //这里使用前端传过来的更新时间作为版本号，通过mysql的行锁和乐观锁，实现update操作的幂等
        wrapper.eq(OrderDO::getUpdateTime,updateTime);

        int result =  orderMapper.update(orderDO,wrapper) ;
        return result == BaseConsts.UPDATE_SUCCESS;
    }
}
