package com.rentx.businessservices;

import com.rentx.businessservices.interfaces.IAdvertisementService;
import com.rentx.dataaccess.repository.AdvertisementRepository;
import com.rentx.dtos.AdvertisementDto;
import com.rentx.entities.Advertisement;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdvertisementServiceTest {
    private AdvertisementRepository advertisementRepository;
    private IAdvertisementService advertisementService;

    /**
     * setup method for advertisement repository and service initialisation
     */
    @BeforeEach
    public void setUp() {
        advertisementRepository = mock(AdvertisementRepository.class);
        advertisementService = new AdvertisementService(advertisementRepository);
    }


    /**
     * test case to find all product for advertisement
     */
    @Test
    public void testFindAll() {
        // Create a list of advertisements for testing
        List<Advertisement> expectedAdvertisements = new ArrayList<>();
        // Set up advertisements as needed

        // Mock the behavior of advertisementRepository.findAll()
        when(advertisementRepository.findAll()).thenReturn(expectedAdvertisements);

        // Call the service method
        List<AdvertisementDto> actualAdvertisements = advertisementService.findAll();

        // Verify the result
        assertNotNull(actualAdvertisements);
        assertEquals(expectedAdvertisements.size(), actualAdvertisements.size());
        // Add more specific assertions if needed
    }


    /**
     * test case to get advertisement based on user id
     */
    @Test
    public void testGetAdvertisementsByUserId() {
        User user = new User(); // Set up a user object as needed
        // Create a list of advertisements for testing
        List<Advertisement> expectedAdvertisements = new ArrayList<>();
        // Set up advertisements as needed

        // Mock the behavior of advertisementRepository.findByUserID()
        when(advertisementRepository.findByUserID(user)).thenReturn(expectedAdvertisements);

        // Call the service method
        List<AdvertisementDto> actualAdvertisements = advertisementService.getAdvertisementsByUserId(user);

        // Verify the result
        assertNotNull(actualAdvertisements);
        assertEquals(expectedAdvertisements.size(), actualAdvertisements.size());
        // Add more specific assertions if needed
    }


    /**
     * test case to get the advertisement by product id
     */
    @Test
    public void testGetAdvertisementsByProductId() {
        ProductReal product = new ProductReal(); // Set up a product object as needed
        // Create a list of advertisements for testing
        List<Advertisement> expectedAdvertisements = new ArrayList<>();
        // Set up advertisements as needed

        // Mock the behavior of advertisementRepository.findByProductID()
        when(advertisementRepository.findByProductID(product)).thenReturn(expectedAdvertisements);

        // Call the service method
        List<AdvertisementDto> actualAdvertisements = advertisementService.getAdvertisementsByProductId(product);

        // Verify the result
        assertNotNull(actualAdvertisements);
        assertEquals(expectedAdvertisements.size(), actualAdvertisements.size());
        // Add more specific assertions if needed
    }

    /**
     * test case to get advertisement base on userid and productid
     */
    @Test
    public void testGetAdvertisementsByUserIdProductId() {
        User user = new User(); // Set up a user object as needed
        ProductReal product = new ProductReal(); // Set up a product object as needed
        // Create a list of advertisements for testing
        List<Advertisement> expectedAdvertisements = new ArrayList<>();
        // Set up advertisements as needed

        // Mock the behavior of advertisementRepository.findByUserIDAndProductID()
        when(advertisementRepository.findByUserIDAndProductID(user, product)).thenReturn(expectedAdvertisements);

        // Call the service method
        List<AdvertisementDto> actualAdvertisements = advertisementService.getAdvertisementsByUserIdProductId(user, product);

        // Verify the result
        assertNotNull(actualAdvertisements);
        assertEquals(expectedAdvertisements.size(), actualAdvertisements.size());
        // Add more specific assertions if needed
    }

    /**
     * test case for the added advertisement
     */
    @Test
    public void testAddAdvertisement() {
        Advertisement advertisement = new Advertisement(); // Set up an Advertisement object as needed
        // Set up the saved Advertisement object as needed
        Advertisement savedAdvertisement = new Advertisement();

        // Mock the behavior of advertisementRepository.save()
        when(advertisementRepository.save(advertisement)).thenReturn(savedAdvertisement);

        // Call the service method
        Advertisement actualAdvertisement = advertisementService.addAdvertisement(advertisement);

        // Verify the result
        assertNotNull(actualAdvertisement);
        assertEquals(savedAdvertisement, actualAdvertisement);
    }
}
