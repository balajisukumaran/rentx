package com.rentx.businessservices.interfaces;

import com.rentx.dtos.CategoryDto;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICategoryService {
    /**
     * method to find all in category
     * @return list of category dto
     */
    List<CategoryDto> findAll();

    /**
     * method to get category by id
     * @param categoryID for category id
     * @return list of category dto
     */
    List<CategoryDto> getCategoryById(@RequestParam int categoryID);
    // Category addAdvertisement(@RequestBody Advertisement advertisement);
}