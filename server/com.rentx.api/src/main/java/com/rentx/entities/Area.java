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
@Table(name="area")
public class Area {
    /**
     * primary key and auto generated key area id
     */
    @Id
    @Column(name="areaID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaID;
    /**
     * area name string field
     */
    @Column(name = "area_name")
    private String areaName;
    /**
     * city where product is on sale
     */
    @Column(name = "city")
    private String city;
    /**
     * state of city for the product
     */
    @Column(name = "state")
    private String state;
    /**
     * country of origin
     */
    @Column(name = "country")
    private String country;
}
