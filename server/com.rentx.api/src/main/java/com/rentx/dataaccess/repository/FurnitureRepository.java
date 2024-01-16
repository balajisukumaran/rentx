package com.rentx.dataaccess.repository;

import com.rentx.entities.Furniture;
import com.rentx.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    /**
     * method to find by furniture type
     * @param furnitureType furniture Type
     * @return list of furniture
     */
    List<Furniture> findByFurnitureType(String furnitureType);

    /**
     * method for find furniture by furniture id
     * @param furnitureId furnitur eId
     * @return object furniture
     */
    Furniture findFurnitureByFurnitureID(int furnitureId);

    /**
     * method for delete by furniture id
     * @param furnitureId furniture Id
     */
    void deleteByFurnitureID(int furnitureId);
}
