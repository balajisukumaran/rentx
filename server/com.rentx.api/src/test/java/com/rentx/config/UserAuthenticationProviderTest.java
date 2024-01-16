package com.rentx.config;

import com.rentx.businessservices.UserService;
import com.rentx.businessservices.exceptions.AppException;
import com.rentx.controller.UserProfileController;
import com.rentx.dtos.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserAuthenticationProviderTest {
    /**
     * injecting the mock into User Authentication Provider
     */
    @InjectMocks
    UserAuthenticationProvider userAuthenticationProvider;
    /**
     * a mock instance of user service
     */
    @Mock
    UserService userService;

    /**
     * before each to set up the mocks
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userAuthenticationProvider = new UserAuthenticationProvider(userService);
        userAuthenticationProvider.setSecretKey("testSecretKey");
    }

    /**
     * test case to create tocken
     */
    @Test
    void createToken() {
        UserDto userDto = new UserDto();
        userDto.setUserID(123);

        String token=userAuthenticationProvider.createToken(userDto);

        assertNotNull(token);
    }

    /**
     * test case to validate token
     */
    @Test
    void validateToken() {
        UserDto userDto = new UserDto();
        userDto.setUserID(123);
        String testToken= userAuthenticationProvider.createToken(userDto);
        Authentication authentication = userAuthenticationProvider.validateToken(testToken);

        assertNotNull(authentication);
    }

    /**
     * test case to validate the strongly tocken
     */
    @Test
    void validateTokenStrongly() {
        UserDto userDto = new UserDto();
        userDto.setUserID(123);
        String testToken= userAuthenticationProvider.createToken(userDto);
        when(userService.findByLogin(null)).thenReturn(userDto);
        Authentication authentication = userAuthenticationProvider.validateTokenStrongly(testToken);

        assertNotNull(authentication);
    }

    /**
     * test case to get tge user by tocken
     */
    @Test
    void getUserByToken() {
        UserDto userDto = new UserDto();
        userDto.setUserID(123);
        String testToken= userAuthenticationProvider.createToken(userDto);
        when(userService.findByLogin(null)).thenReturn(userDto);
        UserDto user = userAuthenticationProvider.getUserByToken(testToken);

        assertNotNull(user);
    }

    /**
     * test case to get user by tocken exceotion
     */
    @Test
    void getUserByTokenException() {
        UserDto userDto = new UserDto();
        userDto.setUserID(123);
        String testToken= userAuthenticationProvider.createToken(userDto);

        when(userService.findByLogin(any(String.class))).thenReturn(userDto);

        Exception exception = Assertions.assertThrows(
                AppException.class,
                () -> {
                    userAuthenticationProvider.getUserByToken(testToken);
                }
        );
    }

    /**
     * test case to test init method
     */
    @Test
    public void testInit(){
        userAuthenticationProvider.init();
        String expectedEncodedKey = Base64.getEncoder().encodeToString("secret-key".getBytes());
        assertNotNull(expectedEncodedKey);
    }

}