package pl.dyplom.dyplom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dyplom.dyplom.domain.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
