package pl.dyplom.dyplom.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private String address;


}
