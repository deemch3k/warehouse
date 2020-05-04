package pl.dyplom.dyplom.dto;

import lombok.Data;
import pl.dyplom.dyplom.domain.ClientInfo;
import pl.dyplom.dyplom.domain.ProductQuantity;

import java.util.Set;

@Data
public class OrderDto {

    private Set<ProductQuantity> orderedProducts;
    private ClientInfo clientInfo;

}