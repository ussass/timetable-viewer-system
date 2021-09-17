package ru.trofimov.timetableviewersystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;

@Service("userDetailServiceIml")
public class UserDetailServiceIml implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailServiceIml(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.findByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SecurityUser.fromUser(user);
    }
}
