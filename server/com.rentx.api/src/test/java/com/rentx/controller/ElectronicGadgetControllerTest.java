package com.rentx.controller;

import com.rentx.businessservices.ElectronicGadgetService;
import com.rentx.businessservices.interfaces.IElectronicGadgetsService;
import com.rentx.entities.ElectronicGadget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ElectronicGadgetControllerTest {
    /**
     * a mock instance of Electronic Gadget Service
     */
    @Mock
    private IElectronicGadgetsService electronicGadgetService;
    /**
     * controller for Electronic Gadget Controller
     */
    private ElectronicGadgetController electronicGadgetControllerController;

    /**
     * before each mock for all test cases
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        electronicGadgetControllerController = new ElectronicGadgetController(this.electronicGadgetService);
    }

    /**
     * test case to get All Electronic Gadgets Details
     */
    @Test
    void getAllElectronicGadgetsDetails() {
        List<ElectronicGadget> gadgetList = new ArrayList<>();
        when(electronicGadgetService.getAllElectronicGadgetsDetails()).thenReturn(gadgetList);
        List<ElectronicGadget> result = electronicGadgetControllerController.getAllElectronicGadgetsDetails();
        assertEquals(gadgetList, result);
    }

    /**
     * test case to add Electronic Gadgets
     */
    @Test
    void addElectronicGadgets() {
        ElectronicGadget electronicGadget = new ElectronicGadget();
        when(electronicGadgetService.addElectronicGadgets(electronicGadget)).thenReturn(electronicGadget);
        ElectronicGadget result = electronicGadgetControllerController.addElectronicGadgets(electronicGadget);
        assertEquals(electronicGadget, result);
    }

    /**
     * test case to get Electronic Gadgets Details By Id
     */
    @Test
    void getElectronicGadgetsDetailsById() {
        int id = 1;
        ElectronicGadget electronicGadget = new ElectronicGadget();
        when(electronicGadgetService.getElectronicGadgetsDetailsById(id)).thenReturn(electronicGadget);
        ResponseEntity<Object> result = electronicGadgetControllerController.getElectronicGadgetsDetailsById(id);
        assertEquals(ResponseEntity.ok(electronicGadget), result);
    }

    /**
     * test case to get Electronic Gadgets Details By Type
     */
    @Test
    void getElectronicGadgetsDetailsByType() {
        String type = "Phone";
        List<ElectronicGadget> gadgetList = new ArrayList<>();
        when(electronicGadgetService.getElectronicGadgetsDetailsByType(type)).thenReturn(gadgetList);
        List<ElectronicGadget> result = electronicGadgetControllerController.getElectronicGadgetsDetailsByType(type);
        assertEquals(gadgetList, result);
    }    
}
