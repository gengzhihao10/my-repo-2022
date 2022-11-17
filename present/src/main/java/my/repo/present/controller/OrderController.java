package my.repo.present.controller;

import io.swagger.annotations.ApiOperation;
import my.repo.api.base.RestResponse;
import my.repo.api.order.input.RestOrderCommandInput;
import my.repo.api.order.output.OrderCommandOutput;
import my.repo.api.service.OrderApiServcie;
import my.repo.business.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements OrderApiServcie {

    @Autowired
    private OrderService orderService;

    @Override
    public RestResponse<OrderCommandOutput> insertOrder(@RequestBody RestOrderCommandInput restOrderCommandInput){
        return orderService.insertOrder(restOrderCommandInput);
    }
}
