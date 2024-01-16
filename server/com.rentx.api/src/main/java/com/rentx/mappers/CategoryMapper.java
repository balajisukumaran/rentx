package com.rentx.mappers;

import java.util.ArrayList;
import java.util.List;

import com.rentx.dtos.CategoryDto;
import com.rentx.entities.Category;

public class CategoryMapper {
    /**
     * static method to categort dto
     * @param category category
     * @return object of category dto
     */
    public static CategoryDto toCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryID(category.getCategoryID());
        categoryDto.setName(category.getName());     
        return categoryDto;
    }

    /**
     * static method for list on=bject of category dtos
     * @param categories categories
     * @return array list of category dtos
     */
    public static List<CategoryDto> toCategoryDtos(List<Category> categories){
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (var category: categories)
            categoryDtos.add(toCategoryDto(category));
        return categoryDtos;
    }
}