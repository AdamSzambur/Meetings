package pl.coderslab.app.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.app.models.User;
import pl.coderslab.app.web.user.UserService;

public class UserConverter implements Converter<String, User> {

    @Autowired
    UserService userService;

    @Override
    public User convert(String s) {
        return userService.getUserById(Long.parseLong(s));
    }
}