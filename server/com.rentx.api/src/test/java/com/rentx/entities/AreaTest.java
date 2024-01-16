package com.rentx.entities;

import com.rentx.entities.Area;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AreaTest {
    /**
     * test case for no Args Constructor
     */
    @Test
    public void testNoArgsConstructor() {
        //arrange
        Area area = new Area();

        //act and assert
        assertNotNull(area);
    }

    /**
     * test case for All Args Constructor
     */
    @Test
    public void testAllArgsConstructor() {
        //arrange
        Area area = new Area(1, "Test Area", "Test City", "Test State", "Test Country");

        //act and assert
        assertNotNull(area);
        assertEquals(1, area.getAreaID());
        assertEquals("Test Area", area.getAreaName());
    }

    /**
     *  tetst case for getter and setters
     */
    @Test
    public void testGettersAndSetters() {
        //arrange
        Area area = new Area();

        //act
        area.setAreaID(1);
        area.setAreaName("Test Area");
        area.setCity("Test City");
        area.setState("Test State");
        area.setCountry("Test Country");

        //assert
        assertEquals(1, area.getAreaID());
        assertEquals("Test Area", area.getAreaName());
        assertEquals("Test City", area.getCity());
        assertEquals("Test State", area.getState());
        assertEquals("Test Country", area.getCountry());
    }

    /**
     * test case for no equals
     */
    @Test
    public void testNotEquals() {
        //arrange
        Area area1 = new Area(1, "Test Area", "Test City", "Test State", "Test Country");

        //act
        Area area2 = new Area(2, "Another Area", "Another City", "Another State", "Another Country");

        //assert
        assertNotEquals(area1, area2);
        assertNotEquals(area1.hashCode(), area2.hashCode());
    }
}
