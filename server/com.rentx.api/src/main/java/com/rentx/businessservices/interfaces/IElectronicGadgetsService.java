package com.rentx.businessservices.interfaces;

import com.rentx.entities.ElectronicGadget;

import java.util.List;

public interface IElectronicGadgetsService {

    /**
     * method to get all electronic gadgets details
     * @return list of electronic gadget
     */
    List<ElectronicGadget> getAllElectronicGadgetsDetails();
    /**
     * method to add electronic gadgets
     * @param electronicGadget for electronic gadget
     * @return object for electronic gadget
     */
    ElectronicGadget addElectronicGadgets(ElectronicGadget electronicGadget);
    /**
     * method to get electronic gadgets details by id
     * @param electronicGadgetID int electronic gadget id
     * @return object of electromic gadget
     */
    ElectronicGadget getElectronicGadgetsDetailsById(int electronicGadgetID);

    /**
     * method to get electronuc gadget details by type
     * @param furniture_type for furniture type string
     * @return list for electronic gadget
     */
    List<ElectronicGadget> getElectronicGadgetsDetailsByType(String furniture_type);
}
