package com.rentx.controller;

import com.rentx.businessservices.KitchenApplianceService;
import com.rentx.entities.KitchenAppliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class KitchenApplianceControllerTest {
    /**
     * a mock instance of Kitchen Appliance Service
     */
    @Mock
    private KitchenApplianceService kitchenApplianceService;
    /**
     * instance for Kitchen Appliance Controller
     */
    private KitchenApplianceController kitchenApplianceController;

    /**
     * before each mock for all the test cases
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        kitchenApplianceController = new KitchenApplianceController(this.kitchenApplianceService);
    }

    /**
     * test case to get All Kitchen Appliance Details
     */
    @Test
    void getAllKitchenApplianceDetails() {
        List<KitchenAppliance> applianceList = new ArrayList<>();
        when(kitchenApplianceService.getAllKitchenApplianceDetails()).thenReturn(applianceList);
        List<KitchenAppliance> result = kitchenApplianceController.getAllKitchenApplianceDetails();
        assertEquals(applianceList, result);
    }

    /**
     * test case to add Kitchen Appliance
     */
    @Test
    void addKitchenAppliance() {
        KitchenAppliance kitchenAppliance = new KitchenAppliance();
        when(kitchenApplianceService.addKitchenAppliance(kitchenAppliance)).thenReturn(kitchenAppliance);
        KitchenAppliance result = kitchenApplianceController.addKitchenAppliance(kitchenAppliance);
        assertEquals(kitchenAppliance, result);
    }

    /**
     * test case to get Kitchen Appliance Details By Id
     */
    @Test
    void getKitchenApplianceDetailsById() {
        int id = 1;
        KitchenAppliance kitchenAppliance = new KitchenAppliance();
        when(kitchenApplianceService.getKitchenApplianceDetailsById(id)).thenReturn(kitchenAppliance);
        ResponseEntity<Object> result = kitchenApplianceController.getKitchenApplianceDetailsById(id);
        assertEquals(ResponseEntity.ok(kitchenAppliance), result);
    }

    /**
     * test case to get Kitchen Appliance Details By Type
      */
    @Test
    void getKitchenApplianceDetailsByType() {
        String type = "Oven";
        List<KitchenAppliance> applianceList = new ArrayList<>();
        when(kitchenApplianceService.getKitchenApplianceDetailsByType(type)).thenReturn(applianceList);
        List<KitchenAppliance> result = kitchenApplianceController.getKitchenApplianceDetailsByType(type);
        assertEquals(applianceList, result);
    }
}
