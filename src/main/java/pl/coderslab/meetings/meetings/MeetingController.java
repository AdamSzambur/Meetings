package pl.coderslab.meetings.meetings;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.meetings.meetings.member.MemberDTO;
import pl.coderslab.meetings.models.User;
import pl.coderslab.meetings.user.UserService;

import javax.validation.Valid;
import java.security.Principal;

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
    public String userAddPage(@RequestParam Long id, Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("meeting", meetingService.getMeetingById(id,true));
        model.addAttribute("newComment", new CommentFormDTO(id));
        model.addAttribute("memberDTO", new MemberDTO());
        return "meeting";
    }


    @PostMapping
    public String addTweet(@ModelAttribute("newComment") @Valid CommentFormDTO newComment, BindingResult result, Model model, Principal principal,@RequestParam Long id) {
        if (result.hasErrors()) {
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
}
