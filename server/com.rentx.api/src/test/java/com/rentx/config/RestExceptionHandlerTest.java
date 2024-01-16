package com.rentx.config;

import com.rentx.dtos.ErrorDto;
import com.rentx.exceptions.AppException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class RestExceptionHandlerTest {
    /**
     * Injecting the mock into RestException Handler
     */
    @InjectMocks
    RestExceptionHandler restExceptionHandler;

    /**
     * setup method for before each test case
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restExceptionHandler = new RestExceptionHandler();
    }

    /**
     * test case to handle the exception
     */
    @Test
    void handleException() {
        AppException appException = new AppException("message", HttpStatus.NOT_FOUND);
        ResponseEntity<ErrorDto> responseEntity = restExceptionHandler.handleException(appException);

        assertNotNull(responseEntity);
    }
}