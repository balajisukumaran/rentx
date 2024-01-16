package com.rentx.businessservices;

import com.rentx.businessservices.interfaces.IKitchenApplianceService;
import com.rentx.dataaccess.repository.KitchenApplianceRepository;
import com.rentx.entities.KitchenAppliance;
import lombok.RequiredArgsConstructor;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KitchenApplianceService implements IKitchenApplianceService {
    /**
     * autowire Kitchen Appliance Repository
     */
    @Autowired
    private KitchenApplianceRepository kitchenApplianceRepository;

    /**
     * method to get All Kitchen Appliance Details
     *
     * @return result from kitchen Appliance Repository
     */
    @Override
    public List<KitchenAppliance> getAllKitchenApplianceDetails() {
        return kitchenApplianceRepository.findAll();
    }

    /**
     * method to add Kitchen Appliance
     *
     * @param kitchenAppliance for kitchen appliance
     * @return udpated kitchen Appliance Repository
     */
    @Override
    public KitchenAppliance addKitchenAppliance(KitchenAppliance kitchenAppliance) {
        return kitchenApplianceRepository.save(kitchenAppliance);
    }

    /**
     * method get Kitchen Appliance Details By Id
     *
     * @param kitchenApplianceID for kitchen appliance id
     * @return reult by id from kitchen Appliance Repository
     */
    @Override
    public KitchenAppliance getKitchenApplianceDetailsById(int kitchenApplianceID) {

        Optional<KitchenAppliance> kitchenAppliance = kitchenApplianceRepository.findById(kitchenApplianceID);

        if (kitchenAppliance != null && kitchenAppliance.isPresent())
            return kitchenAppliance.get();

        throw new ResourceNotFoundException("Kitchen Appliance Details with specific Kitchen Appliance ID not found" + kitchenApplianceID);

    }

    /**
     * method to get Kitchen Appliance Details By Type
     *
     * @param appliance_type string applice type
     * @return type of appliance type
     */
    @Override
    public List<KitchenAppliance> getKitchenApplianceDetailsByType(String appliance_type) {
        return kitchenApplianceRepository.findByApplianceType(appliance_type);
    }
}
