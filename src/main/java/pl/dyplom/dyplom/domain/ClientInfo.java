package pl.dyplom.dyplom.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class ClientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private String address;
    private String number;
    private String postcode;

}
