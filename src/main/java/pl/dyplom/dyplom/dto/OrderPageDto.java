package pl.dyplom.dyplom.dto;

import lombok.Data;
import pl.dyplom.dyplom.domain.Product;

import java.util.List;

@Data
public class OrderPageDto {

    private List<Product> productList;
    private int currentPage;
    private int totalPages;


}
