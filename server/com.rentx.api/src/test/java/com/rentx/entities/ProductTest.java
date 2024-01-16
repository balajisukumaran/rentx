package com.rentx.entities;

import com.rentx.entities.Product;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    /**
     * test case for no args constructor for product
     */
    @Test
    public void testNoArgsConstructor() {
        //arrange
        Product product = new Product();

        //act and assert
        assertNotNull(product);
    }

    /**
     * test case for not equals for product object
     */
    @Test
    public void testNotEquals() {
        //arrange
        Product product1 = new Product();
        product1.setProductID(1);

        //act
        Product product2 = new Product();
        product2.setProductID(2);

        //assert
        assertNotEquals(product1, product2);
        assertNotEquals(product1.hashCode(), product2.hashCode());
    }

    /**
     * test case for getter methods in product object
     */
    @Test
    void testGetters() {
        //arrange
        Product product = new Product();
        Date date = new Date();
        product.setAdvertisement_id(1);
        product.setAdvt_title("Test Ad Title");
        product.setPost_date(date);
        product.setExpiry_date(date);
        product.setUserID("testUser");
        product.setPhone("7844452145");
        product.setFirst_name("Parth");
        product.setLast_name("Singh");
        product.setPref_language("English");
        product.setJoining_date(date);
        product.setPrivacy("Public");
        product.setStatus("Active");
        product.setEmail("parth.singh@gmail.com");
        product.setProductID(101);
        product.setProduct_name("Test Product");
        product.setProduct_description("Description of the test product");
        product.setPrice(50.0f);
        product.setCategory_id(1);
        product.setCategory_name("Test Category");
        product.setAreaID(201);
        product.setArea_name("Test Area");
        product.setCity("Test City");
        product.setState("Test State");
        product.setCountry("Test Country");

        //act and assert
        assertEquals(1, product.getAdvertisement_id());
        assertEquals("Test Ad Title", product.getAdvt_title());
        assertEquals(date, product.getPost_date());
        assertEquals(date, product.getExpiry_date());
        assertEquals("testUser", product.getUserID());
        assertEquals("7844452145", product.getPhone());
        assertEquals("Parth", product.getFirst_name());
        assertEquals("Singh", product.getLast_name());
        assertEquals("English", product.getPref_language());
        assertEquals(date, product.getJoining_date());
        assertEquals("Public", product.getPrivacy());
        assertEquals("Active", product.getStatus());
        assertEquals("parth.singh@gmail.com", product.getEmail());
        assertEquals(101, product.getProductID());
        assertEquals("Test Product", product.getProduct_name());
        assertEquals("Description of the test product", product.getProduct_description());
        assertEquals(50.0f, product.getPrice());
        assertEquals(1, product.getCategory_id());
        assertEquals("Test Category", product.getCategory_name());
        assertEquals(201, product.getAreaID());
        assertEquals("Test Area", product.getArea_name());
        assertEquals("Test City", product.getCity());
        assertEquals("Test State", product.getState());
        assertEquals("Test Country", product.getCountry());
    }
}
