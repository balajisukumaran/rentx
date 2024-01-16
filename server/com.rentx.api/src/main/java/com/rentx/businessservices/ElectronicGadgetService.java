package com.rentx.businessservices;

import com.rentx.businessservices.interfaces.IElectronicGadgetsService;
import com.rentx.dataaccess.repository.ElectronicGadgetRepository;
import com.rentx.entities.Books;
import com.rentx.entities.ElectronicGadget;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ElectronicGadgetService implements IElectronicGadgetsService {
    /**
     * autowire ElectronicGadget Repository
     */
    @Autowired
    private ElectronicGadgetRepository electronicGadgetRepository;

    /**
     * method to get All ElectronicGadgets Details
     *
     * @return list of ElectronicGadgetsDetails
     */
    @Override
    public List<ElectronicGadget> getAllElectronicGadgetsDetails() {
        return electronicGadgetRepository.findAll();
    }

    /**
     * method to add ElectronicGadgets
     *
     * @param electronicGadget for electronic gadget
     * @return ElectronicGadgets repository
     */
    @Override
    public ElectronicGadget addElectronicGadgets(ElectronicGadget electronicGadget) {
        return electronicGadgetRepository.save(electronicGadget);
    }

    /**
     * method to get ElectronicGadgets Details By Id
     *
     * @param electronicGadgetId int electronic gadget id
     * @return result of find by id of electronic gadget
     */
    @Override
    public ElectronicGadget getElectronicGadgetsDetailsById(int electronicGadgetId) {

        Optional<ElectronicGadget> electronicGadget = electronicGadgetRepository.findById(electronicGadgetId);

        if (electronicGadget != null && electronicGadget.isPresent())
            return electronicGadget.get();

        throw new ResourceNotFoundException("electronicGadget Details with specific electronicGadgetId not found" + electronicGadgetId);
    }

    /**
     * method to get ElectronicGadgets Details By Type
     *
     * @param electronicGadgetType for furniture type string
     * @return electronicGadget Repository
     */
    @Override
    public List<ElectronicGadget> getElectronicGadgetsDetailsByType(String electronicGadgetType) {
        return electronicGadgetRepository.findByGadgetType(electronicGadgetType);
    }
}
