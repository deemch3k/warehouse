package pl.dyplom.dyplom.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class ProductQuantity {

    //todo разобраться с зависимостями

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long qty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
