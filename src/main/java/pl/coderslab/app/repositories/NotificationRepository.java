package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.models.Notification;
import pl.coderslab.app.models.User;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserIdOrderByCreatedDesc(Long userId);

    List<Notification> findAllByUserIdAndStatusOrderByCreated(Long userId, String status);
}
