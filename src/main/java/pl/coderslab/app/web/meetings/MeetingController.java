package pl.coderslab.app.web.meetings;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.models.Meeting;
import pl.coderslab.app.web.meetings.member.MemberDTO;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;
import pl.coderslab.app.web.user.messages.MessageService;

import javax.servlet.http.HttpServletRequest;
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
    private MessageService messageService;
    private HttpServletRequest request;

    public MeetingController(UserService userService, MeetingService meetingService, CommentService commentService,
                             MessageService messageService, HttpServletRequest request) {
        this.userService = userService;
        this.meetingService = meetingService;
        this.commentService = commentService;
        this.messageService = messageService;
        this.request = request;
    }

    @ModelAttribute("googleKey")
    public String googleKey() {
        return request.getServletContext().getInitParameter("apiKey");
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
    public String meetingPage(@RequestParam Long id, Model model, Principal principal) {
        Meeting meeting = meetingService.getMeetingById(id,true);

        if (meeting!=null) {
            model.addAttribute("user", userService.getUserByEmail(principal.getName()));
            model.addAttribute("meeting", meetingService.getMeetingById(id, true));
            model.addAttribute("newComment", new CommentFormDTO(id));
            model.addAttribute("memberDTO", new MemberDTO());
            model.addAttribute("formater", DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
            return "meeting";
        } else {
            return "redirect:/";
        }
    }


    @PostMapping
    public String processMeetingPage(@ModelAttribute("newComment") @Valid CommentFormDTO newComment, BindingResult result,
                                     Model model, Principal principal,@RequestParam Long id) {
        if (result.hasErrors()) {
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
