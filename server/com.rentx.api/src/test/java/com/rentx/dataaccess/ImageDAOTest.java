package com.rentx.dataaccess;

import com.rentx.entities.Image;
import jakarta.persistence.EntityManager;

import java.util.*;

import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ImageDAOTest {
    /**
     * image DAO class
     */
    ImageDAO imageDAO;
    /**
     * entity manager object
     */
    EntityManager entityManager;

    /**
     * before each setup method for mock
     */
    @BeforeEach
    public void setUp() {
        entityManager = Mockito.mock(EntityManager.class);
        imageDAO = new ImageDAO(entityManager);
    }

    /**
     * test case to find all image
     */
    @Test
    void findAll() {

        List<Image> images = new ArrayList<>();
        images.add(new Image());
        images.add(new Image());
        TypedQuery<Image> query = (TypedQuery<Image>) Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("from Image", Image.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(images);

        var imageList = imageDAO.findAll();

        assertNotNull(imageList);
        assertEquals(imageList.size(), 2);
    }

    /**
     * test case to insert the image
     */
    @Test
    void insert() {
        Mockito.when(entityManager.merge(new Object())).thenReturn(null);
        var image = imageDAO.insert(new Image());
        assertNotNull(image);
    }

    /**
     * test case to get image
     */
    @Test
    void get() {

        List<Image> images = new ArrayList<>();
        images.add(new Image(123, "test.jpg", "test"));
        images.add(new Image(123, "test.jpg", "test"));
        TypedQuery<Image> query = (TypedQuery<Image>) Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("from Image", Image.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(images);
        var genImage = imageDAO.get("test", 123);

        assertNotNull(genImage);
        assertEquals(2, genImage.size());
        assertEquals(123, genImage.get(1).getId());
        assertEquals("test.jpg", genImage.get(1).getName());
        assertEquals(123, genImage.get(0).getId());
        assertEquals("test.jpg", genImage.get(0).getName());

    }

    /**
     * test case to get null image
     */
    @Test
    void getNull() {

        List<Image> images = new ArrayList<>();
        images.add(new Image(1234, "test.jpg", "test"));
        images.add(new Image(1234, "test.jpg", "test"));
        TypedQuery<Image> query = (TypedQuery<Image>) Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("from Image", Image.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(images);
        var genImage = imageDAO.get("test", 123);
        assertNull(genImage);
    }

}