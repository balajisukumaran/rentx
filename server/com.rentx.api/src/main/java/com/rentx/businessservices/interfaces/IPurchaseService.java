package com.rentx.businessservices.interfaces;

import com.rentx.entities.Purchase;

import java.util.List;

public interface IPurchaseService {

    /**
     * method to get all purchases
     * @return list of purchase
     */
    List<Purchase> getAllPurchases(int userId);

    /**
     * method to add purchase
     * @param purchase for purchase
     * @return object purchase
     */
    Purchase addPurchase(Purchase purchase);

    /**
     * method to get purchase details by id
     * @param purchaseId purchase id integer
     * @return object purchase
     */
    Purchase getPurchaseDetailsById(int purchaseId);
}
