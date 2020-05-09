package pl.dyplom.dyplom.domain.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dyplom.dyplom.domain.ClientInfo;
import pl.dyplom.dyplom.domain.ProductQuantity;
import pl.dyplom.dyplom.domain.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ProductQuantity> productQuantities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_info_id")
    private ClientInfo clientInfo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}