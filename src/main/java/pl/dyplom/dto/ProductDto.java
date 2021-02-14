package pl.dyplom.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private int totalAmount;
    private String placement;
    private LocalDate expirationDate;
}
