package pl.dyplom.dyplom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dyplom.dyplom.domain.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
