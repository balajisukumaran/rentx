package com.rentx.dataaccess.repository;

import java.util.List;
import com.rentx.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long>{
    /**
     * method to find all from category
     * @return object find all
     */
    List<Category> findAll();

    /**
     * method to find All By Category ID
     * @param catID cat ID
     * @return cat id of object
     */
    List<Category> findAllByCategoryID(int catID);
}
