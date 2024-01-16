package com.rentx.businessservices.interfaces;

import com.rentx.entities.Furniture;

import java.util.List;

public interface IFurnitureService {

    /**
     * method to get all furniture details
     * @return list of furniture details
     */
    List<Furniture> getAllFurnitureDetails();
    /**
     * method to add furniture
     * @param furniture furniture
     * @return furniture object
     */
    Furniture addFurniture(Furniture furniture);
    /**
     * method to get furniture details by ud
     * @param furnitureId for furniture id
     * @return object for furniture
     */
    Furniture getFurnitureDetailsById(int furnitureId);
    /**
     * method to get furniture details by id
     * @param furniture_type for string furniture type
     * @return list of furniture type
     */
    List<Furniture> getFurnitureDetailsByType(String furniture_type);
}
