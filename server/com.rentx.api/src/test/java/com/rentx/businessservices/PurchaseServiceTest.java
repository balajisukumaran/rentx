package com.rentx.businessservices;


import com.rentx.dataaccess.repository.PurchaseRepository;
import com.rentx.entities.Purchase;
import com.rentx.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PurchaseServiceTest {
    /**
     * mock instance of purchase repository
     */
    @Mock
    private PurchaseRepository purchaseRepository;
    /**
     * Injecting the mock into purchase service
     */
    @InjectMocks
    private PurchaseService purchaseService;

    /**
     * Initializing mocks before each test method
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test case to get all purchases and to checkk with test data
     */
    @Test
    public void testGetAllPurchases() {
        // Arrange
        List<Purchase> purchases = new ArrayList<>();
        User u = new User();
        u.setUserID(123);
        Purchase p1 = new Purchase(3009, LocalDateTime.now());
        p1.setBuyerID(u);
        p1.setIs_emi(0);
        p1.setPaid(20);
        Purchase p2 = new Purchase(3010,LocalDateTime.now());
        p2.setBuyerID(u);
        p2.setIs_emi(0);
        purchases.add(p1);
        purchases.add(p2);

        when(purchaseRepository.findAll()).thenReturn(purchases);

        // Act
        List<Purchase> result = purchaseService.getAllPurchases(123);

        // Assert
        assertEquals(purchases, result);
        verify(purchaseRepository, times(1)).findAll();
    }

    /**
     * test case to add purchase in purchase repository and assert to verify
     */
    @Test
    public void testAddPurchase() {
        // Arrange
        Purchase purchase = new Purchase(3006,LocalDateTime.now());
        purchase.setIs_emi(0);
        purchase.setPaid(20);
        when(purchaseRepository.save(purchase)).thenReturn(purchase);

        // Act
        Purchase result = purchaseService.addPurchase(purchase);

        // Assert
        assertEquals(purchase, result);
        verify(purchaseRepository, times(1)).save(purchase);
    }

    /**
     * test case to get purchase details by id
     */
    @Test
    public void testGetPurchaseDetailsById() {
        // Arrange
        Purchase purchase = new Purchase(3005, LocalDateTime.now());
        purchase.setIs_emi(0);
        purchase.setPaid(20);

        when(purchaseRepository.findById(3005)).thenReturn(Optional.of(purchase));

        // Act
        Purchase result = purchaseService.getPurchaseDetailsById(3005);

        // Assert
        assertEquals(purchase, result);
        verify(purchaseRepository, times(1)).findById(3005);
    }
}