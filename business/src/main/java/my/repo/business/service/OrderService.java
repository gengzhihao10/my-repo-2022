package my.repo.business.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import my.repo.api.base.RestResponse;
import my.repo.api.enums.ResponseCodeEnum;
import my.repo.api.order.input.RestOrderCommandInput;
import my.repo.api.order.output.OrderCommandOutput;
import my.repo.business.converter.OrderConverter;
import my.repo.common.utils.IDUtil;
import my.repo.common.utils.JsonUtil;
import my.repo.common.utils.RedisUtil;
import my.repo.infrastructure.DO.OrderDO;
import my.repo.infrastructure.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private RedisUtil redisUtil;

    public RestResponse<OrderCommandOutput> insertOrder(RestOrderCommandInput restOrderCommandInput){
        //todo 做幂等

        OrderDO orderDO = orderConverter.convert(restOrderCommandInput);


        //雪花算法生成id
        orderDO.setId(IDUtil.getId(null,null));
        orderDO.setCreateTime(new Date());
        orderDO.setUpdateTime(new Date());

        boolean result = false;
        try {
            result = orderRepository.save(orderDO);
        } catch (Exception e) {
            log.error("保存订单发生异常，",e);
        }

        if (!result){
            log.warn("保存订单失败");
            return RestResponse.fail(ResponseCodeEnum.FAILED);
        }else {
            log.debug("保存订单成功，订单id为 {}",orderDO.getId());
        }
        return RestResponse.ok();
    }

    public RestResponse<OrderCommandOutput> updateOrder(RestOrderCommandInput restOrderCommandInput){


        OrderDO orderDO = orderConverter.convert(restOrderCommandInput);
        //这里使用前端传过来的更新时间作为版本号，通过mysql的行锁和乐观锁，实现update操作的幂等
        Date updateTime = orderDO.getUpdateTime();
        orderDO.setUpdateTime(new Date());

        if (null == updateTime){
            log.warn("订单更新时间为空，订单信息为 {}", JsonUtil.obj2Str(orderDO));
            return RestResponse.fail(ResponseCodeEnum.UPDATE_ORDER_UPDATETIME_IS_NULL);
        }
        if (null == orderDO.getId()){
            log.warn("订单id为空，订单信息为 {}", JsonUtil.obj2Str(orderDO));
            return RestResponse.fail(ResponseCodeEnum.UPDATE_ORDER_ID_IS_NULL);
        }
        boolean result = false;
        try {
            result = orderRepository.updateByIdAndUpdateTime(orderDO,updateTime);
        } catch (Exception e) {
            log.error("更新订单发生异常，",e);
        }

        if (!result){
            log.warn("更新订单失败");
            return RestResponse.fail(ResponseCodeEnum.FAILED);
        }else {
            log.debug("更新订单成功，订单id为 {}",orderDO.getId());
        }
        return RestResponse.ok();
    }

}
