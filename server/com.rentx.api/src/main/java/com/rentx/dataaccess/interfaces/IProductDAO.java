package com.rentx.dataaccess.interfaces;

import com.rentx.entities.Product;
import java.util.List;

/**
 * Interface to product data access object
 */
public interface IProductDAO {

    /**
     * get all the available objects
     * @return product list
     */
    public List<Product> getAllAvailableProducts();

    /**
     * get the available product
     * @param productId product id to get
     * @return product object
     */
    public Product getAllAvailableProduct(int productId);
}
