package pl.coderslab.app.web.user.meetings;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.models.Meeting;
import pl.coderslab.app.web.meetings.MeetingDTO;
import pl.coderslab.app.web.meetings.MeetingService;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;
import pl.coderslab.app.web.user.messages.MessageService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/user/meetings")
public class UserMeetingsController {

    private UserService userService;
    private MeetingService meetingService;
    private MessageService messageService;

    public UserMeetingsController(UserService userService, MeetingService meetingService, MessageService messageService) {
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
        Meeting meeting = meetingService.getMeetingById(id,false);
        if (meeting!=null) {
            model.addAttribute("user", userService.getUserByEmail(principal.getName()));
            model.addAttribute("meeting", new MeetingEditDTO(meetingService.getMeetingById(id, false)));
            return "meetingEdit";
        } else {
            return "redirect:/user/meetings";
        }
    }

    @PostMapping("/edit")
    public String processUserMeetingEdit(@ModelAttribute("meeting") @Valid MeetingEditDTO meeting, BindingResult result) {
        if (result.hasErrors()) {
            return "meetingEdit";
        } else {
            meetingService.updateMeeting(meeting);
            return "redirect:/user/meetings";
        }
    }
}
