package com.rentx.controller;

import com.rentx.businessservices.ReviewService;
import com.rentx.businessservices.interfaces.IReviewService;
import com.rentx.config.UserAuthenticationProvider;
import com.rentx.dataaccess.repository.ProductRealRepository;
import com.rentx.dataaccess.repository.UserRepository;
import com.rentx.dtos.UserDto;
import com.rentx.entities.ProductReal;
import com.rentx.entities.Review;
import com.rentx.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReviewControllerTest {
    /**
     * instance of review controller
     */
    private ReviewController reviewController;
    /**
     * a mock instance of review service
     */
    @Mock
    private IReviewService reviewService;
    /**
     * a mock instance of user repository
     */
    @Mock
    private UserRepository userRepository;
    /**
     * a mock instance of Product Real Repository
     */
    @Mock
    private ProductRealRepository productRealRepository;
    /**
     * a mock instance of User Authentication Provider
     */
    @Mock
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * before each mock for all the test cases
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewController = new ReviewController(reviewService, userAuthenticationProvider, userRepository, productRealRepository);
    }

    /**
     * test case to Get Review For Product
     */
    @Test
    public void testGetReviewForProduct() {
        int productID = 1;
        List<Review> reviews = new ArrayList<>();
        when(reviewService.getReviewForProduct(productID)).thenReturn(reviews);

        ResponseEntity<List<Review>> responseEntity = reviewController.getReviewForProduct(productID);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviews, responseEntity.getBody());
    }

    /**
     * test case to post reviews
     */
    @Test
    public void testPostReview() {
        Review review = new Review();
        String authorizationHeader = "Bearer token";
        int product_id = 1;
        UserDto userDto = new UserDto();
        User user = new User();
        when(userAuthenticationProvider.getUserByToken("token")).thenReturn(userDto);
        when(userRepository.findByUserID(userDto.getUserID())).thenReturn(user);
        when(productRealRepository.findById(product_id)).thenReturn(Optional.of(new ProductReal()));
        ResponseEntity<?> responseEntity = reviewController.postReview(review, authorizationHeader, product_id);

        verify(userAuthenticationProvider, times(1)).getUserByToken("token");
        verify(userRepository, times(1)).findByUserID(userDto.getUserID());
        verify(reviewService, times(1)).addReview(review);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
