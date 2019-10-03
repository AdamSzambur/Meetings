package pl.coderslab.meetings.user.meetings;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.meetings.meetings.MeetingService;
import pl.coderslab.meetings.models.User;
import pl.coderslab.meetings.user.UserService;

import java.security.Principal;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/user/meetings")
public class UserMeetingsController {

    private UserService userService;
    private MeetingService meetingService;

    public UserMeetingsController(UserService userService, MeetingService meetingService) {
        this.userService = userService;
        this.meetingService = meetingService;
    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping
    public String userMeetingsPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("formater", DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));

        model.addAttribute("ownerMeetings", meetingService.getMeetingByOwner(userService.getUserByEmail(principal.getName())));
        model.addAttribute("memberMeetings", meetingService.getMeetingsByMember(userService.getUserByEmail(principal.getName())));

        return "userMeetings";
    }
}
