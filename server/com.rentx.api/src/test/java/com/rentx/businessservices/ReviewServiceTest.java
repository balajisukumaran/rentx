package com.rentx.businessservices;

import com.rentx.dataaccess.repository.ReviewRepository;
import com.rentx.entities.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {
    /**
     * review service class init
     */
    private ReviewService reviewService;
    /**
     * mock instance of review Repository
     */
    @Mock
    private ReviewRepository reviewRepository;

    /**
     * Initializing mocks before each test method
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    /**
     * test case to get review for product from user
     */
    @Test
    public void testGetReviewForProduct() {
        int productId = 3;
        List<Review> reviews = new ArrayList<>();
        Review review1 = new Review();
        review1.setReviewID(1);
        review1.setProductID(productId);
        review1.setRating(4);
        review1.setDescription("Good product");
        reviews.add(review1);
        Review review2 = new Review();
        review2.setReviewID(2);
        review2.setProductID(productId);
        review2.setRating(5);
        review2.setDescription("Excellent product");
        reviews.add(review2);

        when(reviewRepository.findByProduct_ProductID(productId)).thenReturn(reviews);

        List<Review> result = reviewService.getReviewForProduct(productId);

        assertEquals(2, result.size());
        assertEquals(reviews, result);
        verify(reviewRepository, times(1)).findByProduct_ProductID(productId);
    }

    /**
     * test case to add review
     */
    @Test
    public void testAddReview() {
        Review review = new Review();
        review.setReviewID(2);
        review.setProductID(4);
        review.setRating(5);
        review.setDescription("Value for money");

        reviewService.addReview(review);

        verify(reviewRepository, times(1)).save(review);
    }
}