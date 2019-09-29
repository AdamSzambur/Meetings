package pl.coderslab.meetings.meetings;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.meetings.models.User;
import pl.coderslab.meetings.user.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/meetings")
public class MeetingController {

    private UserService userService;
    private MeetingService meetingService;

    public MeetingController(UserService userService, MeetingService meetingService) {
        this.userService = userService;
        this.meetingService = meetingService;
    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
    }


    @GetMapping
    public String userAddPage(@RequestParam Long id, Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("meeting", meetingService.getMeetingById(id));
        return "meeting";
    }

//    @PostMapping
//    public  String processUserAddPage(@ModelAttribute("meeting") @Valid MeetingDTO meeting, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "meeting";
//        }
//
//        meetingService.addMeeting(meeting);
//        return "redirect:/";
//    }
}
