package com.rentx.businessservices;

import com.rentx.dataaccess.repository.CategoryRepository;
import com.rentx.dtos.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.rentx.entities.Category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.*;

class CategoryServiceTest {
    /**
     * mock inject for category service
     */
    @InjectMocks
    CategoryService categoryService;
    /**
     * mock initiate for category repository
     */
    @Mock
    CategoryRepository categoryRepository;

    /**
     * setup method to get object of category service before each test case
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService();
        categoryService.setCategoryRepository(categoryRepository);
    }

    /**
     * test case to find all in category list array
     */
    @Test
    void findAll() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDto> categoryDtos = categoryService.findAll();

        assertEquals(categories.size(), categoryDtos.size());
    }

    /**
     * test case to get category by id and test a categiry id size
     */
    @Test
    void getCategoryById() {

        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setCategoryID(123);
        categoryList.add(category);
        when(categoryRepository.findAllByCategoryID(any(Integer.class))).thenReturn(categoryList);

        List<CategoryDto> categoryDtos = categoryService.getCategoryById(123);

        assertEquals(categoryList.size(), categoryDtos.size());
        assertEquals(categoryList.get(0).getCategoryID(), categoryDtos.get(0).getCategoryID());
    }
}