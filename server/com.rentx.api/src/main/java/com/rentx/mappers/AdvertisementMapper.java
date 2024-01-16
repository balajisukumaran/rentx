package com.rentx.mappers;

import com.rentx.dtos.AdvertisementDto;
import com.rentx.entities.Advertisement;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementMapper {
    /**
     * static method tp advertisement dto
     * @param advertisement advertisement
     * @return updated advertisement dto
     */
    public static AdvertisementDto toAdvertisementsDto(Advertisement advertisement){
        AdvertisementDto advertisementDto = new AdvertisementDto();

        advertisementDto.setAdvertisementID(advertisement.getAdvertisementID());
        advertisementDto.setVerificationStatus(advertisement.getVerificationStatus());
        advertisementDto.setAdvtTitle(advertisement.getAdvtTitle());
        advertisementDto.setIsActive(advertisement.getIsActive());
        advertisementDto.setPostDate(advertisement.getPostDate());
        advertisementDto.setExpiryDate(advertisement.getExpiryDate());
        advertisementDto.setUser(advertisement.getUserID());
        advertisementDto.setProductReal(advertisement.getProductID());        
        return advertisementDto;
    }

    /**
     * method for to advertisement dto
     * @param advertisements advertisements
     * @return object advertisement dto
     */
    public static List<AdvertisementDto> toAdvertisementsDto(List<Advertisement> advertisements){
        List<AdvertisementDto> advertisementsDto = new ArrayList<>();

        for (var advertisement: advertisements)
            advertisementsDto.add(toAdvertisementsDto(advertisement));
        return advertisementsDto;
    }
}
