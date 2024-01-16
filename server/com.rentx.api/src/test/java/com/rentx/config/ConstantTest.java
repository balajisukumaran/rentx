package com.rentx.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantTest {
    /**
     * constant value
     */
    Constant constantValue = new Constant();

    /**
     * test case for Kitchen Appliance Category Id
     */
    @Test
    public void testKitchenApplianceCategoryId() {
        assertEquals(11, Constant.KITCHEN_APPLIANCE_CATEGORY_ID);
    }

    /**
     * test case for Furniture Category Id
     */
    @Test
    public void testFurnitureCategoryId() {
        assertEquals(12, Constant.FURNITURE_CATEGORY_ID);
    }

    /**
     * test case for Books Category Id
     */
    @Test
    public void testBooksCategoryId() {
        assertEquals(13, Constant.BOOKS_CATEGORY_ID);
    }

    /**
     * test case for Electronic Gadget Category Id
     */
    @Test
    public void testElectronicGadgetCategoryId() {
        assertEquals(14, Constant.ELECTRONIC_GADGET_CATEGORY_ID);
    }
}
