package pl.coderslab.app.web.meetings;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.web.meetings.member.MemberDTO;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/meetings")
public class MeetingController {

    private UserService userService;
    private MeetingService meetingService;
    private CommentService commentService;

    public MeetingController(UserService userService, MeetingService meetingService, CommentService commentService) {
        this.userService = userService;
        this.meetingService = meetingService;
        this.commentService = commentService;
    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
    }


    @GetMapping
    public String meetingPage(@RequestParam Long id, Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("meeting", meetingService.getMeetingById(id,true));
        model.addAttribute("newComment", new CommentFormDTO(id));
        model.addAttribute("memberDTO", new MemberDTO());
        model.addAttribute("formater", DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
        return "meeting";
    }


    @PostMapping
    public String processMeetingPage(@ModelAttribute("newComment") @Valid CommentFormDTO newComment, BindingResult result,
                                     Model model, Principal principal,@RequestParam Long id) {
        if (result.hasErrors()) {
            model.addAttribute("user", userService.getUserByEmail(principal.getName()));
            model.addAttribute("meeting", meetingService.getMeetingById(id, true));
            if (newComment.getParentId() != null) {
                model.addAttribute("error", newComment.getParentId());
            } else {
                model.addAttribute("error",0);
            }
            return "meeting";
        } else {
            commentService.addComment(newComment,principal.getName());
            return "redirect:/meetings?id="+newComment.getMeetingId();
        }
    }

    private static long[] getTime(LocalDateTime dob, LocalDateTime now) {
        LocalDateTime today = LocalDateTime.of(now.getYear(),
                now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = ((seconds % 3600) / 60);
        long secs = (seconds % 60);

        return new long[]{hours, minutes, secs};
    }
}
