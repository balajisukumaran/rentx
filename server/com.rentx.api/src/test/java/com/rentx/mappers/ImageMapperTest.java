package com.rentx.mappers;

import com.rentx.dtos.ImageDto;
import com.rentx.entities.Image;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageMapperTest {


    /**
     * test case to image DTO
     */
    @Test
    void toImageDto() {
        Image image = new Image();
        image.setImageId(123);
        image.setId(123);
        image.setName("name");
        image.setImageType("type");

        ImageDto imageDto = ImageMapper.toImageDto(image);
        assertEquals(image.getImageId(),imageDto.getImageId());
        assertEquals(image.getId(),imageDto.getId());
        assertEquals(image.getName(),imageDto.getName());
        assertEquals(image.getImageType(),imageDto.getImageType());
    }

    /**
     * test case for the image DTO
     */
    @Test
    void toImageDtos() {
        List<Image> images = new ArrayList<>();
        images.add(new Image());
        images.add(new Image());

        List<ImageDto> ImageDtos = ImageMapper.toImageDtos(images);

        assertNotNull(ImageDtos);
        assertEquals(2, ImageDtos.size());
    }
}