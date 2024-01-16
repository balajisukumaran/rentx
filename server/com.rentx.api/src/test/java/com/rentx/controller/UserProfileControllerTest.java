package com.rentx.controller;

import com.rentx.businessservices.UserProfileService;
import com.rentx.config.ApiResponse;
import com.rentx.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserProfileControllerTest {
    /**
     * inject mock to User Profile Controller
     */
    @InjectMocks
    private UserProfileController userProfileController;
    /**
     * a mock instance of user profile service
     */
    @Mock
    private UserProfileService userProfileService;

    /**
     * before each mock for test cases
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userProfileController = new UserProfileController();
        userProfileController.setUserService(userProfileService);
    }

    /**
     *  test case to get user profile
     */
    @Test
    void getUserProfile() {
        int userId = 1001;
        User user = new User();
        user.setUserID(userId);
        user.setFirstName("Alice");
        user.setLastName("Smith");
        when(userProfileService.findUserById(userId)).thenReturn(user);

        ResponseEntity<User> responseEntity = userProfileController.getUserProfile(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    /**
     * test case to update user profile
     */
    @Test
    void updateUserProfile() {
        int userId = 1005;
        User user = new User();
        user.setUserID(userId);
        user.setFirstName("Eve");
        user.setLastName("Sins");

        ResponseEntity<ApiResponse> responseEntity = userProfileController.updateUserProfile(userId, user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
