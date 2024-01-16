package com.rentx.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name = "product")
public class ProductReal {
    /**
     * primary key product id
     */
    @Id
    @Column(name = "productID")
    private int productID;
    /**
     * string name for product
     */
    @Column(name = "name")
    private String name;
    /**
     * string description for product details
     */
    @Column(name = "description")
    private String description;
    /**
     * price for product
     */
    @Column(name = "price")
    private float price;
    /**
     * sell type of product
     */
    @Column(name = "sellType")
    private String sellType;
    /**
     * many to many relationship for category for category id
     */
    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;
    /**
     * many to many relationship for area for area id
     */
    @ManyToOne
    @JoinColumn(name = "areaID")
    private Area area;

    /**
     * transient for manufacture not meant to store in database
     */
    @Transient
    private String manufacture;
    /**
     * year of purchase for fetching data
     */
    @Transient
    private String year_of_purchase;
    /**
     * Same for Furniture and Kitchen Appliances
     */
    @Transient
    private String model_name;
    /**
     * gadget type field
     */
    @Transient
    private String gadget_type;
    /**
     * furniture type
     */
    @Transient
    private String furniture_type;
    /**
     * condition of furniture
     */
    @Transient
    private String condition;
    /**
     * author field
     */
    @Transient
    private String author;
    /**
     * year of publication of product
     */
    @Transient
    private String year_of_public;
    /**
     * appliance type
     */
    @Transient
    private String appliance_type;
    /**
     * berification status of product
     */
    @Transient
    private String verificationStatus;
    /**
     * advertisement title
     */
    @Transient
    private String advtTitle;
    /**
     * is active status for product
     */
    @Transient
    private String isActive;
    /**
     * post date for the advertisement
     */
    @Transient
    private Date postDate;
    /**
     * expiry for advertisement
     */
    @Transient
    private Date expiryDate;
}
