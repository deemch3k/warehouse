package pl.dyplom.dyplom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dyplom.dyplom.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {



}
