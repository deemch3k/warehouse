package pl.dyplom.dto;

import lombok.Data;
import pl.dyplom.domain.ClientInfo;
import pl.dyplom.domain.ProductQuantity;
import pl.dyplom.domain.User;
import pl.dyplom.domain.order.OrderStatus;

import java.time.LocalDate;
import java.util.Set;

@Data
public class OrderDto {

    private Long id;
    private OrderStatus status;
    private LocalDate shippingDate;
    private Set<ProductQuantity> orderedProducts;
    private ClientInfo clientInfo;
    private User user;

}
