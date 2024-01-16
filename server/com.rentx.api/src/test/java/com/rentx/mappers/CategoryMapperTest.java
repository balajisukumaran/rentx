package com.rentx.mappers;

import com.rentx.dtos.CategoryDto;
import com.rentx.entities.Category;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryMapperTest {

    /**
     * test case to get category DTO
     */
    @Test
    void toCategoryDto() {
        Category category = new Category();
        category.setCategoryID(123);
        category.setName("name");

        CategoryDto categoryDto = CategoryMapper.toCategoryDto(category);
        assertEquals(category.getCategoryID(),categoryDto.getCategoryID());
        assertEquals(category.getName(),categoryDto.getName());
    }

    /**
     * test case for to category DTO with add category
     */
    @Test
    void toCategoryDtos() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        List<CategoryDto> CategoryDtos = CategoryMapper.toCategoryDtos(categories);

        assertNotNull(CategoryDtos);
        assertEquals(2, CategoryDtos.size());
    }
}