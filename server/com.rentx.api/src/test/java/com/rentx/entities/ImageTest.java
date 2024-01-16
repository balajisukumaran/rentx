package com.rentx.entities;

import com.rentx.entities.Image;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageTest {
    /**
     * test case for no args constructor for image
     */
    @Test
    public void testNoArgsConstructor() {
        //arrange
        Image image = new Image();

        //act and assert
        assertNotNull(image);
    }

    /**
     * test case for all args constructor
     */
    @Test
    public void testAllArgsConstructor() {
        //arrange
        Image image = new Image(0, "testImage", "jpg");

        //act and assert
        assertNotNull(image);
        assertEquals(0, image.getImageId());
        assertEquals("testImage", image.getName());
        assertEquals("jpg", image.getImageType());
    }

    /**
     * test case for getter and setter for image object
     */
    @Test
    public void testGettersAndSetters() {
        //arrange
        Image image = new Image();

        //act
        image.setImageId(1);
        image.setId(2);
        image.setName("testImage");
        image.setImageType("png");

        //assert
        assertEquals(1, image.getImageId());
        assertEquals(2, image.getId());
        assertEquals("testImage", image.getName());
        assertEquals("png", image.getImageType());
    }

    /**
     * test case for not equals for image
     */
    @Test
    public void testNotEquals() {
        //arrange
        Image image1 = new Image(1, "testImage1", "jpg");

        //act
        Image image2 = new Image(2, "testImage2", "png");

        //assert
        assertNotEquals(image1, image2);
        assertNotEquals(image1.hashCode(), image2.hashCode());
    }
}
