package pl.dyplom.dyplom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dyplom.dyplom.domain.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}
