package com.rentx.controller;

import com.rentx.businessservices.WishlistService;
import com.rentx.config.UserAuthenticationProvider;
import com.rentx.dataaccess.repository.ProductRealRepository;
import com.rentx.dataaccess.repository.UserRepository;
import com.rentx.dtos.UserDto;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishlistControllerTest {
    /**
     * private mock Mvc object
     */
    private MockMvc mockMvc;
    /***
     * a mock instance of Wishlist Service
     */
    @Mock
    private WishlistService wishlistService;
    /**
     * a mock instance of product real repository
     */
    @Mock
    private ProductRealRepository productRealRepository;
    /**
     * a mock instance of user repository
     */
    @Mock
    private UserRepository userRepository;
    /**
     * a mock instance of User Authentication Provider
     */
    @Mock
    private UserAuthenticationProvider userAuthenticationProvider;
    /**
     * instance of wishlist controller
     */
    private WishlistController wishlistController;

    /**
     * before each for mock for the test cases
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        wishlistController = new WishlistController(wishlistService, userAuthenticationProvider, userRepository, productRealRepository);
    }

    /**
     *  test case to get wishlist for user  with exception
     * @throws Exception for exception handle
     */
    @Test
    public void testGetWishlistForUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserID(1004);
        User user = new User();
        user.setUserID(1004);
        List<ProductReal> wishlist = new ArrayList<>();
        wishlist.add(new ProductReal());
        when(userAuthenticationProvider.getUserByToken("token")).thenReturn(userDto);
        when(userRepository.findByUserID(1004)).thenReturn(user);
        when(wishlistService.getWishlistForUser(user)).thenReturn(wishlist);
        ResponseEntity<List<ProductReal>> responseEntity = wishlistController.getWishlistForUser("Bearer token");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(wishlist, responseEntity.getBody());
    }

    /**
     * test case to add to wishlist with exception
     * @throws Exception for exception handle
     */
    @Test
    public void testAddToWishlist() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserID(1006);
        User user = new User();
        user.setUserID(1006);
        ProductReal product = new ProductReal();
        product.setProductID(5);
        when(userAuthenticationProvider.getUserByToken("token")).thenReturn(userDto);
        when(userRepository.findByUserID(1006)).thenReturn(user);
        when(productRealRepository.findById(5)).thenReturn(java.util.Optional.of(product));
        ResponseEntity<String> responseEntity = wishlistController.addToWishlist("Bearer token", 5);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product added to the wishlist.", responseEntity.getBody());
    }

    /**
     * test case to add wishlist product not found with exception
     * @throws Exception for exception handle
     */
    @Test
    public void testAddToWishlistProductNotFound() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserID(1006);
        User user = new User();
        user.setUserID(1006);
        when(userAuthenticationProvider.getUserByToken("token")).thenReturn(userDto);
        when(userRepository.findByUserID(1006)).thenReturn(user);
        when(productRealRepository.findById(anyInt())).thenReturn(java.util.Optional.empty());
        ResponseEntity<String> responseEntity = wishlistController.addToWishlist("Bearer token", 5);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Product not found.", responseEntity.getBody());
    }

    /**
     * test case to remove from wishlist
     * @throws Exception for exception handle
     */
    @Test
    public void testRemoveFromWishlist() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserID(1006);
        User user = new User();
        user.setUserID(1006);
        when(userAuthenticationProvider.getUserByToken("token")).thenReturn(userDto);
        when(userRepository.findByUserID(1006)).thenReturn(user);
        ProductReal product = productRealRepository.findById(5).orElse(null);
        ResponseEntity<String> responseEntity = wishlistController.removeFromWishlist("Bearer token",5);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product removed from the wishlist.", responseEntity.getBody());
    }
}
