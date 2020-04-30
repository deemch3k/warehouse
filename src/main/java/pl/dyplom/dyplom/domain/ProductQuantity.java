package pl.dyplom.dyplom.domain;

import lombok.Data;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Data
@Entity
@Table
public class ProductQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long qty;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ordered_product_id")
    private OrderedProduct orderedProduct;


}
