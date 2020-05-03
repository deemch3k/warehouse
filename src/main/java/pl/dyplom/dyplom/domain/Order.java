package pl.dyplom.dyplom.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade =  CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ProductQuantity> productQuantities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_info_id")
    private ClientInfo clientInfo;

}
