package com.rentx.dataaccess;

import com.rentx.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ProductDAOTest {
    /**
     * entity manager object
     */
    EntityManager entityManager;
    /**
     * product DAO
     */
    ProductDAO productDAO;
    /**
     * products list
     */
    List<Product> products;

    /**
     * public method for product DTO test
     */
    public ProductDAOTest(){
        entityManager = Mockito.mock(EntityManager.class);
        productDAO = new ProductDAO(entityManager);
        products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
    }

    /**
     * test case to get all available products
     */
    @Test
    void getAllAvailableProducts() {
        StoredProcedureQuery storedProcedureQuery = Mockito.mock(StoredProcedureQuery.class);
        Mockito.when(entityManager.createNamedStoredProcedureQuery(any(String.class))).thenReturn(storedProcedureQuery);
        Mockito.when(storedProcedureQuery.getResultList()).thenReturn(products);

        var products = productDAO.getAllAvailableProducts();

        assertNotNull(products);
        assertEquals(products.size(),2);
    }

    /**
     * test case to get all available product
     */
    @Test
    void getAllAvailableProduct() {
        StoredProcedureQuery storedProcedureQuery = (StoredProcedureQuery) Mockito.mock(StoredProcedureQuery.class);
        Mockito.when(entityManager.createNamedStoredProcedureQuery(any(String.class))).thenReturn(storedProcedureQuery);
        Mockito.when(storedProcedureQuery.setParameter(any(String.class), any(Integer.class))).thenReturn(storedProcedureQuery);
        Mockito.when(storedProcedureQuery.getResultList()).thenReturn(products);

        var product = productDAO.getAllAvailableProduct(2);

        assertNotNull(product);
    }

    /**
     * test case to get all null available product
     */
    @Test
    void getAllAvailableProductNull() {

        StoredProcedureQuery storedProcedureQuery = (StoredProcedureQuery) Mockito.mock(StoredProcedureQuery.class);
        Mockito.when(entityManager.createNamedStoredProcedureQuery(any(String.class))).thenReturn(storedProcedureQuery);
        Mockito.when(storedProcedureQuery.setParameter(any(String.class), any(Integer.class))).thenReturn(storedProcedureQuery);
        Mockito.when(storedProcedureQuery.getResultList()).thenReturn(new LinkedList<Product>());

        var product = productDAO.getAllAvailableProduct(2);

        assertNull(product);
    }
}