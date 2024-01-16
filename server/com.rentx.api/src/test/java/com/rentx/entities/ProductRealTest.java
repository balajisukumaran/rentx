package com.rentx.entities;

import com.rentx.entities.Category;
import com.rentx.entities.ProductReal;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class ProductRealTest {
    /**
     * test case for no args constructor
     */
    @Test
    public void testNoArgsConstructor() {
        //arrange
        ProductReal product = new ProductReal();

        //act and assert
        assertNotNull(product);
    }

    /**
     * test case for all args constructor
     */
    @Test
    public void testAllArgsConstructor() {
        //arrange
        Category category = new Category();

        //act
        ProductReal product = new ProductReal(1, "Test Product", "Test Description", 50.0f, "rent",
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        //assert
        assertNotNull(product);
        assertEquals(1, product.getProductID());
        assertEquals("Test Product", product.getName());
    }

    /**
     * test case for getter and setter
     */
    @Test
    public void testGettersAndSetters() {
        //arrange
        ProductReal product = new ProductReal();

        //act
        product.setProductID(1);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(50.0f);
        Category category = new Category();
        product.setCategory(category);

        //assert
        assertEquals(1, product.getProductID());
        assertEquals("Test Product", product.getName());
        assertEquals("Test Description", product.getDescription());
        assertEquals(50.0f, product.getPrice());
        assertEquals(category, product.getCategory());
    }
}
