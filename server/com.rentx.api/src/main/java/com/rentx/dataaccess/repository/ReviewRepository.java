package com.rentx.dataaccess.repository;

import com.rentx.entities.Review;
import com.rentx.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByProduct_ProductID(int productID);
}
