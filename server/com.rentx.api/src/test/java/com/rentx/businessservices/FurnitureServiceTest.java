package com.rentx.businessservices;


import com.rentx.dataaccess.repository.FurnitureRepository;
import com.rentx.entities.Furniture;
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

public class FurnitureServiceTest {
    /**
     *  a mock instance of furniture Repository
     */
    @Mock
    private FurnitureRepository furnitureRepository;
    /**
     * Injecting the mock into furniture service
     */
    @InjectMocks
    private FurnitureService furnitureService;

    /**
     * Initializing mocks before each test method
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test case to get all furniture details and assert them with test data
     */
    @Test
    public void testGetAllFurnitureDetails() {
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(new Furniture(1, "Table", "Wooden"));
        furnitureList.add(new Furniture(2, "Chair", "Metal"));
        when(furnitureRepository.findAll()).thenReturn(furnitureList);

        List<Furniture> result = furnitureService.getAllFurnitureDetails();

        assertEquals(furnitureList, result);
        verify(furnitureRepository, times(1)).findAll();
    }

    /**
     * test case to add furniture
     */
    @Test
    public void testAddFurniture() {
        Furniture furniture = new Furniture(7, "Rocking Chair", "Ramesh Furnishings");
        when(furnitureRepository.save(furniture)).thenReturn(furniture);

        Furniture result = furnitureService.addFurniture(furniture);

        assertEquals(furniture, result);
        verify(furnitureRepository, times(1)).save(furniture);
    }

    /**
     * test case to get furniture detaails by id and assert and verify them with furniture repository
     */
    @Test
    public void testGetFurnitureDetailsById() {
        Furniture furniture = new Furniture(3, "Dining Table", "Wooden Things");
        when(furnitureRepository.findById(3)).thenReturn(Optional.of(furniture));

        Furniture result = furnitureService.getFurnitureDetailsById(3);

        assertEquals(furniture, result);
        verify(furnitureRepository, times(1)).findById(3);
    }

    /**
     * test case to get furniture details by id not found to check the exception in code
     */
    @Test
    public void testGetFurnitureDetailsByIdNotFound() {
        when(furnitureRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> furnitureService.getFurnitureDetailsById(1));
        verify(furnitureRepository, times(1)).findById(1);
    }

    /**
     * test case to get furniture details by type
     */
    @Test
    public void testGetFurnitureDetailsByType() {
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(new Furniture(13, "WorkDesk", "Wooden Things"));
        furnitureList.add(new Furniture(14, "BedFrames", "One stop Furniture"));
        when(furnitureRepository.findByFurnitureType("WorkDesk")).thenReturn(furnitureList);

        List<Furniture> result = furnitureService.getFurnitureDetailsByType("WorkDesk");

        assertEquals(furnitureList, result);
        verify(furnitureRepository, times(1)).findByFurnitureType("WorkDesk");
    }
}