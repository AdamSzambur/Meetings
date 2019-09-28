package pl.coderslab.meetings.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.meetings.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Long countByEmail(String email);
    User findByEmail(String email);
}
