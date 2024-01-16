package com.rentx.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * product data transfer object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    /**
     * integer advertisement Id
     */
    private int advertisementId;
    /**
     * string advt title
     */
    private String advtTitle;
    /**
     * date post date
     */
    private Date postDate;
    /**
     * date expiry date
     */
    private Date expiryDate;
    /**
     * string user id
     */
    private String userId;
    /**
     * string phone number
     */
    private String phone;
    /**
     * string first name
     */
    private String firstName;
    /**
     * string last name
     */
    private String lastName;
    /**
     * strinf pref language
     */
    private String prefLanguage;
    /**
     * date joining date
     */
    private Date joiningDate;
    /**
     * string privacy
     */
    private String privacy;
    /**
     * string status
     */
    private String status;
    /**
     * string email
     */
    private String email;
    /**
     * integer product id
     */
    private int productID;
    /**
     * string product name
     */
    private String productName;
    /**
     * string product description
     */
    private String productDescription;
    /**
     * float price
     */
    private float price;
    /**
     * integer category id
     */
    private int categoryId;
    /**
     * strinf category name
     */
    private String categoryName;
    /**
     * integer area id
     */
    private int areaId;
    /**
     * string area name
     */
    private String areaName;
    /**
     * string city
     */
    private String city;
    /**
     * string state
     */
    private String state;
    /**
     * string country
     */
    private String country;
    /**
     * sell type 'rent' or 'sell'
     */
    private String sellType;
}