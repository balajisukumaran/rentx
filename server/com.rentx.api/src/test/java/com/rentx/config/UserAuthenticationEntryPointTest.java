package com.rentx.config;

import com.rentx.dtos.ErrorDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.AuthenticationException;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.mockito.Mockito.when;

class UserAuthenticationEntryPointTest {
    /**
     * a mock instance of Http Servlet response
     */
    @Mock
    HttpServletResponse httpServletResponse;
    /**
     * a mock instance of  Authentication Exception
     */
    @Mock
    AuthenticationException authenticationException;
    /**
     * a mock instance of Servlet Output Stream
     */
    @Mock
    ServletOutputStream outputStream;
    /**
     * injecting the mock into User Authentication Entry Point
     */
    @InjectMocks
    UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    /**
     * before each setup method for mock
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userAuthenticationEntryPoint = new UserAuthenticationEntryPoint();
    }

    /**
     * test case for the commence for user authentication entry point
     * @throws ServletException
     * @throws IOException
     */
    @Test
    void commence() throws ServletException, IOException {

        StackTraceElement[] stackTraceElement=new StackTraceElement[1];

        when(httpServletResponse.getOutputStream()).thenReturn(outputStream);
        when(authenticationException.getMessage()).thenReturn("testmessage");
        when(authenticationException.getStackTrace()).thenReturn(stackTraceElement);

        userAuthenticationEntryPoint.commence(null,httpServletResponse, authenticationException);
    }
}