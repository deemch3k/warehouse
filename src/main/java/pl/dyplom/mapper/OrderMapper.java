package pl.dyplom.mapper;

import org.mapstruct.Mapper;
import pl.dyplom.domain.order.Order;
import pl.dyplom.dto.OrderDto;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderDto orderDto);

    OrderDto toOrderDto(Order order);

}
