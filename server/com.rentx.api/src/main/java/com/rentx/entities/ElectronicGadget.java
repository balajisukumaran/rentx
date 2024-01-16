package com.rentx.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "electronic_gadgets")
public class ElectronicGadget {

    /**
     * primary key of electronic Gadgets ID
     */
    @Id
    @Column(name = "electronic_id")
    private int electronicGadgetsID;

    /**
     * manufacturer for electronic gadget
     */
    @Column(name = "manufacturer")
    private String manufacturer;

    /**
     * year of purchase for gadget
     */
    @Column(name = "year_of_purchase")
    private String yearOfPurchase;

    /**
     * string model name
     */
    @Column(name = "model_name")
    private String modelName;

    /**
     * gadget type
     */
    @Column(name = "Gadget_Type")
    private String gadgetType;

    /**
     * many to many relationship with category for category id
     */
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category categoryID;
}
