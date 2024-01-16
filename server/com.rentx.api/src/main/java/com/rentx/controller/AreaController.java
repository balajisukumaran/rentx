
package com.rentx.controller;

import com.rentx.businessservices.interfaces.IAreaService;
import com.rentx.dtos.AreaDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AreaController {
    private final IAreaService areaService;

    /**
     * constructor for area controller
     * @param areaService areaService
     */
    public AreaController(IAreaService areaService) {
        this.areaService = areaService;
    }

    /**
     * method for find all im area
     * @return list for area service
     */
    @GetMapping("/areas")
    public List<AreaDto> findAll() {
        return areaService.findAll();
    }
}