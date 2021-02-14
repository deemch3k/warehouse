package pl.dyplom.dyplom.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @Column(name = "total_amount")
    private int totalAmount;
    private double price;
    private String placement;

    @Column(name = "expiration_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;


}
