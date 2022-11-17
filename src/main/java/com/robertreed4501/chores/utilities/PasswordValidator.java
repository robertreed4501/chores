package com.robertreed4501.chores.utilities;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordValidator {

    PasswordEncoder passwordEncoder;

    public boolean checkPassword (String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }

}
