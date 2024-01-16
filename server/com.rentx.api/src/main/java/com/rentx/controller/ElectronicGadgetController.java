package com.rentx.controller;

import com.rentx.businessservices.ElectronicGadgetService;
import com.rentx.businessservices.interfaces.IElectronicGadgetsService;
import com.rentx.entities.ElectronicGadget;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/electronicgadget")
public class ElectronicGadgetController {
    /**
     * autowire for Electronic Gadget Service
     */
    private final IElectronicGadgetsService electronicGadgetService;

    public ElectronicGadgetController(IElectronicGadgetsService electronicGadgetService) {
        this.electronicGadgetService = electronicGadgetService;
    }

    /**
     * get mapping to get All Electronic Gadgets Details
     * @return electronic gadget details
     */
    @GetMapping
    public List<ElectronicGadget> getAllElectronicGadgetsDetails() {
        return electronicGadgetService.getAllElectronicGadgetsDetails();
    }

    /**
     * post mapping to add Electronic Gadgets
     * @param electronicGadget electronicGadget
     * @return addded ElectronicGadgets in electronic Gadget Service
     */
    @PostMapping("/add")
    public ElectronicGadget addElectronicGadgets(@RequestBody ElectronicGadget electronicGadget) {
        return electronicGadgetService.addElectronicGadgets(electronicGadget);
    }

    /**
     * get mapping get Electronic Gadgets Details By Id
     * @param electronicGadgetsId electronic Gadgets Id
     * @return object if electronic gadget
     */
    @GetMapping("/id/{electronicGadgetsId}")
    public ResponseEntity<Object> getElectronicGadgetsDetailsById(@PathVariable int electronicGadgetsId) {
        ElectronicGadget electronicGadget = electronicGadgetService.getElectronicGadgetsDetailsById(electronicGadgetsId);
        return ResponseEntity.ok(electronicGadget);
    }

    /**
     * get mapping to get Electronic Gadgets Details By Type
     * @param electronicGadgetsType electronic Gadgets Type
     * @return list for the electronic Gadgets Type
     */
    @GetMapping("/type/{electronicGadgetsType}")
    public List<ElectronicGadget> getElectronicGadgetsDetailsByType(@PathVariable String electronicGadgetsType) {
        return electronicGadgetService.getElectronicGadgetsDetailsByType(electronicGadgetsType);
    }
}
