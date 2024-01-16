package com.rentx.dataaccess.repository;

import com.rentx.entities.KitchenAppliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenApplianceRepository extends JpaRepository<KitchenAppliance, Integer> {
    /**
     * method to find by appliance type
     * @param gadgetType gadget Type
     * @return list of kitchen appliance
     */
    List<KitchenAppliance> findByApplianceType(String gadgetType);

    /**
     * method to find by kitche  appliance
     * @param id id
     * @return object kitchen appliance
     */
    KitchenAppliance findByKitchenApplianceID(int id);

    /**
     * method to delete by kitchen appliance id
     * @param kitchenApplianceId kitchenApplianceId
     */
    void deleteByKitchenApplianceID(int kitchenApplianceId);
}
