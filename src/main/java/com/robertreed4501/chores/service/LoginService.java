package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.http.requests.LoginRequest;
import com.robertreed4501.chores.model.http.response.LoginResponse;
import com.robertreed4501.chores.utilities.PasswordValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class LoginService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordValidator passwordValidator;

    public LoginResponse login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        LoginResponse response;
        User user = userService.getUserByUsername(username);
        if (user == null) {
            response = new LoginResponse(null, "username not found");
            return response;
        }

        System.out.println(password + " - password, " + user.getPassword() + " - user.getPassword() - LoginService.java");
        System.out.println(passwordEncoder.matches(request.getPassword(), user.getPassword()) + " - bool for checking pw against hash");
/*        if (!password.equals(user.getPassword())) {*/
            //if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
                if (!passwordValidator.checkPassword(request.getPassword(), user.getPassword())) {
            response = new LoginResponse(null, "invalid password");
        } else{
            String key = UUID.randomUUID().toString();
            user.setApiKey(key);
            response = new LoginResponse(user.getUserResponse(), null );

        }


        return response;
    }
}
