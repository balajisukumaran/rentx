package com.rentx.dataaccess.repository;

import com.rentx.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository  extends JpaRepository<Purchase, Integer> {
}
