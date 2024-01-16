package com.rentx.businessservices;

import com.rentx.businessservices.interfaces.IFurnitureService;
import com.rentx.dataaccess.repository.FurnitureRepository;
import com.rentx.entities.Furniture;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FurnitureService implements IFurnitureService {
    /**
     * auto wire Furniture Repository
     */
    @Autowired
    private FurnitureRepository furnitureRepository;

    /**
     * method to get All Furniture Details
     *
     * @return result of furniture Repository
     */
    @Override
    public List<Furniture> getAllFurnitureDetails() {
        return furnitureRepository.findAll();
    }

    /**
     * method to add Furniture
     *
     * @param furniture furniture
     * @return updated furniture repository
     */
    @Override
    public Furniture addFurniture(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    /**
     * method get Furniture Details By Id
     *
     * @param furnitureId for furniture id
     * @return result from furniture Repository
     */
    @Override
    public Furniture getFurnitureDetailsById(int furnitureId) {

        Optional<Furniture> furniture = furnitureRepository.findById(furnitureId);

        if (furniture != null && furniture.isPresent())
            return furniture.get();

        throw new ResourceNotFoundException("Furniture Details with specific furnitureId not found" + furnitureId);
    }

    /**
     * method to get Furniture Details By Type
     *
     * @param furnitureType for string furniture type
     * @return
     */
    @Override
    public List<Furniture> getFurnitureDetailsByType(String furnitureType) {
        return furnitureRepository.findByFurnitureType(furnitureType);
    }
}
