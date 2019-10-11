package pl.coderslab.app.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.converters.PolishDayAndMonthNamesConverter;
import pl.coderslab.app.web.meetings.MeetingService;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;
import pl.coderslab.app.web.user.messages.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomePageController {
    private Validator validator;
    private UserService userService;
    private MeetingService meetingService;
    private MessageService messageService;



    public HomePageController(UserService userService,MeetingService meetingService, MessageService messageService) {
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
    public String homePage(HttpServletRequest request, @ModelAttribute("finderFormDTO") FinderFormDTO finderFormDTO, @RequestParam(required = false) String selectedDate, Model model, Principal principal) {
        if (selectedDate!=null) {
            model.addAttribute("title","Wszystkie spotkania dostepne we wskazanym dniu");
            model.addAttribute("meetings", meetingService.getMeetingByDate(selectedDate));
        } else if (finderFormDTO.getDistance()!=null) {
            model.addAttribute("title","Wszystkie spotkania zawierające podaną frazę dostepne we wskazanej odległości");
            model.addAttribute("meetings", meetingService.getMeetingByFinderForm(finderFormDTO));
            model.addAttribute("finderFormDTO", finderFormDTO);
        } else {
            model.addAttribute("title","Wydarzenia kolejne 7 dni");
            model.addAttribute("meetings",meetingService.getMeetingsNext7Days());
        }

        //potrzebujemy tego do parsowania LocalDateTime
        model.addAttribute("changeEnglishToPolish", new PolishDayAndMonthNamesConverter());
        model.addAttribute("googleKey", request.getServletContext().getInitParameter("apiKey"));
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("loggedUsers", userService.findAllLoggedInUsers());
        return "index";
    }
}
