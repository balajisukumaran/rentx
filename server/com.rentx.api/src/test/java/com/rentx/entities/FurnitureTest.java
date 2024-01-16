package com.rentx.entities;

import com.rentx.entities.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FurnitureTest {
    /**
     * test case for no args constructor in furniture
     */
    @Test
    public void testNoArgsConstructor() {
        //arrange
        Furniture furniture = new Furniture();

        //act and assert
        assertNotNull(furniture);
    }

    /**
     * test case for all args constructor for furniture
     */
    @Test
    public void testAllArgsConstructor() {
        //arrange
        Category category = new Category();
        Furniture furniture = new Furniture(1, "Sofa", category, "Good", "IKEA", "2022");

        //act and assert
        assertNotNull(furniture);
        assertEquals(1, furniture.getFurnitureID());
        assertEquals("Sofa", furniture.getFurnitureType());
        assertEquals(category, furniture.getCategoryID());
        assertEquals("Good", furniture.getFurnitureCondition());
        assertEquals("IKEA", furniture.getManufacturer());
        assertEquals("2022", furniture.getYearOfPurchase());
    }

    /**
     * test case for getter and setter in category and furniture object
     */
    @Test
    public void testGettersAndSetters() {
        //arrange
        Furniture furniture = new Furniture();
        Category category = new Category();

        //act
        furniture.setFurnitureID(1);
        furniture.setFurnitureType("Table");
        furniture.setCategoryID(category);
        furniture.setFurnitureCondition("Excellent");
        furniture.setManufacturer("WoodenCraft");
        furniture.setYearOfPurchase("2021");

        //assert
        assertEquals(1, furniture.getFurnitureID());
        assertEquals("Table", furniture.getFurnitureType());
        assertEquals(category, furniture.getCategoryID());
        assertEquals("Excellent", furniture.getFurnitureCondition());
        assertEquals("WoodenCraft", furniture.getManufacturer());
        assertEquals("2021", furniture.getYearOfPurchase());
    }

    /**
     * test case for not equals in category
     */
    @Test
    public void testNotEquals() {
        //arrange
        Category category1 = new Category();
        Category category2 = new Category();
        Furniture furniture1 = new Furniture(1, "Table", category1, "Excellent", "WoodenCraft", "2021");
        Furniture furniture2 = new Furniture(2, "Desk", category2, "Good", "OfficeFurniture", "2022");

        //act and assert
        assertNotEquals(furniture1, furniture2);
        assertNotEquals(furniture1.hashCode(), furniture2.hashCode());
    }

    /**
     * test case for first constructor
     */
    @Test
    void testFirstConstructor() {
        //arrange
        Furniture furniture = new Furniture("Table", "ABC Furniture");

        //act and assert
        assertNotNull(furniture);
    }

    /**
     * test case for second constructor for furniture
     */
    @Test
    void testSecondConstructor() {
        //arrange
        Furniture furniture = new Furniture(1, "Chair", "XYZ Furniture");

        //act and assert
        assertNotNull(furniture);
    }
}
