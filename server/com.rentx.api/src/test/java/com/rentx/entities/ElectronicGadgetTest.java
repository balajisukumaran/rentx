package com.rentx.entities;

import com.rentx.entities.ElectronicGadget;
import com.rentx.entities.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ElectronicGadgetTest {
    /**
     * test case for no args constructor
     */
    @Test
    public void testNoArgsConstructor() {
        //arrange
        ElectronicGadget electronicGadget = new ElectronicGadget();

        //act and assert
        assertNotNull(electronicGadget);
    }

    /**
     * test case for all args constructor
     */
    @Test
    public void testAllArgsConstructor() {
        //arrange
        Category category = new Category();

        //act
        ElectronicGadget electronicGadget = new ElectronicGadget(1, "Samsung", "2022", "ZL1", "Mobile", category);

        //assert
        assertNotNull(electronicGadget);
        assertEquals(1, electronicGadget.getElectronicGadgetsID());
        assertEquals("Samsung", electronicGadget.getManufacturer());
        assertEquals("2022", electronicGadget.getYearOfPurchase());
        assertEquals("ZL1", electronicGadget.getModelName());
        assertEquals("Mobile", electronicGadget.getGadgetType());
        assertEquals(category, electronicGadget.getCategoryID());
    }

    /**
     * test case for getter and setter
     */
    @Test
    public void testGettersAndSetters() {
        //arrange
        ElectronicGadget electronicGadget = new ElectronicGadget();
        Category category = new Category();

        //act
        electronicGadget.setElectronicGadgetsID(1);
        electronicGadget.setManufacturer("Samsung");
        electronicGadget.setYearOfPurchase("2022");
        electronicGadget.setModelName("ZL1");
        electronicGadget.setGadgetType("Mobile");
        electronicGadget.setCategoryID(category);

        //assert
        assertEquals(1, electronicGadget.getElectronicGadgetsID());
        assertEquals("Samsung", electronicGadget.getManufacturer());
        assertEquals("2022", electronicGadget.getYearOfPurchase());
        assertEquals("ZL1", electronicGadget.getModelName());
        assertEquals("Mobile", electronicGadget.getGadgetType());
        assertEquals(category, electronicGadget.getCategoryID());
    }

    /**
     * test case for not equals in electronic gadget
     */
    @Test
    public void testNotEquals() {
        //arrange
        Category category1 = new Category();
        Category category2 = new Category();
        ElectronicGadget electronicGadget1 = new ElectronicGadget(1, "Samsung", "2022", "ZL1", "Mobile", category1);

        //act
        ElectronicGadget electronicGadget2 = new ElectronicGadget(2, "Apple", "2023", "Pro", "Laptop", category2);

        //assert
        assertNotEquals(electronicGadget1, electronicGadget2);
        assertNotEquals(electronicGadget1.hashCode(), electronicGadget2.hashCode());
    }

    /**
     * test case for specific constructor
     */
    @Test
    void testSpecificConstructor() {
        //arrange and act
        ElectronicGadget electronicGadget = new ElectronicGadget();

        //assert
        assertNotNull(electronicGadget);
    }
}
