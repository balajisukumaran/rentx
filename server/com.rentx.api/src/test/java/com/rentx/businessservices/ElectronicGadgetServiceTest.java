package com.rentx.businessservices;

import com.rentx.dataaccess.repository.ElectronicGadgetRepository;
import com.rentx.entities.ElectronicGadget;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ElectronicGadgetServiceTest {
    /**
     * a mock instance of Electronic Gadget Repository
     */
    @Mock
    private ElectronicGadgetRepository electronicGadgetRepository;
    /**
     * Injecting the mock into Electronic
     */
    @InjectMocks
    private ElectronicGadgetService electronicGadgetService;

    /**
     * Initializing mocks before each test method
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test case to test autowired fields in electronic gadget service
     */
    @Test
    public void testAutowiredField() {
        // Assert that the @Autowired field is properly injected
        assertNotNull(electronicGadgetService);
    }

    /**
     * test case to get all electronic gadget details from service and assert and verify
     */
    @Test
    public void testGetAllElectronicGadgetDetails() {
        // Arrange
        List<ElectronicGadget> electronicGadgetsList = new ArrayList<>();
        electronicGadgetsList.add(new ElectronicGadget());
        electronicGadgetsList.add(new ElectronicGadget());
        when(electronicGadgetRepository.findAll()).thenReturn(electronicGadgetsList);
        // Act
        List<ElectronicGadget> result = electronicGadgetService.getAllElectronicGadgetsDetails();
        // Assert
        assertEquals(electronicGadgetsList, result);
        verify(electronicGadgetRepository, times(1)).findAll();
    }

    /**
     * test case to add electronic gadgets into database via repositry
     */
    @Test
    public void testAddElectronicGadget() {
        // Arrange
        ElectronicGadget electronicGadget = new ElectronicGadget();
        when(electronicGadgetRepository.save(electronicGadget)).thenReturn(electronicGadget);
        // Act
        ElectronicGadget result = electronicGadgetService.addElectronicGadgets(electronicGadget);
        // Assert
        assertEquals(electronicGadget, result);
        verify(electronicGadgetRepository, times(1)).save(electronicGadget);
    }

    /**
     * test case to get electronic gadget details by id not found
     */
    @Test
    public void testGetElectronicGadgetDetailsByIdNotFound() {
        // Arrange +  Act
        when(electronicGadgetRepository.findById(1)).thenReturn(Optional.empty());
        // Assert
        assertThrows(ResourceNotFoundException.class, () -> electronicGadgetService.getElectronicGadgetsDetailsById(1));
        verify(electronicGadgetRepository, times(1)).findById(1);
    }

    /**
     * test case to Get Electronic Gadget Details By Type
     */
    @Test
    public void testGetElectronicGadgetDetailsByType() {
        // Arrange
        List<ElectronicGadget> electronicGadgetsList = new ArrayList<>();
        electronicGadgetsList.add(new ElectronicGadget());
        electronicGadgetsList.add(new ElectronicGadget());
        when(electronicGadgetRepository.findByGadgetType("WorkDesk")).thenReturn(electronicGadgetsList);
        // Act
        List<ElectronicGadget> result = electronicGadgetService.getElectronicGadgetsDetailsByType("WorkDesk");
        // Assert
        assertEquals(electronicGadgetsList, result);
        verify(electronicGadgetRepository, times(1)).findByGadgetType("WorkDesk");
    }
}
