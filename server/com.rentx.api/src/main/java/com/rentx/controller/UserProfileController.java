package com.rentx.controller;

import com.rentx.businessservices.UserProfileService;
import com.rentx.businessservices.interfaces.IImageService;
import com.rentx.config.ApiResponse;
import com.rentx.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Setter
@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
    /**
     * autowire user profile service
     */
    @Autowired
    private UserProfileService userService;

    /**
     * get mapping to get user profile
     * @param userId user Id
     * @return response entity
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable int userId) {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * put mapping to update user profile
     * @param userId user Id
     * @param user user
     * @return update user profile response entity
     */
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUserProfile(@PathVariable int userId, @RequestBody User user) {
        userService.updateUserProfile(userId, user);
        return ResponseEntity.ok(new ApiResponse("User profile updated successfully"));
    }
}