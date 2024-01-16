package com.rentx.businessservices;


import com.rentx.businessservices.interfaces.IWishlistService;
import com.rentx.dataaccess.repository.WishlistRepository;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;
import com.rentx.entities.Wishlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WishlistServiceTest {
    /**
     * IWishlist Service class
     */
    private IWishlistService wishlistService;
    /**
     *  a mock instance of wishlist Repository
     */
    @Mock
    private WishlistRepository wishlistRepository;

    /**
     * before each for init mock for wishlist service
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        wishlistService = new WishlistService(wishlistRepository);

    }

    /**
     * test case for user related wishlist
     */
    @Test
    public void testGetWishlistForUser() {
        User user = new User();
        Wishlist wishlist1 = new Wishlist();
        Wishlist wishlist2 = new Wishlist();
        ProductReal product1 = new ProductReal();
        ProductReal product2 = new ProductReal();
        wishlist1.setProduct(product1);
        wishlist2.setProduct(product2);
        List<Wishlist> wishlistItems = new ArrayList<>();
        wishlistItems.add(wishlist1);
        wishlistItems.add(wishlist2);
        when(wishlistRepository.findByUser(user)).thenReturn(wishlistItems);

        List<ProductReal> result = wishlistService.getWishlistForUser(user);

        assertEquals(2, result.size());
        assertEquals(product1, result.get(0));
        assertEquals(product2, result.get(1));
    }


    /**
     * test case for add to wishlist
     */
    @Test
    public void testAddToWishlist() {
        User user = new User();
        ProductReal product = new ProductReal();
        Wishlist wishlistItem = new Wishlist();
        wishlistItem.setUser(user);
        wishlistItem.setProduct(product);

        wishlistService.addToWishlist(user, product);

        verify(wishlistRepository, times(1)).save(wishlistItem);
    }

    /**
     * test case for the remove item from the wishlist
     */
    @Test
    public void testRemoveFromWishlist() {
        User user = new User();
        ProductReal product = new ProductReal();
        int productId = 1;
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        when(wishlistRepository.findByUserAndProduct(user, product)).thenReturn(wishlist);

        wishlistService.removeFromWishlist(user, product);

        verify(wishlistRepository, times(1)).delete(wishlist);
    }
}