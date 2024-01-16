package com.rentx.utils;

import com.rentx.entities.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class UserServiceUtilsTest {

    /**
     * test case to get electronic gadget
     */
    @Test
    public void testGetElectronicGadget() {
        // Create a sample ProductReal
        ProductReal productReal = new ProductReal();
        productReal.setProductID(1);
        productReal.setCategory(mock(Category.class));
        productReal.setManufacture("Sample Manufacturer");
        productReal.setYear_of_purchase("2022");
        productReal.setModel_name("Sample Model");
        productReal.setGadget_type("Sample Gadget Type");

        // Call the method to test
        ElectronicGadget electronicGadget = UserServiceUtils.getElectronicGadget(productReal);

        // Assert the result
        assertEquals(1, electronicGadget.getElectronicGadgetsID());
        assertEquals(productReal.getCategory(), electronicGadget.getCategoryID());
        assertEquals("Sample Manufacturer", electronicGadget.getManufacturer());
        assertEquals("2022", electronicGadget.getYearOfPurchase());
        assertEquals("Sample Model", electronicGadget.getModelName());
        assertEquals("Sample Gadget Type", electronicGadget.getGadgetType());
    }

    /**
     * test case to get books
     */
    @Test
    public void testGetBooks() {
        // Create a sample ProductReal
        ProductReal productReal = new ProductReal();
        productReal.setProductID(2);
        productReal.setCategory(mock(Category.class));
        productReal.setAuthor("Sample Author");
        productReal.setCondition("Sample Condition");
        productReal.setYear_of_public("2022");

        // Call the method to test
        Books books = UserServiceUtils.getBooks(productReal);

        // Assert the result
        assertEquals(2, books.getBooksID());
        assertEquals(productReal.getCategory(), books.getCategoryID());
        assertEquals("Sample Author", books.getAuthor());
        assertEquals("Sample Condition", books.getCondition());
        assertEquals("2022", books.getYearOfPublished());
    }

    /**
     * test case to get furniture
     */
    @Test
    public void testGetFurniture() {
        // Create a sample ProductReal
        ProductReal productReal = new ProductReal();
        productReal.setProductID(3);
        productReal.setCategory(mock(Category.class));
        productReal.setFurniture_type("Sample Furniture Type");
        productReal.setManufacture("Sample Manufacturer");
        productReal.setCondition("Sample Condition");
        productReal.setYear_of_purchase("2022");

        // Call the method to test
        Furniture furniture = UserServiceUtils.getFurniture(productReal);

        // Assert the result
        assertEquals(3, furniture.getFurnitureID());
        assertEquals("Sample Furniture Type", furniture.getFurnitureType());
        assertEquals("Sample Manufacturer", furniture.getManufacturer());
        assertEquals("Sample Condition", furniture.getFurnitureCondition());
        assertEquals("2022", furniture.getYearOfPurchase());
        assertEquals(productReal.getCategory(), furniture.getCategoryID());
    }

    /**
     * test case to get kitchen appliance
     */
    @Test
    public void testGetKitchenAppliance() {
        // Create a sample ProductReal
        ProductReal productReal = new ProductReal();
        productReal.setProductID(4);
        productReal.setCategory(mock(Category.class));
        productReal.setAppliance_type("Sample Appliance Type");
        productReal.setModel_name("Sample Model");
        productReal.setYear_of_purchase("2022");
        productReal.setManufacture("Sample Manufacturer");

        // Call the method to test
        KitchenAppliance kitchenAppliance = UserServiceUtils.getKitchenAppliance(productReal);

        // Assert the result
        assertEquals(4, kitchenAppliance.getKitchenApplianceID());
        assertEquals("Sample Appliance Type", kitchenAppliance.getApplianceType());
        assertEquals(productReal.getCategory(), kitchenAppliance.getCategoryID());
        assertEquals("Sample Model", kitchenAppliance.getModelName());
        assertEquals("2022", kitchenAppliance.getYearOfPurchase());
        assertEquals("Sample Manufacturer", kitchenAppliance.getManufacturer());
    }
}
