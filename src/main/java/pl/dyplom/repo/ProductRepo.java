package pl.dyplom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dyplom.domain.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {


}
