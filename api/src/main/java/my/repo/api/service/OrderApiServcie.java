package my.repo.api.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import my.repo.api.base.RestResponse;
import my.repo.api.order.input.RestOrderCommandInput;
import my.repo.api.order.output.OrderCommandOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "订单操作接口")
public interface OrderApiServcie {


    @ApiOperation(value = "新增订单")
    @PostMapping("/order/insert")
    RestResponse<OrderCommandOutput> insertOrder(RestOrderCommandInput restOrderCommandInput);

    @ApiOperation(value = "修改订单")
    @PostMapping("/order/update")
    RestResponse<OrderCommandOutput> updateOrder(RestOrderCommandInput restOrderCommandInput);
}
