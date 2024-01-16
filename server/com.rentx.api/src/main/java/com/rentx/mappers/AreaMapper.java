package com.rentx.mappers;

import com.rentx.dtos.AreaDto;
import com.rentx.entities.Area;

import java.util.ArrayList;
import java.util.List;

public class AreaMapper {
    /**
     * static method to area dto
     * @param area area
     * @return updated area dto
     */
    public static AreaDto toAreaDto(Area area){
        AreaDto areaDto = new AreaDto();

        areaDto.setAreaID(area.getAreaID());
        areaDto.setAreaName(area.getAreaName());
        areaDto.setState(area.getState());
        areaDto.setCity(area.getCity());
        areaDto.setCountry(area.getCountry());
        return areaDto;
    }

    /**
     * static method to area dtods
     * @param areas areas
     * @return area dtos
     */
    public static List<AreaDto> toAreaDtos(List<Area> areas){
        List<AreaDto> areaDtos = new ArrayList<>();

        for (var area: areas)
            areaDtos.add(toAreaDto(area));

        return areaDtos;
    }
}
