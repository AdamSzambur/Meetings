package pl.coderslab.meetings.web.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.meetings.dto.LoginFormDTO;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String registrationPage(@RequestParam(required = false) String error, Model model) {
        // DTO - data transfer object (obiekt zawierajacy w tym wypadku pole formularza).
        model.addAttribute("user",new LoginFormDTO());
        if (!(error == null)) {
            model.addAttribute("errorMsg", "Podano błędne dane logowania");
        }
        return "login";
    }
}
