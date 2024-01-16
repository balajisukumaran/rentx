package com.rentx.businessservices.interfaces;

import com.rentx.dtos.AdvertisementDto;
import com.rentx.entities.Advertisement;
import com.rentx.entities.ProductReal;
import com.rentx.entities.User;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IAdvertisementService {

    /**
     * method for the find all
     * @return list of the find all
     */
    List<AdvertisementDto> findAll();

    /**
     * method for the get advertisements by user id
     * @param userID for user id
     * @return list of advertisement dto
     */
    List<AdvertisementDto> getAdvertisementsByUserId(User userID);

    /**
     * method for the get advertisements by product id
     * @param productID for product id
     * @return list of advertisement dto
     */
    List<AdvertisementDto> getAdvertisementsByProductId(ProductReal productID);

    /**
     * method for advertisements by userid and product id
     * @param userID for user id
     * @param productID for product id
     * @return list of advertisement
     */
    List<AdvertisementDto> getAdvertisementsByUserIdProductId(User userID, ProductReal productID);

    /**
     * method foor add advertisement
     * @param advertisement for advertisment
     * @return advertisement
     */
    Advertisement addAdvertisement(Advertisement advertisement);
}