package com.rentx.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentx.entities.ElectronicGadget;

import java.util.List;

@Repository
public interface ElectronicGadgetRepository extends JpaRepository<ElectronicGadget, Integer> {
    /**
     * method to find by gadget type
     * @param gadgetType gadgetType
     * @return list of  Electronic Gadget
     */
    List<ElectronicGadget> findByGadgetType(String gadgetType);

    /**
     * method to find by electronic gadgets id
     * @param electronicGadget  electronic Gadget
     * @return object of electronic gadget
     */
    ElectronicGadget findByElectronicGadgetsID(int electronicGadget);

    /**
     * method to delete by electronic gadgets id
     * @param electronicGadgetId electronic Gadget Id
     */
    void deleteByElectronicGadgetsID(int electronicGadgetId);
}
