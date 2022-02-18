package diplomabackend.repository;

import diplomabackend.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<Consumer,Long> {
    Optional<Consumer> findUserByUsername(String username);

}
