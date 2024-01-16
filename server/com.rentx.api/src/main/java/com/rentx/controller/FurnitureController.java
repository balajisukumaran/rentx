package com.rentx.controller;

import com.rentx.businessservices.FurnitureService;
import com.rentx.businessservices.interfaces.IAdvertisementService;
import com.rentx.businessservices.interfaces.IFurnitureService;
import com.rentx.entities.Furniture;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/products/furniture")
public class  FurnitureController {
    /**
     * autowire furniture service
     */
    private final IFurnitureService furnitureService;

    public FurnitureController(IFurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    /**
     * get mapping to get All Furniture Category Details
     * @return list of furniture service
     */
    @GetMapping
    public List<Furniture> getAllFurnitureCategoryDetails() {
        return furnitureService.getAllFurnitureDetails();
    }

    /**
     * post mapping to add furniture product details
     * @param furniture furniture
     * @return furniture service
     */
    @PostMapping("/put")
    public Furniture addFurnitureProductDetails(@RequestBody Furniture furniture) {
        return furnitureService.addFurniture(furniture);
    }

    /**
     * get mapping to get Furniture Product Details By Id
     * @param furnitureId furniture Id
     * @return object if furniture
     */
    @GetMapping("/id/{furnitureId}")
    public ResponseEntity<Furniture> getFurnitureProductDetailsById(@PathVariable int furnitureId) {
        Furniture furniture = furnitureService.getFurnitureDetailsById(furnitureId);
        return ResponseEntity.ok(furniture);
    }

    /**
     * get mapping to get Furniture Product Details By Type
     * @param furnitureType furniture Type
     * @return list for Furniture Details By Type
     */
    @GetMapping("/type/{furnitureType}")
    public List<Furniture> getFurnitureProductDetailsByType(@PathVariable String furnitureType) {
        return furnitureService.getFurnitureDetailsByType(furnitureType);
    }
}
