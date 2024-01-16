package com.rentx.dataaccess.repository;

import com.rentx.entities.Advertisement;
import com.rentx.entities.ProductReal;

import java.util.List;

import com.rentx.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    /**
     * method find all for advertisement
     * @return advertisement object
     */
    List<Advertisement> findAll();

    /**
     * method to find user id
     * @param userID user ID
     * @return user object
     */
    List<Advertisement> findByUserID(User userID);

    /**
     * method to find advertisement by product id
     * @param productID product ID
     * @return product real object
     */
    List<Advertisement> findByProductID(ProductReal productID);

    /**
     * find By UserID And ProductID method
     * @param userID user ID
     * @param productID product ID
     * @return object
     */
    List<Advertisement> findByUserIDAndProductID(User userID, ProductReal productID);

    /**
     * method delete by product id
     * @param productReal product Real
     */
    void deleteByProductID(ProductReal productReal);
}