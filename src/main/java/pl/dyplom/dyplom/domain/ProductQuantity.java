package pl.dyplom.dyplom.domain;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table
public class ProductQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long qty;

    @OneToOne
    @JoinColumn(name = "fk_product")
    private Product product;

}
