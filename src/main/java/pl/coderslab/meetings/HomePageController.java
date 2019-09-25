package pl.coderslab.meetings;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.meetings.meeting.MeetingService;
import pl.coderslab.meetings.user.User;
import pl.coderslab.meetings.user.UserService;
import javax.validation.Validator;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomePageController {
    private Validator validator;
    private UserService userService;
    private MeetingService meetingService;

    public HomePageController(UserService userService,MeetingService meetingService) {
        this.userService = userService;
        this.meetingService = meetingService;

    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping
    public String homePage(Model model, Principal principal) {
        model.addAttribute("meetingsNext7Days",meetingService.getMeetingsNext7Days());
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("loggedUsers", userService.findAllLoggedInUsers());
        return "index";
    }
}
