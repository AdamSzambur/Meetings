package pl.coderslab.app.web.user.notifications;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.models.Notification;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/user/notifications")
public class NotificationController {

    private NotificationService notificationService;
    private UserService userService;

    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
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


