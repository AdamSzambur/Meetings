package pl.coderslab.app.web.user.meetings;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.web.meetings.MeetingDTO;
import pl.coderslab.app.web.meetings.MeetingService;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;

import javax.validation.Valid;
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


    @PostMapping
    public String processUserMeetingPage(@RequestParam String searchFraze, Model model, Principal principal) {

        System.out.println(searchFraze);
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("formater", DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
        model.addAttribute("ownerMeetings", meetingService.getMeetingByOwnerContainsFraze(searchFraze, userService.getUserByEmail(principal.getName())));
        model.addAttribute("memberMeetings", meetingService.getMeetingByMemberContainsFraze(searchFraze,userService.getUserByEmail(principal.getName())));
        return "userMeetings";
    }

    @PostMapping("/delete")
    public String userMeetingDelete(@RequestParam Long meetingId) {
        meetingService.removeMeeting(meetingId);
        return "redirect:/user/meetings";
    }

    @GetMapping("/edit")
    public String userMeetingEditPage(@RequestParam Long id, Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("meeting", new MeetingDTO(meetingService.getMeetingById(id,false)));
        return "meetingEdit";
    }

    @PostMapping("/edit")
    public String processUserMeetingEdit(@ModelAttribute("meeting") @Valid MeetingDTO meeting, BindingResult result) {
        if (result.hasErrors()) {
            return "meetingEdit";
        } else {
            meetingService.updateMeeting(meeting);
            return "redirect:/user/meetings";
        }
    }
}
