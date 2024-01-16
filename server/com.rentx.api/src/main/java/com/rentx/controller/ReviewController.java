package com.rentx.controller;

import com.rentx.businessservices.ReviewService;
import com.rentx.config.UserAuthenticationProvider;
import com.rentx.dataaccess.repository.ProductRealRepository;
import com.rentx.dataaccess.repository.UserRepository;
import com.rentx.dtos.UserDto;
import com.rentx.entities.ProductReal;
import com.rentx.entities.Review;
import com.rentx.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rentx.businessservices.interfaces.IReviewService;

import java.util.List;

@Getter
@Setter
@RestController
@RequestMapping("/api/products")
public class ReviewController {

    @Autowired
    private IReviewService reviewService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRealRepository productRealRepository;

    private UserAuthenticationProvider userAuthenticationProvider;

    public ReviewController(IReviewService reviewService, UserAuthenticationProvider userAuthenticationProvider, UserRepository userRepository, ProductRealRepository productRealRepository) {
        this.reviewService = reviewService;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.userRepository = userRepository;
        this.productRealRepository = productRealRepository;
    }
    @GetMapping("/review/{productID}")
    public ResponseEntity<List<Review>> getReviewForProduct(@PathVariable int productID) {
        List<Review> reviews = reviewService.getReviewForProduct(productID);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/review/{product_id}")
    public ResponseEntity<?> postReview(@RequestBody Review review,
                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                        @PathVariable int product_id) {

        UserDto userDto = userAuthenticationProvider.getUserByToken(authorizationHeader.split(" ")[1]);
        User user = userRepository.findByUserID(userDto.getUserID());
        ProductReal product = productRealRepository.findById(product_id).orElse(null);
        review.setUser(user);
        review.setProductID(product_id);
        review.setProduct(product);
        reviewService.addReview(review);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
