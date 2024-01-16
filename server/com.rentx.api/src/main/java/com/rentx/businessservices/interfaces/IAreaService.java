package com.rentx.businessservices.interfaces;

import com.rentx.dtos.AreaDto;

import java.util.List;

public interface IAreaService {
    /**
     * method for find all for area service
     * @return list of area
     */
    List<AreaDto> findAll();
}
