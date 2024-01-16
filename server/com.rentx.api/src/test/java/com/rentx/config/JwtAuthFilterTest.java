package com.rentx.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.rentx.dtos.UserDto;

class JwtAuthFilterTest {

    /**
     * mock injection for jwt auth filter
     */
    @InjectMocks
    JwtAuthFilter jwtAuthFilter;
    /**
     *  a mock instance of User Authentication Provider
     */
    @Mock
    UserAuthenticationProvider userAuthenticationProvider;
    /**
     *  a mock instance of Http Servlet Request
     */
    @Mock
    HttpServletRequest httpServletRequest;
    /**
     * a mock instance of Http Servlet response
     */
    @Mock
    HttpServletResponse httpServletResponse;

    /**
     * mock for filter chain
     */
    @Mock
    FilterChain filterChain;

    /**
     * before each to gether filter before test cases
     */
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        jwtAuthFilter = new JwtAuthFilter(userAuthenticationProvider);
    }

    /**
     * test for internal filter
     * @throws ServletException
     * @throws IOException
     */
    @Test
    void doFilterInternal() throws ServletException, IOException {
        UserDto userDto = new UserDto();
        userDto.setUserID(123);
        String token = userAuthenticationProvider.createToken(userDto);

        when(httpServletRequest.getHeader(any(String.class))).thenReturn("Bearer "+token);
        jwtAuthFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
    }
}