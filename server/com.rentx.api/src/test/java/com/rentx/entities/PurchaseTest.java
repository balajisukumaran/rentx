package com.rentx.entities;

import com.rentx.entities.Purchase;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PurchaseTest {
    /**
     * test case for no args constructor in purchase object
     */
    @Test
    public void testNoArgsConstructor() {
        // arrange
        Purchase purchase = new Purchase();

        // act and assert
        assertNotNull(purchase);
    }

    /**
     * test case for Constructor With Purchase ID And Date Of Sale
     */
    @Test
    void testConstructorWithPurchaseIDAndDateOfSale() {
        // arrange
        LocalDateTime currentDate = LocalDateTime.now();
        Purchase purchase = new Purchase(1, currentDate);

        // act and assert
        assertEquals(1, purchase.getPurchaseID());
        assertEquals(currentDate, purchase.getDate_of_sale());
    }

    /**
     * test case for getter and setter
     */
    @Test
    public void testGettersAndSetters() {
        // arrange
        Purchase purchase = new Purchase();

        // act
        purchase.setPurchaseID(1);
        // Set other properties as needed

        // assert
        assertEquals(1, purchase.getPurchaseID());
    }

    /**
     * test case for not equals
     */
    @Test
    public void testNotEquals() {
        // arrange
        Purchase purchase1 = new Purchase();
        purchase1.setPurchaseID(1);
        Purchase purchase2 = new Purchase();
        purchase2.setPurchaseID(2);

        // act and assert
        assertNotEquals(purchase1, purchase2);
        assertNotEquals(purchase1.hashCode(), purchase2.hashCode());
    }

    /**
     * test case for All Args Constructor
     */
    @Test
    void testAllArgsConstructor() {
        // arrange
        User buyer = mock(User.class);
        User seller = mock(User.class);
        Advertisement advertisement = mock(Advertisement.class);
        LocalDateTime currentTime = LocalDateTime.now();

        // act
        Purchase purchase = new Purchase(1, buyer, seller, 2, advertisement, currentTime , 20, 1);

        // assert
        assertEquals(1, purchase.getPurchaseID());
        assertEquals(buyer, purchase.getBuyerID());
        assertEquals(seller, purchase.getSellerID());
        assertEquals(2, purchase.getProductID());
        assertEquals(advertisement, purchase.getAdvertisementID());
        assertEquals(1, purchase.getIs_emi());
        assertEquals(20, purchase.getPaid());
        assertEquals(currentTime, purchase.getDate_of_sale());
    }
}
