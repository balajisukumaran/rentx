package com.rentx.businessservices.interfaces;

import com.rentx.entities.ProductReal;
import com.rentx.entities.User;

import java.util.List;

public interface IWishlistService {
    /**
     * method to get wishlist for user
     * @param user
     * @return list of real products
     */
    List<ProductReal> getWishlistForUser(User user);

    /**
     * method to add to wishlist
     * @param user user
     * @param product product real
     */
    void addToWishlist(User user, ProductReal product);

    /**
     * method to remove product from wishlist
     * @param user user
     * @param product product
     */
    void removeFromWishlist(User user, ProductReal product);


}
