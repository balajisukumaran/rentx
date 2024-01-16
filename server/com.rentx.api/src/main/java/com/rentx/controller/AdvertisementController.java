package com.rentx.controller;

import com.rentx.businessservices.interfaces.IAdvertisementService;
import com.rentx.dtos.AdvertisementDto;
import com.rentx.entities.Advertisement;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdvertisementController {
    private final IAdvertisementService advertisementService;

    /**
     * method for advertisement controller
     * @param advertisementService advertisement Service
     */
    public AdvertisementController(IAdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    /**
     * method for find all in advertisement dto
     * @return list of advertisement Service
     */
    @GetMapping("/advertisements")
    public List<AdvertisementDto> findAll(){
        return advertisementService.findAll();
    }

    /**
     * method to get Advertisements By UserId
     * @param userID user id
     * @return list of advertisement by user id from advertisement
     */
    @GetMapping("/advertisements/user")
    public List<AdvertisementDto> getAdvertisementsByUserId(@RequestParam User userID){
        return advertisementService.getAdvertisementsByUserId(userID);
    }

    /**
     * method to get Advertisements By ProductId
     * @param productID product id
     * @return list for Advertisements By ProductId
     */
    @GetMapping("/advertisements/product")
    public List<AdvertisementDto> getAdvertisementsByProductId(@ModelAttribute ProductReal productID){
        return advertisementService.getAdvertisementsByProductId(productID);
    }

    /**
     * method to get Advertisements By UserId and ProductId
     * @param productID productid
     * @param userID userid
     * @return list of advertisement Service
     */
    @GetMapping("/advertisements/userproduct")
    public List<AdvertisementDto> getAdvertisementsByUserIdProductId(@ModelAttribute ProductReal productID, @RequestParam User userID){
        return advertisementService.getAdvertisementsByUserIdProductId(userID, productID);
    }

    /**
     * method to add products
     * @param advertisement advertisement
     * @return entity for advertisement service
     */
    @PostMapping("/advertisement")
    public ResponseEntity<Advertisement> addProduct(@RequestBody Advertisement advertisement) {
        Advertisement savedAdvertisement = advertisementService.addAdvertisement(advertisement);
        return new ResponseEntity<>(savedAdvertisement, HttpStatus.CREATED);
    }
}