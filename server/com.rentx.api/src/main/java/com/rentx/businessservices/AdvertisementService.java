package com.rentx.businessservices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import lombok.RequiredArgsConstructor;

import com.rentx.businessservices.interfaces.IAdvertisementService;
import com.rentx.dataaccess.repository.AdvertisementRepository;
import com.rentx.dtos.AdvertisementDto;
import com.rentx.entities.Advertisement;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;
import com.rentx.mappers.AdvertisementMapper;

@RequiredArgsConstructor
@Service
public class AdvertisementService implements IAdvertisementService {
    /**
     * Advertisement Repository
     */
    private AdvertisementRepository advertisementRepository;

    /**
     * autowire Advertisement Service
     * @param advertisementRepository advertisement Repository
     */
    @Autowired
    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    /**
     * method to find all in advertisement
     * @return list of advertisemnt dto
     */
    @Override
    public List<AdvertisementDto> findAll() {
        return AdvertisementMapper.toAdvertisementsDto(advertisementRepository.findAll());
    }

    /**
     * method to get advertisements by user id
     * @param userID for user id
     * @return list of advertisement dto
     */
    @Override
    public List<AdvertisementDto> getAdvertisementsByUserId(User userID) {
         return AdvertisementMapper.toAdvertisementsDto(advertisementRepository.findByUserID(userID));
    }

    /**
     * method to get Advertisements By Product Id
     * @param productID for product id
     * @return list of advertisement by product id
     */
    @Override
    public List<AdvertisementDto> getAdvertisementsByProductId(ProductReal productID) {
         return AdvertisementMapper.toAdvertisementsDto(advertisementRepository.findByProductID(productID));
    }

    /**
     * method to get Advertisements By UserId and ProductId
     * @param userID for user id
     * @param productID for product id
     * @return list of advertisement based on user and product id
     */
    @Override
    public List<AdvertisementDto> getAdvertisementsByUserIdProductId(User userID, ProductReal productID) {
         return AdvertisementMapper.toAdvertisementsDto(advertisementRepository.findByUserIDAndProductID(userID, productID));
    }

    /**
     * method to add advertisement
     * @param advertisement for advertisment
     * @return advertisement Repository
     */
    @Override
    public Advertisement addAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    } 

}
