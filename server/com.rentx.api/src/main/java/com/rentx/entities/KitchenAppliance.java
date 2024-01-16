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
@Table(name = "kitchen_appliances")
public class KitchenAppliance {
    /**
     * primary key for kitchen appliance id
     */
    @Id
    @Column(name = "kitchen_appliances_id")
    private int kitchenApplianceID;
    /**
     * manufacutrer field for product
     */
    @Column(name = "manufacturer")
    private String manufacturer;
    /**
     * year of purchase for product
     */
    @Column(name = "year_of_purchase")
    private String yearOfPurchase;
    /**
     * model name for appliance
     */
    @Column(name = "model_name")
    private String modelName;
    /**
     * appliance type
     */
    @Column(name = "appliance_type")
    private String applianceType;

    /**
     * many to many relationship for category to category id
     */
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category categoryID;
}
