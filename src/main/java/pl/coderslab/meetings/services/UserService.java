package pl.coderslab.meetings.services;

import org.apache.commons.io.IOUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.meetings.dto.UserFormDTO;
import pl.coderslab.meetings.models.User;
import pl.coderslab.meetings.repositories.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
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
}
