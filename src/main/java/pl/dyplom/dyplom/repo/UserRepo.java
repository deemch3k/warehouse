package pl.dyplom.dyplom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dyplom.dyplom.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
