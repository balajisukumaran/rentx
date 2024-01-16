package com.rentx.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaDto {
    /**
     * Unique identifier for the area
     */
    private int areaID;
    /**
     * Name of the area
     */
    private String areaName;
    /**
     * City where the area is located
     */
    private String city;
    /**
     * State where the area is located
     */
    private String state;
    /**
     * Country where the area is located
     */
    private String country;
}
