package com.rentx.dataaccess.repository;

import java.util.List;

import com.rentx.entities.Area;

import com.rentx.entities.ProductReal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
AreaRepository extends JpaRepository<Area, Long> {
    /**
     * list for area to find all
     * @return find all area from jpa
     */
    List<Area> findAll();
    
    Area findByAreaID(int areaID);
}