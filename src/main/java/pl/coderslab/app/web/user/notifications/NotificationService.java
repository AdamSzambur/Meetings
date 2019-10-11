package pl.coderslab.app.web.user.notifications;

import org.springframework.stereotype.Service;
import pl.coderslab.app.models.Notification;
import pl.coderslab.app.models.User;
import pl.coderslab.app.repositories.NotificationRepository;
import pl.coderslab.app.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NotificationService {

    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void addNotificationForUser(String notificationText, String url, User user, String alertType) {
        setNotificationAndSave(notificationText, url, user, alertType);
    }

    public void addNotificationForUserList(String notificationText, String url, List<User> users, String alertType) {
        users.forEach(u->setNotificationAndSave(notificationText,url,u,alertType));
    }

    private void setNotificationAndSave(String notificationText, String url, User user, String alertType) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setText(notificationText);
        notification.setUrl(url);
        notification.setAlertType(alertType);
        notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification findNotification(Long id) {
        return notificationRepository.findOne(id);
    }

    public List<Notification> getAllNotificationsForUser(Long userId) {
        List<Notification> result = notificationRepository.findAllByUserIdOrderByCreatedDesc(userId);
        // ustawiamy id uzytkownika nie potrzebujemy calego do JSona.
        result.forEach(n->n.setOwnerId(n.getUser().getId()));
        return result;
    }

    public List<Notification> getAllNotificationsByUserAndStatus(Long userId, String status) {
        return notificationRepository.findAllByUserIdAndStatusOrderByCreated(userId, status);
    }

    public void setStatusForNotificationById(String status, Long notificationId) {
        Notification notification = notificationRepository.findOne(notificationId);
        notification.setStatus(status);
        notificationRepository.save(notification);
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.delete(notificationId);
    }

    public void deleteNotification(List<Long> notificationsId) {
        notificationsId.forEach(n->notificationRepository.delete(n));
    }
}
