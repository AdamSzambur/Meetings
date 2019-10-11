package pl.coderslab.app.web.user.notifications;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.models.ChatMessage;
import pl.coderslab.app.models.Notification;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.meetings.chat.ChatMessageDTO;
import pl.coderslab.app.web.meetings.chat.ChatService;
import pl.coderslab.app.web.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/user/notifications")
public class NotificationRestController {

    private NotificationService notificationService;
    private UserService userService;

    public NotificationRestController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public List<Notification> getNotificationsByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return notificationService.getAllNotificationsForUser(userId);
    }

    @GetMapping("/{userId}/{status}")
    public List<Notification> getNotificationsByUserAndStatus(@PathVariable Long userId, @PathVariable String status) {
        return notificationService.getAllNotificationsByUserAndStatus(userId,status);
    }

    @PutMapping("/{notificationId}/{status}")
    public void setNotificationStatus(@PathVariable Long notificationId, @PathVariable String status) {
        notificationService.setStatusForNotificationById(status,notificationId);
    }
}


