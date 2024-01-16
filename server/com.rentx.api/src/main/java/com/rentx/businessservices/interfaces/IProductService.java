package com.rentx.businessservices.interfaces;

import com.rentx.dtos.ProductDto;
import com.rentx.entities.ProductReal;

import java.util.List;

/**
 * Interfaces to product service
 */
public interface IProductService {

    /**
     * get all the available products
     * @return product dtos
     */
    public List<ProductDto> getAllAvailableProducts() ;

    /**
     * get a product by id
     * @param productId product id to get
     * @return product dto
     */
    ProductDto getProduct(int productId);

    /**
     * Returns list of all products uploaded by the user
     * @param userId is usedID
     * @return list of all products
     */
    List<ProductReal> getProducts(int userId);

    /**
     * Add product and corresponding advertisement
     * @param userId
     * @param productReal
     * @return
     */
    ProductReal addProduct(int userId, ProductReal productReal);

    /**
     * Update product details
     * @param userId is userID
     * @param productReal takes product entity
     * @return product entity object
     */
    ProductReal updateProduct(int userId, ProductReal productReal);

    /**
     * Deletes products
     * @param userID is userID
     * @param productReal is Product
     * @return number of deleted rows
     */
    int deleteProduct(int userID, ProductReal productReal);
}
