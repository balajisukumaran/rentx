package com.rentx.mappers;

import com.rentx.dtos.ImageDto;
import com.rentx.entities.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageMapper {
    /**
     * static method for image dto
     * @param image image
     * @return updated image dto
     */
    public static ImageDto toImageDto(Image image){
        ImageDto imageDto = new ImageDto();
        imageDto.setImageType(image.getImageType());
        imageDto.setImageId(image.getImageId());
        imageDto.setId(image.getId());
        imageDto.setName(image.getName());
        return imageDto;
    }

    /**
     * method to check all image in image dto
     * @param images images
     * @return image dto object
     */
    public static List<ImageDto> toImageDtos(List<Image> images){
        List<ImageDto> imageDtos = new ArrayList<>();

        for (var image: images)
            imageDtos.add(toImageDto(image));

        return imageDtos;
    }
}