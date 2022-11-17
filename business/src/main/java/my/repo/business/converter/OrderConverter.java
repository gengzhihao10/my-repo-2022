package my.repo.business.converter;

import my.repo.api.order.input.RestOrderCommandInput;
import my.repo.api.order.output.OrderCommandOutput;
import my.repo.infrastructure.DO.OrderDO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderConverter {

    OrderDO convert(RestOrderCommandInput restOrderCommandInput);

}
