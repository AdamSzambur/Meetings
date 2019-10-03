package pl.coderslab.app.user;

import org.apache.commons.io.IOUtils;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.app.models.User;
import pl.coderslab.app.repositories.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private SessionRegistry sessionRegistry;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, SessionRegistry sessionRegistry) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRegistry = sessionRegistry;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        user.setBase64Image(Base64.getEncoder().encodeToString(user.getAvatar()));
        return user;
    }

    public User getUserById(Long id) {
        User user = userRepository.findOne(id);
        user.setBase64Image(Base64.getEncoder().encodeToString(user.getAvatar()));
        return user;
    }

    public void updateUser(UserFormDTO data) {
        User user = userRepository.findByEmail(data.getEmail());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        if (!data.getAvatar().isEmpty()) {
            try {
                user.setAvatar(IOUtils.toByteArray(data.getAvatar().getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.save(user);
    }


    public List<User> findAllLoggedInUsers() {
      //  System.out.println("Liczba zalogownych uzytkowników : " +sessionRegistry.getAllPrincipals().size());
        List<User> result = sessionRegistry.getAllPrincipals()
                .stream()
                .filter(principal -> principal instanceof UserDetails)
                .map(UserDetails.class::cast)
                .map(p->getUserByEmail(p.getUsername()))
                .collect(Collectors.toList());
        return result;
    }
}
