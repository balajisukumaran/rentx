package com.rentx.controller;

import com.rentx.dtos.ImageDto;
import com.rentx.entities.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.rentx.businessservices.interfaces.IImageService;

import java.util.*;

class ImageControllerTest {
    /**
     * a mock instance of image service
     */
    @Mock
    private IImageService imageService;
    /**
     * mock insertion fo image controller
     */
    @InjectMocks
    ImageController imageController;

    /**
     * before each mock for all the test case
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        imageController = new ImageController(this.imageService);
    }

    /**
     * test case to update profile image successfully
     * @throws IOException exception handling
     */
    @Test
    void updateUserProfileImageUploadSuccess() throws IOException {
        when(imageService.uploadFile(any(), any(MultipartFile.class), any(Integer.class), any(String.class))).thenReturn(true);
        byte[] content = new byte[1];

        ResponseEntity<String> responseEntity = imageController.uploadFile(new MockMultipartFile("name", content), 123, "test");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("File uploaded successfully", responseEntity.getBody());
    }

    /**
     * test case to update user profile image upload error
     * @throws IOException exception handling
     */
    @Test
    void updateUserProfileImageUploadError() throws IOException {
        when(imageService.uploadFile(any(String.class), any(MultipartFile.class), any(Integer.class), any(String.class))).thenReturn(false);
        byte[] content = new byte[1];

        ResponseEntity<String> responseEntity = imageController.uploadFile(new MockMultipartFile("name", content), 123, "test");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Error while Uploading", responseEntity.getBody());
    }

    /**
     * test case for update User Profile Image Upload Fail
     * @throws IOException for exception
     */
    @Test
    void updateUserProfileImageUploadFail() throws IOException {
        when(imageService.uploadFile(any(String.class), any(MultipartFile.class), any(Integer.class), any(String.class))).thenReturn(false);
        byte[] content = new byte[1];

        ResponseEntity<String> responseEntity = imageController.uploadFile(new MockMultipartFile("name", content), 123, "test");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Error while Uploading", responseEntity.getBody());
    }

    /**
     * test case to get file details for image upload
     */
    @Test
    void getFileDetails() {
        List<ImageDto> imageList = new ArrayList<>();
        imageList.add(new ImageDto());

        when(imageService.get(any(String.class), any(Integer.class))).thenReturn(imageList);

        ResponseEntity<List<ImageDto>> responseEntity = imageController.getFileDetails("test", 123);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }
}