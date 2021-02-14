package pl.dyplom.mapper;

import org.mapstruct.Mapper;
import pl.dyplom.domain.OrderedProduct;
import pl.dyplom.domain.Product;
import pl.dyplom.dto.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDto productDto);
    OrderedProduct toOrderedProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);
    ProductDto toProductDto(OrderedProduct orderedProduct);
}
