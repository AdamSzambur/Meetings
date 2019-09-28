package pl.coderslab.meetings.meeting.add;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.meetings.meeting.MeetingDTO;
import pl.coderslab.meetings.meeting.MeetingService;
import pl.coderslab.meetings.models.User;
import pl.coderslab.meetings.user.UserFormDTO;
import pl.coderslab.meetings.user.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/meeting")
public class MeetingAddController {

    private UserService userService;
    private MeetingService meetingService;

    public MeetingAddController(UserService userService, MeetingService meetingService) {
        this.userService = userService;
        this.meetingService = meetingService;
    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping("/add")
    public String userAddPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("meeting",new MeetingDTO());
        return "meeting";
    }

    @PostMapping("/add")
    public  String processUserAddPage(@ModelAttribute("meeting") @Valid MeetingDTO meeting, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "meeting";
        }

        meetingService.addMeeting(meeting);
        return "redirect:";
    }
}
