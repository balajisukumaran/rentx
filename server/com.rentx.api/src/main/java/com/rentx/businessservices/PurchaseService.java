package com.rentx.businessservices;

import com.rentx.businessservices.interfaces.IPurchaseService;
import com.rentx.dataaccess.repository.PurchaseRepository;

import com.rentx.entities.Purchase;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PurchaseService implements IPurchaseService {
    /**
     * auto wire Purchase Repository
     */
    @Autowired
    private PurchaseRepository purchaseRepository;

    /**
     * method to get All Purchases
     *
     * @return find result from purchase Repository
     */
    @Override
    public List<Purchase> getAllPurchases(int userId) {
        return purchaseRepository.findAll().stream().filter(p -> {
                    if (p != null && p.getBuyerID() != null)
                        return p.getBuyerID().getUserID() == userId;
                    else return false;
                }
        ).toList();
    }

    /**
     * method to add Purchase
     *
     * @param purchase for purchase
     * @return updated purchase Repository
     */
    @Override
    public Purchase addPurchase(Purchase purchase) {
        purchase.setDate_of_sale(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }

    /**
     * method to get Purchase Details By Id
     *
     * @param purchaseId purchase id integer
     * @return result by id form purchase Repository
     */
    @Override
    public Purchase getPurchaseDetailsById(int purchaseId) {
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);

        if (purchase != null && purchase.isPresent())
            return purchase.get();

        throw new ResourceNotFoundException("Transaction Details with specific purchaseID not found" + purchaseId);
    }
}