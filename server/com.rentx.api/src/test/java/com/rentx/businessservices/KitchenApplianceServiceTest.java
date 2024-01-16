package com.rentx.businessservices;

import com.rentx.dataaccess.repository.FurnitureRepository;
import com.rentx.dataaccess.repository.KitchenApplianceRepository;
import com.rentx.entities.KitchenAppliance;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class KitchenApplianceServiceTest {
    /**
     * a mock instance of Kitchen Appliance Repository
     */
    @Mock
    private KitchenApplianceRepository kitchenApplianceRepository;
    /**
     * Injecting the mock into Kitchen Appliance Service
     */
    @InjectMocks
    private KitchenApplianceService kitchenApplianceService;

    /**
     * Initializing mocks before each test method
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test case to get all furniture details and test from appliance repository
     */
    @Test
    public void testGetAllFurnitureDetails() {
        List<KitchenAppliance> kitchenApplianceList = new ArrayList<>();
        kitchenApplianceList.add(new KitchenAppliance());
        kitchenApplianceList.add(new KitchenAppliance());
        when(kitchenApplianceRepository.findAll()).thenReturn(kitchenApplianceList);

        List<KitchenAppliance> result = kitchenApplianceService.getAllKitchenApplianceDetails();

        assertEquals(kitchenApplianceList, result);
        verify(kitchenApplianceRepository, times(1)).findAll();
    }

    /**
     * test case to add furniture with repository and service
     */
    @Test
    public void testAddFurniture() {
        KitchenAppliance furniture = new KitchenAppliance();
        when(kitchenApplianceRepository.save(furniture)).thenReturn(furniture);

        KitchenAppliance result = kitchenApplianceService.addKitchenAppliance(furniture);

        assertEquals(furniture, result);
        verify(kitchenApplianceRepository, times(1)).save(furniture);
    }

    /**
     * test case to get furniture details by id with furniture object and test data
     */
    @Test
    public void testGetFurnitureDetailsById() {
        KitchenAppliance furniture = new KitchenAppliance();
        when(kitchenApplianceRepository.findById(3)).thenReturn(Optional.of(furniture));

        KitchenAppliance result = kitchenApplianceService.getKitchenApplianceDetailsById(3);

        assertEquals(furniture, result);
        verify(kitchenApplianceRepository, times(1)).findById(3);
    }

    /**
     * test case to get furniture details by id not found to check for the exceptions
     */
    @Test
    public void testGetFurnitureDetailsByIdNotFound() {
        when(kitchenApplianceRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> kitchenApplianceService.getKitchenApplianceDetailsById(1));
        verify(kitchenApplianceRepository, times(1)).findById(1);
    }

    /**
     * test case to get furniture details by type
     */
    @Test
    public void testGetFurnitureDetailsByType() {
        List<KitchenAppliance> kitchenApplianceList = new ArrayList<>();
        kitchenApplianceList.add(new KitchenAppliance());
        kitchenApplianceList.add(new KitchenAppliance());
        when(kitchenApplianceRepository.findByApplianceType("WorkDesk")).thenReturn(kitchenApplianceList);
        List<KitchenAppliance> result = kitchenApplianceService.getKitchenApplianceDetailsByType("WorkDesk");

        assertEquals(kitchenApplianceList, result);
        verify(kitchenApplianceRepository, times(1)).findByApplianceType("WorkDesk");
    }
}
