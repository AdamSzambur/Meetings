package pl.coderslab.meetings.user.userEdit;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.meetings.user.UserFormDTO;
import pl.coderslab.meetings.models.User;
import pl.coderslab.meetings.user.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserEditController {

    private UserService userService;

    public UserEditController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("principal")
    public User principalToUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping("/userEdit")
    public String userEditPage(Model model, Principal principal) {
        UserFormDTO userFormDTO = new UserFormDTO(userService.getUserByEmail(principal.getName()));
        model.addAttribute("data",userFormDTO);
        return "userEdit";
    }

    @PostMapping("/userEdit")
    public  String processUserEditPage(@ModelAttribute("data") @Valid UserFormDTO data, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "userEdit";
        }

        if (!data.getPassword().equals(data.getRePassword())) {
            result.rejectValue("repassword", null,"Hasła muszą być zgodne");
            return "userEdit";
        }

        if (data.getAvatar().getSize()>150000) {
            result.rejectValue("avatar", null,"Plik nie może być większy od 150kB");
            return "registration";
        }

        userService.updateUser(data);
        model.addAttribute("msg", "Dane uzytkownika zostały zaktualizowane");
        return "userEdit";
    }
}
