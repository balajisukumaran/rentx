package com.rentx.controller;

import com.rentx.businessservices.interfaces.ICategoryService;
import com.rentx.dtos.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {
    /**
     * a mock instance of category Service
     */
    @Mock
    private ICategoryService categoryService;
    /**
     * instance of category controller
     */
    private CategoryController categoryController;

    /**
     * before each for all the test case to gether mock data
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryController = new CategoryController(categoryService);
    }

    /**
     * test case to find all
     */
    @Test
    void findAll() {
        List<CategoryDto> categoryList = new ArrayList<>();

        when(categoryService.findAll()).thenReturn(categoryList);

        List<CategoryDto> result = categoryController.findAll();

        assertEquals(categoryList, result);
    }

    /**
     * test case to get category by id
     */
    @Test
    void getCategoryById() {
        int categoryId = 1;
        List<CategoryDto> categoryList = new ArrayList<>();

        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryList);

        List<CategoryDto> result = categoryController.getCategoryById(categoryId);

        assertEquals(categoryList, result);
    }
}
