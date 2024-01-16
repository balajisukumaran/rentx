package com.rentx.controller;
import org.springframework.web.bind.annotation.*;

import com.rentx.businessservices.interfaces.ICategoryService;
import com.rentx.dtos.CategoryDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final ICategoryService categoryService;

    /**
     * constuctor for category controller
     * @param categoryService
     */
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * get mapping for find all in category service
     * @return list for category
     */
    @GetMapping("/categories")
    public List<CategoryDto> findAll(){
        return categoryService.findAll();
    }

    /**
     * get mapping to get Advertisements By UserId
     * @param categoryID category id
     * @return categiry id for category service
     */
    @GetMapping("/category/id")
    public List<CategoryDto> getCategoryById(@RequestParam int categoryID){
        return categoryService.getCategoryById(categoryID);
    }
}
