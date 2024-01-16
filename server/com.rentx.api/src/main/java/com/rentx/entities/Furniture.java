package com.rentx.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="furniture")
public class Furniture {
    /**
     * primary key of furniture
     */
    @Id
    @Column(name="furnitureID")
    private int furnitureID;
    /**
     * furniture type
     */
    @Column(name="Furniture_Type")
    private String furnitureType;
    /**
     * many to many relationship to category for category id
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="cat_id", referencedColumnName = "category_id")
    private Category categoryID;
    /**
     * furniture condition
     */
    @Column(name="furniture_condition")
    private String furnitureCondition;
    /**
     * manufacturer field for furniture
     */
    @Column(name="manufacturer")
    private String manufacturer;
    /**
     * year of purchase for furniture product
     */
    @Column(name="Year_of_Purchase")
    private String yearOfPurchase;

    /**
     * furniture method
     * @param furnitureType object
     * @param manufacturer object
     */
    public Furniture(String furnitureType, String manufacturer) {
        this.furnitureType = furnitureType;
        this.manufacturer = manufacturer;
    }

    /**
     * furniture method
     * @param furnitureID object
     * @param furnitureType object
     * @param manufacturer object
     */
    public Furniture(int furnitureID, String furnitureType, String manufacturer) {
        this.furnitureID = furnitureID;
        this.furnitureType = furnitureType;
        this.manufacturer = manufacturer;
    }

}
