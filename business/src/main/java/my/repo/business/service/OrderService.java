package my.repo.business.service;

import lombok.extern.slf4j.Slf4j;
import my.repo.api.base.RestResponse;
import my.repo.api.enums.ResponseCodeEnum;
import my.repo.api.order.input.RestOrderCommandInput;
import my.repo.api.order.output.OrderCommandOutput;
import my.repo.business.converter.OrderConverter;
import my.repo.common.utils.IDUtil;
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
}
