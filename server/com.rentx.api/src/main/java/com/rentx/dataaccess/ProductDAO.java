package com.rentx.dataaccess;

import com.rentx.dataaccess.interfaces.IProductDAO;
import com.rentx.entities.Product;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * product data access object implementation
 */
@Repository
public class ProductDAO implements IProductDAO {
    private final EntityManager entityManager;

    /**
     * constructor for product data access object
     * @param entityManager entity manager for persisting information
     */
    @Autowired
    public ProductDAO(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    /**
     * get all the available objects
     * @return product list
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAllAvailableProducts() {
        return entityManager.createNamedStoredProcedureQuery("getAllAvailableProducts").getResultList();
    }


    /**
     * get the available product
     * @param productId product id to get
     * @return product object
     */
    @Override
    public Product getAllAvailableProduct(int productId) {
        var productSearchResult = entityManager.createNamedStoredProcedureQuery("getAllAvailableProduct")
                .setParameter("sp_product_id",productId).getResultList();
        if(productSearchResult.size()>0){
            return (Product) productSearchResult.get(0);
        }
        return null;
    }
}
