package pl.coderslab.meetings.services;

import org.apache.commons.io.IOUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.meetings.dto.UserFormDTO;
import pl.coderslab.meetings.models.User;
import pl.coderslab.meetings.repositories.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class RegistrationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean isEmailAvailable(String email) {
        long count = userRepository.countByEmail(email);
        if (count > 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public void registerUser(UserFormDTO data) {
        User user = new User();
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
