package com.rentx.businessservices;


import com.rentx.businessservices.interfaces.IWishlistService;
import com.rentx.dataaccess.repository.WishlistRepository;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;
import com.rentx.entities.Wishlist;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WishlistService implements IWishlistService {
    /**
     * autowire Wishlist Repository
     */
    @Autowired
    private WishlistRepository wishlistRepository;

    /**
     * constructor for wishlist service
     * @param wishlistRepository wishlist Repository
     */
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }
    @Override
    public List<ProductReal> getWishlistForUser(User user) {
        List<Wishlist> wishlistItems = wishlistRepository.findByUser(user);
        return wishlistItems.stream().map(Wishlist::getProduct).collect(Collectors.toList());
    }

    /**
     * method to add wishlist user
     * @param user user
     * @param product product real
     */
    @Override
    public void addToWishlist(User user, ProductReal product) {
        Wishlist wishlistItem = new Wishlist();
        wishlistItem.setUser(user);
        wishlistItem.setProduct(product);
        wishlistRepository.save(wishlistItem);
    }

    /**
     * method to removee from wish list
     * @param user user
     * @param product product
     */
    public void removeFromWishlist(User user, ProductReal product) {
        Wishlist wishlist = wishlistRepository.findByUserAndProduct(user, product);
        if (wishlist != null) {
            wishlistRepository.delete(wishlist);
        }
    }

}

