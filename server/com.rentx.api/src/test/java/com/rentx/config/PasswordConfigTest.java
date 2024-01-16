package com.rentx.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PasswordConfigTest {
    /**
     * test case for password encoder bean
     */
    @Test
    public void testPasswordEncoderBean() {
        PasswordConfig passwordConfig = new PasswordConfig();
        PasswordEncoder passwordEncoder = passwordConfig.passwordEncoder();

        assertNotNull(passwordEncoder);
    }
}
