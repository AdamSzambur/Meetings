package pl.coderslab.app.user.register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.user.UserFormDTO;
import pl.coderslab.app.models.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/user/register")
public class RegistrationController {

    private RegistrationService registrationService;


    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String registrationPage(Model model) {
        // DTO - data transfer object (obiekt zawierajacy w tym wypadku pole formularza).
        model.addAttribute("data",new UserFormDTO(new User()));
        return "registration";
    }

    @PostMapping
    public  String processRegistrationPage(@ModelAttribute("data") @Valid UserFormDTO data, BindingResult result) {

        if (result.hasErrors()) {
            return "registration";
        }

        if (!data.getPassword().equals(data.getRePassword())) {
            result.rejectValue("repassword", null,"Hasła muszą być zgodne");
            return "registration";
        }

        if (!registrationService.isEmailAvailable(data.getEmail())) {
            result.rejectValue("email", null, "Email jest zajęty");
            return "registration";
        }

        if (data.getAvatar().getSize()>150000) {
            result.rejectValue("avatar", null,"Plik nie może być większy od 150kB");
            return "registration";
        }

        registrationService.registerUser(data);
        return "redirect:/user/login";
    }
}
