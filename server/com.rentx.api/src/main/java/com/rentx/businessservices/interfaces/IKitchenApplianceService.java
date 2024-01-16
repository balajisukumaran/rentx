package com.rentx.businessservices.interfaces;

import com.rentx.entities.KitchenAppliance;

import java.util.List;

public interface IKitchenApplianceService {
    /**
     * method to get all kitchen appliance details
     * @return list of kitchen appliance
     */
    List<KitchenAppliance> getAllKitchenApplianceDetails();
    /**
     * method to add kitchen appliance
     * @param kitchenAppliance for kitchen appliance
     * @return object for kitchen appliance
     */
    KitchenAppliance addKitchenAppliance(KitchenAppliance kitchenAppliance);
    /**
     * method to get kitchen appliance details bu id
     * @param kitchenApplianceID for kitchen appliance id
     * @return object for kitchen appliance
     */
    KitchenAppliance getKitchenApplianceDetailsById(int kitchenApplianceID);
    /**
     * method to get kitchen appliance details by type
     * @param appliance_type string applice type
     * @return list for kitchen appliance
     */
    List<KitchenAppliance> getKitchenApplianceDetailsByType(String appliance_type);
}
