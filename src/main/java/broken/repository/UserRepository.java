package broken.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sk.stopangin.archunit.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
