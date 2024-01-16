package com.rentx.controller;

import com.rentx.businessservices.KitchenApplianceService;
import com.rentx.businessservices.interfaces.IElectronicGadgetsService;
import com.rentx.businessservices.interfaces.IKitchenApplianceService;
import com.rentx.entities.KitchenAppliance;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/kitchenappliance")
public class KitchenApplianceController {

    private final IKitchenApplianceService kitchenApplianceService;

    public KitchenApplianceController(IKitchenApplianceService kitchenApplianceService) {
        this.kitchenApplianceService = kitchenApplianceService;
    }

    /**
     * get mapping to get All Kitchen Appliance Details
     * @return list for the kitchen Appliance Service
     */
    @GetMapping
    public List<KitchenAppliance> getAllKitchenApplianceDetails() {
        return kitchenApplianceService.getAllKitchenApplianceDetails();
    }

    /**
     * post mapping too add kitchen appliance
     * @param kitchenAppliance kitchenAppliance
     * @return kitchen appliance
     */
    @PostMapping("/add")
    public KitchenAppliance addKitchenAppliance(@RequestBody KitchenAppliance kitchenAppliance) {
        return kitchenApplianceService.addKitchenAppliance(kitchenAppliance);
    }

    /**
     * get mapping to get Kitchen Appliance Details By Id
     * @param kitchenApplianceId kitchen Appliance Id
     * @return object for kitchen Appliance Service
     */
    @GetMapping("/id/{kitchenApplianceId}")
    public ResponseEntity<Object> getKitchenApplianceDetailsById(@PathVariable int kitchenApplianceId) {
        KitchenAppliance kitchenAppliance = kitchenApplianceService.getKitchenApplianceDetailsById(kitchenApplianceId);
        return ResponseEntity.ok(kitchenAppliance);
        // try {
        // } catch (Exception e) {
        //     return ResponseUtils.sendNotFoundResponse("Something went wrong!");
        // }
    }

    /**
     * get mapping to get Kitchen Appliance Details By Type
     * @param kitchenAppliancesType kitchen Appliances Type
     * @return list for kitchen Appliance Service
     */
    @GetMapping("/type/{kitchenAppliancesType}")
    public List<KitchenAppliance> getKitchenApplianceDetailsByType(@PathVariable String kitchenAppliancesType) {
        return kitchenApplianceService.getKitchenApplianceDetailsByType(kitchenAppliancesType);
    }
}
