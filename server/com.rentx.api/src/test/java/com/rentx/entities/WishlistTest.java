package com.rentx.entities;

import com.rentx.entities.User;
import com.rentx.entities.ProductReal;
import com.rentx.entities.Wishlist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

public class WishlistTest {
    /**
     * test case for no args constructor for wishlist
     */
    @Test
    public void testNoArgsConstructor() {
        // arrange
        Wishlist wishlist = new Wishlist();

        // act
        assertNotNull(wishlist);

        // assert
        assertNull(wishlist.getUser());
        assertNull(wishlist.getProduct());
    }

    /**
     * test case for all args constructor for uer and product real object
     */
    @Test
    public void testAllArgsConstructor() {
        // arrange
        User user = new User();
        ProductReal product = new ProductReal();

        // act
        Wishlist wishlist = new Wishlist(1, user, product);

        // assert
        assertNotNull(wishlist);
        assertEquals(1, wishlist.getWishlistID());
        assertEquals(user, wishlist.getUser());
        assertEquals(product, wishlist.getProduct());
    }

    /**
     * test cases for getter and setter methods
     */
    @Test
    public void testGettersAndSetters() {
        // arrange
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistID(1);

        User user = new User();
        wishlist.setUser(user);

        ProductReal product = new ProductReal();
        wishlist.setProduct(product);

        // act and assert
        assertEquals(1, wishlist.getWishlistID());
        assertEquals(user, wishlist.getUser());
        assertEquals(product, wishlist.getProduct());
    }

    /**
     * test case for not equals
     */
    @Test
    public void testNotEquals() {
        // arrange
        User user1 = new User();
        user1.setUserID(1);

        User user2 = new User();
        user2.setUserID(2);

        ProductReal product1 = new ProductReal();
        product1.setProductID(1);

        ProductReal product2 = new ProductReal();
        product2.setProductID(2);

        Wishlist wishlist1 = new Wishlist(1, user1, product1);
        Wishlist wishlist2 = new Wishlist(2, user2, product2);

        // act and assert
        assertNotEquals(wishlist1, wishlist2);
        assertNotEquals(wishlist1.hashCode(), wishlist2.hashCode());
    }

    /**
     * test case for User And Product Not Null
     */
    @Test
    public void testUserAndProductNotNull() {
        // arrange
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(new User());
        wishlist.setProduct(new ProductReal());

        // act and assert
        assertNotNull(wishlist.getUser());
        assertNotNull(wishlist.getProduct());
    }

    /**
     * test case for user and product null
     */
    @Test
    public void testUserAndProductNull() {
        // arrange
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(null);
        wishlist.setProduct(null);

        // act and assert
        assertNull(wishlist.getUser());
        assertNull(wishlist.getProduct());
    }
}
