package com.rentx.businessservices.exceptions;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class AppExceptionTest {
    /**
     * test case to get code
     */
    @Test
    void getCode() {

        AppException appException = new AppException("test message", HttpStatus.NOT_FOUND);

         assertEquals(HttpStatus.NOT_FOUND,appException.getCode());

    }
}