package pl.coderslab.app.web.user.messages;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.models.AbstractMessage;
import pl.coderslab.app.models.InboxMessage;
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
    public String getMessagesByUser(@RequestParam String box, @RequestParam(required = false) String searchFraze, Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user",user);

        if (searchFraze!=null) {
            model.addAttribute("messages", messageService.getAllMessagesContainsFraze(user.getId(),box, searchFraze));
        } else {
            model.addAttribute("messages", messageService.getAllMessagesByUser(user.getId(),box));
        }
        model.addAttribute("formater", DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
        return "messages";
    }

    @PostMapping("/delete")
    public String deleteMessage(@RequestParam String box, @RequestParam(required = false) Long messageId, @RequestParam(required = false) List<Long> selectedMessages) {
        if (messageId!=null) {
            System.out.println("usuwamy message o id "+messageId);
            messageService.deleteMessage(messageId, box);
        }
        if (selectedMessages!=null) {
            messageService.deleteMessages(selectedMessages, box);
        }
        return "redirect:/user/messages?box="+box;
    }

    @GetMapping("/message")
    public String getMessage(@RequestParam String box, @RequestParam Long messageId, Model model, Principal principal) {
        if (box.equals("inbox")) {
            messageService.setInboxMessageReaded(messageId);
        }
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("messageDTO", new MessageDTO(messageService.getMessageById(messageId,box)));
        return "message";
    }
}


