package pl.coderslab.app.web.user.messages;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;
import pl.coderslab.app.web.user.notifications.NotificationService;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/user/messages")
public class MessageController {

    private MessageService messageService;
    private UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
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
    public String getNotificationsByUser(@RequestParam String box, @RequestParam(required = false) String searchFraze, Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user",user);

        if (searchFraze!=null) {
            model.addAttribute("messages", messageService.getAllMessagesContainsFraze(user.getId(),box, searchFraze));
        }
//        if (box.equals("inbox")) {
//            model.addAttribute("messages", messageService.getAllInboxMessagesByRecipientId(user.getId()));
//        } else if (box.equals("outbox")) {
//            model.addAttribute("messages", messageService.getAllOutboxMessagesByRecipientId(user.getId()));
//        }
        model.addAttribute("formater", DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
        return "messages";
    }

//    @PostMapping("/delete")
//    public String deleteNotification(@RequestParam(required = false) Long notificationId, @RequestParam(required = false) List<Long> selectedNotifications) {
//        if (notificationId!=null) {
//            notificationService.deleteNotification(notificationId);
//        }
//        if (selectedNotifications!=null) {
//            notificationService.deleteNotification(selectedNotifications);
//        }
//        return "redirect:/user/notifications";
//    }
}


