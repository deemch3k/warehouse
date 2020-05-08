package pl.dyplom.dyplom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dyplom.dyplom.domain.order.Order;
import pl.dyplom.dyplom.domain.User;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
