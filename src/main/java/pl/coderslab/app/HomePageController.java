package pl.coderslab.app;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.converters.PolishDayAndMonthNamesConverter;
import pl.coderslab.app.web.meetings.MeetingService;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;

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
    public String homePage(@ModelAttribute("finderFormDTO") FinderFormDTO finderFormDTO, @RequestParam(required = false) String selectedDate, Model model, Principal principal) {

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

        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("loggedUsers", userService.findAllLoggedInUsers());
        return "index";
    }
}
