package com.robertreed4501.chores.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class PasswordValidatorTest {

    @Autowired
    PasswordValidator passwordValidator;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void doesPasswordValidatorWorkWithMatchingPassword() {
        String password = "asdfghjk";
        String hashedPassword1 = passwordEncoder.encode(password);
        String hashedPassword2 = passwordEncoder.encode(password);
        String hashedPassword3 = passwordEncoder.encode(password);
        String hashedPassword4 = passwordEncoder.encode(password);
        assert passwordValidator.checkPassword(password, hashedPassword1);
        assert passwordValidator.checkPassword(password, hashedPassword2);
        assert passwordValidator.checkPassword(password, hashedPassword3);
        assert passwordValidator.checkPassword(password, hashedPassword4);
        assert !hashedPassword1.equals(hashedPassword2);

    }
}