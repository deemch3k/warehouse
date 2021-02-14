package pl.dyplom.dyplom.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_quantity")
public class ProductQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int qty;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ordered_product_id")
    private OrderedProduct orderedProduct;


}
