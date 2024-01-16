package com.rentx.businessservices;

import com.google.cloud.storage.*;
import com.rentx.controller.UserProfileController;
import com.rentx.dataaccess.ImageDAO;
import com.rentx.dtos.ImageDto;
import com.rentx.entities.Image;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import com.google.cloud.storage.Blob;

class ImageServiceTest {
    /**
     * Injecting the mock into image service
     */
    @InjectMocks
    ImageService imageService;
    /**
     *  a mock instance of storage
     */
    @Mock
    Storage storage;
    /**
     *  a mock instance of blob
     */
    @Mock
    Blob mockBlob;
    /**
     *  a mock instance of image DAO
     */
    @Mock
    ImageDAO imageDAO;

    /**
     * Initializing mocks before each test method
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        imageService = new ImageService();
        imageService.setStorage(storage);
        imageService.setImageDAO(imageDAO);
    }

    /**
     * test case to download file success in byte array form
     */
    @Test
    void downloadFileSuccess() {
        when(mockBlob.getContent()).thenReturn(new byte[1]);
        when(storage.get(any(String.class), any(String.class))).thenReturn(mockBlob);
        ByteArrayResource expected = new ByteArrayResource(mockBlob.getContent());

        ByteArrayResource res = imageService.downloadFile("bucketName", "test.jpg");

        assertEquals(expected, res);
    }

    /**
     * test case to upload file successfully for image
     */
    @Test
    void uploadFileSuccess() {
        byte[] content = new byte[1];
        when(imageDAO.insert(any(Image.class))).thenReturn(new Image());
        boolean res = imageService.uploadFile("bucketName", new MockMultipartFile("name", content), 123, "test");

        assertTrue(res);
    }

    /**
     * test case to upload file failed exception test
     */
    @Test
    void uploadFileFailed() {
        boolean res = imageService.uploadFile("bucketName", null, 123, "test");
        assertFalse(res);
    }

    /**
     * test case to get list of image files and assert null
     */
    @Test
    void get() {
        List<Image> imageList = new ArrayList<>();
        imageList.add(new Image());

        when(imageDAO.get(any(String.class), any(Integer.class))).thenReturn(imageList);

        List<ImageDto> images = imageService.get("test", 123);
        assertNotNull(images);
    }
}