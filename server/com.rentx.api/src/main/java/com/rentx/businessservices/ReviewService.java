package com.rentx.businessservices;

import com.rentx.businessservices.interfaces.IReviewService;
import com.rentx.dataaccess.repository.ReviewRepository;
import com.rentx.entities.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    @Override
    public List<Review> getReviewForProduct(int productID) {
        return reviewRepository.findByProduct_ProductID(productID);
    }

    @Override
    public void addReview(@RequestBody Review review) {
        reviewRepository.save(review);
    }
}


