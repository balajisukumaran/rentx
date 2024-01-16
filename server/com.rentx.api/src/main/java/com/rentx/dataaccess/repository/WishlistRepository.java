package com.rentx.dataaccess.repository;


import com.rentx.entities.ProductReal;
import com.rentx.entities.User;
import com.rentx.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    /**
     * method to find by user from wishlist
     * @param user user
     * @return list of user
     */
    List<Wishlist> findByUser(User user);

    /**
     * method to find user and product from wishlist
     * @param user user
     * @param product product
     * @return object of wishlist
     */
    Wishlist findByUserAndProduct(User user, ProductReal product);
}
