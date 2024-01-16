package com.rentx.businessservices;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import lombok.RequiredArgsConstructor;

import com.rentx.businessservices.interfaces.ICategoryService;
import com.rentx.dataaccess.repository.CategoryRepository;
import com.rentx.dtos.CategoryDto;
import com.rentx.mappers.CategoryMapper;

@RequiredArgsConstructor
@Service
@Setter
public class CategoryService implements ICategoryService {
    /**
     * private Category Repository
     */
    private CategoryRepository categoryRepository;

    /**
     * category serive constructor
     * @param categoryRepository category Repository
     */
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * method to fiind all in category dto
     * @return category mapper
     */
    @Override
    public List<CategoryDto> findAll() {
        return CategoryMapper.toCategoryDtos(categoryRepository.findAll());
    }

    /**
     * method to get category by id
     * @param categoryID for category id
     * @return list of category by id
     */
    @Override
    public List<CategoryDto> getCategoryById(@RequestParam int categoryID) {
        return CategoryMapper.toCategoryDtos(categoryRepository.findAllByCategoryID(categoryID));
    }
}