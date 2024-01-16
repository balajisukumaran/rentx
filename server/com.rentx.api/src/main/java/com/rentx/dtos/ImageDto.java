package com.rentx.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {
    /**
     * provate integer image id
     */
    private int imageId;
    /**
     * private integer id
     */
    private Integer id;
    /**
     * string name
     */
    private String name;
    /**
     * string image type
     */
    private String imageType;
}