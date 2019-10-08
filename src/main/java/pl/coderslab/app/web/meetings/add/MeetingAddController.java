package pl.coderslab.app.web.meetings.add;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.web.meetings.MeetingDTO;
import pl.coderslab.app.web.meetings.MeetingService;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;
import pl.coderslab.app.web.user.messages.MessageService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/meetings/add")
public class MeetingAddController {

    private UserService userService;
    private MeetingService meetingService;
    private MessageService messageService;

    public MeetingAddController(UserService userService, MeetingService meetingService, MessageService messageService) {
        this.userService = userService;
        this.meetingService = meetingService;
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
    public String meetingAddPage(Model model, Principal principal) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        model.addAttribute("actualTime", LocalDateTime.now().format(inputFormatter));
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("meeting",new MeetingDTO());
        return "meetingAdd";
    }

    @PostMapping
    public  String processAddPage(@ModelAttribute("meeting") @Valid MeetingDTO meeting, BindingResult result,
                                  Model model, Principal principal) {
        if (result.hasErrors()) {
            return "meetingAdd";
        }
        meetingService.addMeeting(meeting);
        return "redirect:/";
    }
}
