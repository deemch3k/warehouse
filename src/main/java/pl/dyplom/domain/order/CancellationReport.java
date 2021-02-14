package pl.dyplom.domain.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cancellation_report")
@Entity
public class CancellationReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "reason_for_cancellation")
    private String reasonForCancellation;

    @Column(name = "date_of_cancellation")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCancellation;

}
