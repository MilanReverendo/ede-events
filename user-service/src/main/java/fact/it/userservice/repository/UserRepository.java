package fact.it.userservice.repository;

import fact.it.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);  // Method to find a user by username
}
