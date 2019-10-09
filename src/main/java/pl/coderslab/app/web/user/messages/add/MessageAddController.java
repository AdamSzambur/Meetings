package pl.coderslab.app.web.user.messages.add;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;
import pl.coderslab.app.web.user.messages.MessageDTO;
import pl.coderslab.app.web.user.messages.MessageService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/user/messages/add")
public class MessageAddController {

    private MessageService messageService;
    private UserService userService;

    public MessageAddController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @ModelAttribute("recipients")
    public List<User> recipients() {
        return userService.getAllUsersWithoutPrincipal(principalToUser().getId());
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
    public String newMessage(@RequestParam Long senderId, @RequestParam(required = false) Long recipientId, Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user",user);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSender(userService.getUserById(senderId));
        if (recipientId!=null) {
            messageDTO.setRecipient(userService.getUserById(recipientId));
        }
        model.addAttribute("messageDTO", messageDTO);
        return "messageAdd";
    }

    @GetMapping("/response")
    public String responseMessage(@RequestParam Long messageId, Principal principal, Model model) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user",user);
        MessageDTO messageDTO = new MessageDTO(messageService.getMessageById(messageId,"inbox"));

        messageDTO.setRecipient(messageDTO.getSender());
        messageDTO.setSender(user);
        messageDTO.setText("\n\n--------Poprzednia wiadomość--------\n"+messageDTO.getTitle()+"\n-----------------------------------------------\n"+messageDTO.getText());
        messageDTO.setTitle("");
        model.addAttribute("messageDTO", messageDTO);
        return "messageAdd";
    }

    @PostMapping
    public String newMessageProgress(@Valid @ModelAttribute("messageDTO") MessageDTO messageDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "messageAdd";
        } else {
            messageService.sendMessage(messageDTO);
            return "redirect:/user/messages?box=outbox";
        }
    }
}


