package com.rentx.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

/**
 * product entity class
 */
@Table
@Getter
@Setter
@Entity
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name="getAllAvailableProducts",procedureName = "get_available_products", resultClasses = { Product.class }),
                                @NamedStoredProcedureQuery(name = "getAllAvailableProduct", procedureName = "get_available_product", resultClasses = { Product.class }, parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "sp_product_id", type = Integer.class)})})
public class Product implements Serializable {
    /**
     * primary key for advertisement id
     */
    private int advertisement_id;
    /**
     * advertisement title
     */
    private String advt_title;
    /**
     * post date for advertisement
     */
    private Date post_date;
    /**
     * expiry date of advertisement
     */
    private Date expiry_date;
    /**
     * user id of user
     */
    private String userID;
    /**
     * phone number of user
     */
    private String phone;
    /**
     * first name of user
     */
    private String first_name;
    /**
     * last name of user
     */
    private String last_name;
    /**
     * preffered language of user
     */
    private String pref_language;
    /**
     * joining date of user
     */
    private Date joining_date;
    /**
     * privacy field
     */
    private String privacy;
    /**
     * status for user
     */
    private String status;
    /**
     * email address field
     */
    private String email;
    /**
     * product id
     */
    @Id
    private int productID;
    /**
     * product name field
     */
    private String product_name;
    /**
     * product description field
     */
    private String product_description;
    /**
     * price field to store price
     */
    private float Price;
    /**
     * categiry id
     */
    private int category_id;
    /**
     * category name
     */
    private String category_name;
    /**
     * area id
     */
    private int areaID;
    /**
     * area name field
     */
    private String area_name;
    /**
     * city name
     */
    private String city;
    /**
     * state where the advertisement appeared
     */
    private String state;
    /**
     * county for user
     */
    private String country;

    private String sellType;
}
