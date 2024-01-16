package com.rentx.controller;

import com.rentx.businessservices.interfaces.IAdvertisementService;
import com.rentx.dtos.AdvertisementDto;
import com.rentx.entities.Advertisement;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AdvertisementControllerTest {
    /**
     * advertisement Service class
     */
    IAdvertisementService advertisementService;
    /**
     * advertisement Dtos
     */
    List<AdvertisementDto> advertisementDtos;
    /**
     * Advertisement Controller
     */
    AdvertisementController advertisementController;

    /**
     * test method for Advertisement Controller
     */
    public AdvertisementControllerTest(){
        this.advertisementService = Mockito.mock(IAdvertisementService.class);
        advertisementController = new AdvertisementController((this.advertisementService));
    }

    /**
     * test case for the find all method
     */
    @Test
    void findAll(){
        advertisementDtos = new ArrayList<>();
        advertisementDtos.add(new AdvertisementDto());
        advertisementDtos.add(new AdvertisementDto());
        advertisementDtos.add(new AdvertisementDto());
        when(advertisementService.findAll()).thenReturn(advertisementDtos);
        var genAdvertisementDtos = advertisementController.findAll();
        assertEquals(genAdvertisementDtos.size(), advertisementDtos.size());
    }


    /**
     * test case for the get advertisement by user id
     */
    @Test
    public void testGetAdvertisementsByUserId() {
        // Create a mock for IAdvertisementService
        IAdvertisementService advertisementService = Mockito.mock(IAdvertisementService.class);

        // Create a test user and a list of advertisements
        User user = new User(); // Set up a user object as needed
        AdvertisementDto advertisement1 = new AdvertisementDto(); // Set up advertisements as needed
        AdvertisementDto advertisement2 = new AdvertisementDto();
        List<AdvertisementDto> expectedAdvertisements = Arrays.asList(advertisement1, advertisement2);

        // Mock the behavior of advertisementService.getAdvertisementsByUserId()
        when(advertisementService.getAdvertisementsByUserId(user)).thenReturn(expectedAdvertisements);

        // Create an instance of AdvertisementController with the mocked service
        AdvertisementController advertisementController = new AdvertisementController(advertisementService);

        // Call the endpoint
        List<AdvertisementDto> actualAdvertisements = advertisementController.getAdvertisementsByUserId(user);

        // Verify the result
        assertNotNull(actualAdvertisements);
        assertEquals(expectedAdvertisements.size(), actualAdvertisements.size());
        assertEquals(expectedAdvertisements.get(0), actualAdvertisements.get(0)); // Add more specific assertions if needed
        assertEquals(expectedAdvertisements.get(1), actualAdvertisements.get(1));

        // Verify that the advertisementService.getAdvertisementsByUserId() was called once with the correct user
        verify(advertisementService, times(1)).getAdvertisementsByUserId(user);
    }


    /**
     * test case to get advertisement from the product id
     */
    @Test
    public void testGetAdvertisementsByProductId() {
        // Create a mock for IAdvertisementService
        IAdvertisementService advertisementService = mock(IAdvertisementService.class);

        // Create a test product and a list of advertisements
        ProductReal product = new ProductReal(); // Set up a product object as needed
        AdvertisementDto advertisement1 = new AdvertisementDto(); // Set up advertisements as needed
        AdvertisementDto advertisement2 = new AdvertisementDto();
        List<AdvertisementDto> expectedAdvertisements = Arrays.asList(advertisement1, advertisement2);

        // Mock the behavior of advertisementService.getAdvertisementsByProductId()
        when(advertisementService.getAdvertisementsByProductId(product)).thenReturn(expectedAdvertisements);

        // Create an instance of AdvertisementController with the mocked service
        AdvertisementController advertisementController = new AdvertisementController(advertisementService);

        // Call the endpoint
        List<AdvertisementDto> actualAdvertisements = advertisementController.getAdvertisementsByProductId(product);

        // Verify the result
        assertNotNull(actualAdvertisements);
        assertEquals(expectedAdvertisements.size(), actualAdvertisements.size());
        assertEquals(expectedAdvertisements.get(0), actualAdvertisements.get(0)); // Add more specific assertions if needed
        assertEquals(expectedAdvertisements.get(1), actualAdvertisements.get(1));

        // Verify that the advertisementService.getAdvertisementsByProductId() was called once with the correct product
        verify(advertisementService, times(1)).getAdvertisementsByProductId(product);
    }


    /**
     * test case to get advertisement from the product id and user id
     */
    @Test
    public void testGetAdvertisementsByUserIdProductId() {
        // Create a mock for IAdvertisementService
        IAdvertisementService advertisementService = mock(IAdvertisementService.class);

        // Create test user, product, and a list of advertisements
        User user = new User(); // Set up a user object as needed
        ProductReal product = new ProductReal(); // Set up a product object as needed
        AdvertisementDto advertisement1 = new AdvertisementDto(); // Set up advertisements as needed
        AdvertisementDto advertisement2 = new AdvertisementDto();
        List<AdvertisementDto> expectedAdvertisements = Arrays.asList(advertisement1, advertisement2);

        // Mock the behavior of advertisementService.getAdvertisementsByUserIdProductId()
        when(advertisementService.getAdvertisementsByUserIdProductId(user, product)).thenReturn(expectedAdvertisements);

        // Create an instance of AdvertisementController with the mocked service
        AdvertisementController advertisementController = new AdvertisementController(advertisementService);

        // Call the endpoint
        List<AdvertisementDto> actualAdvertisements = advertisementController.getAdvertisementsByUserIdProductId(product, user);

        // Verify the result
        assertNotNull(actualAdvertisements);
        assertEquals(expectedAdvertisements.size(), actualAdvertisements.size());
        assertEquals(expectedAdvertisements.get(0), actualAdvertisements.get(0)); // Add more specific assertions if needed
        assertEquals(expectedAdvertisements.get(1), actualAdvertisements.get(1));

        // Verify that the advertisementService.getAdvertisementsByUserIdProductId() was called once with the correct user and product
        verify(advertisementService, times(1)).getAdvertisementsByUserIdProductId(user, product);
    }


    /**
     * test case to add advertisement
     */
    @Test
    public void testAddAdvertisement() {
        // Create a mock for IAdvertisementService
        IAdvertisementService advertisementService = mock(IAdvertisementService.class);

        // Create a test advertisement
        Advertisement advertisement = new Advertisement(); // Set up an Advertisement object as needed
        Advertisement savedAdvertisement = new Advertisement(); // Set up the saved Advertisement object as needed

        // Mock the behavior of advertisementService.addAdvertisement()
        when(advertisementService.addAdvertisement(advertisement)).thenReturn(savedAdvertisement);

        // Create an instance of AdvertisementController with the mocked service
        AdvertisementController advertisementController = new AdvertisementController(advertisementService);

        // Call the endpoint
        ResponseEntity<Advertisement> responseEntity = advertisementController.addProduct(advertisement);

        // Verify the result
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Advertisement returnedAdvertisement = responseEntity.getBody();
        assertNotNull(returnedAdvertisement);
        assertEquals(savedAdvertisement, returnedAdvertisement); // Add more specific assertions if needed

        // Verify that the advertisementService.addAdvertisement() was called once with the correct advertisement
        verify(advertisementService, times(1)).addAdvertisement(advertisement);
    }
}
