package pl.coderslab.meetings.web.controlers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.meetings.Chat;
import pl.coderslab.meetings.models.User;
import pl.coderslab.meetings.services.UserService;
import javax.validation.Validator;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomePageController {
    private Validator validator;
    private UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping
    public String homePage(Model model, Principal principal) {
        ++Chat.i;
        model.addAttribute("varible", Chat.i);
        return "index";
    }
}
