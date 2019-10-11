package pl.coderslab.app.web.user.notifications;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.models.Notification;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;
import pl.coderslab.app.web.user.messages.MessageService;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/user/notifications")
public class NotificationController {

    private NotificationService notificationService;
    private UserService userService;
    private MessageService messageService;

    public NotificationController(NotificationService notificationService, UserService userService, MessageService messageService) {
        this.notificationService = notificationService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
    }

    @ModelAttribute("numberOfNewMessages")
    public Long numberOfNewMessages() {
        return messageService.getNewUnreadedMessagesByRecipient(principalToUser().getId());
    }


    @GetMapping
    public String getNotificationsByUser(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("notifications", notificationService.getAllNotificationsForUser(user.getId()));
        model.addAttribute("formater", DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
        return "notifications";
    }

    @PostMapping("/delete")
    public String deleteNotification(@RequestParam(required = false) Long notificationId, @RequestParam(required = false) List<Long> selectedNotifications) {
        if (notificationId!=null) {
            notificationService.deleteNotification(notificationId);
        }
        if (selectedNotifications!=null) {
            notificationService.deleteNotification(selectedNotifications);
        }
        return "redirect:/user/notifications";
    }
}


