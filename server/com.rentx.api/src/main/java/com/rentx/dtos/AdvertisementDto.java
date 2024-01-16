package com.rentx.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.rentx.entities.ProductReal;
import com.rentx.entities.User;

/**
 * advertisement data transfer object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertisementDto {
    /**
     * Unique identifier for the advertisement
     */
    private int advertisementID;
    /**
     * Status indicating whether the advertisement has been verified
     */
    private String verificationStatus;
    /**
     * Title of the advertisement
     */
    private String advtTitle;
    /**
     * Status indicating whether the advertisement is currently active
     */
    private String isActive;
    /**
     * Date when the advertisement was posted
     */
    private Date postDate;
    /**
     *  Date when the advertisement will expire
     */
    private Date expiryDate;
    /**
     * User associated with the advertisement
     */
    private User user;
    /**
     *  Real product information associated with the advertisement
     */
    private ProductReal productReal;
}