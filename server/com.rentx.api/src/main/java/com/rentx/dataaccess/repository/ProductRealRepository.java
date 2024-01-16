package com.rentx.dataaccess.repository;

import com.rentx.entities.ElectronicGadget;
import com.rentx.entities.ProductReal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRealRepository extends JpaRepository<ProductReal, Integer> {
    /**
     * query to find last primary key in product table
     * @return value of query
     */
    @Query(value = "SELECT productID FROM product ORDER BY productID DESC LIMIT 1", nativeQuery = true)
    Integer findLastPrimaryKey();

    /**
     * method to find product with product id
     * @param productId product Id
     * @return object of real product
     */
    ProductReal findByProductID(int productId);

    /**
     * method to delete by product id
     * @param productId product Id
     */
    void deleteByProductID(int productId);
}
