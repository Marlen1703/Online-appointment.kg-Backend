package diplomabackend.repository;

import diplomabackend.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer,Long> {
    Consumer findByUsername(String login);
}
