package com.rentx.controller;


import com.rentx.businessservices.PurchaseService;
import com.rentx.businessservices.interfaces.IPurchaseService;
import com.rentx.config.UserAuthenticationProvider;
import com.rentx.dtos.UserDto;
import com.rentx.entities.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;


public class PurchaseControllerTest {
    /**
     * a mock injected to purchase controller
     */
    @InjectMocks
    private PurchaseController purchaseController;
    /**
     * a mock instance of User Authentication Provider
     */
    @Mock
    UserAuthenticationProvider userAuthenticationProvider;
    /**
     * a mock instance of purchase service
     */
    @Mock
    private IPurchaseService purchaseService;

    /**
     * before each mock for all the test cases
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        purchaseController = new PurchaseController(this.purchaseService, this.userAuthenticationProvider);
    }

    /**
     * test case to get All Purchases Details
     */
    @Test
    void getAllPurchasesDetails() {
        List<Purchase> purchaseList = new ArrayList<>();
        String validToken = "validToken";
        String authorizationHeader = "Bearer " + validToken;
        UserDto userDto = new UserDto();
        userDto.setUserID(123);
        when(userAuthenticationProvider.getUserByToken(validToken)).thenReturn(userDto);
        when(purchaseService.getAllPurchases(any(Integer.class))).thenReturn(purchaseList);
        List<Purchase> result = purchaseController.getAllPurchasesDetails(authorizationHeader);
        assertEquals(purchaseList, result);
    }

    /**
     * test case to add Purchase Details
     */
    @Test
    void addPurchaseDetails() {
        Purchase purchase = new Purchase();
        when(purchaseService.addPurchase(purchase)).thenReturn(purchase);
        Purchase result = purchaseController.addPurchaseDetails(purchase);
        assertEquals(purchase, result);
    }

    /**
     * test case to get Purchase Details By Id
     */
    @Test
    void getPurchaseDetailsById() {
        int id = 5;
        Purchase purchase = new Purchase();
        when(purchaseService.getPurchaseDetailsById(id)).thenReturn(purchase);
        ResponseEntity<Purchase> result = purchaseController.getPurchaseDetailsById(id);
        assertEquals(ResponseEntity.ok(purchase), result);
    }

}
