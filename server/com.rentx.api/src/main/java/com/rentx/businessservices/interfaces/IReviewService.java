package com.rentx.businessservices.interfaces;

import com.rentx.entities.Advertisement;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;
import com.rentx.entities.Review;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IReviewService {

    List<Review> getReviewForProduct(int productID);

    void addReview(@RequestBody Review review);


}
