package com.rentx.mappers;

import com.rentx.dtos.AreaDto;
import com.rentx.entities.Area;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AreaMapperTest {

    /**
     * test case for to area DTO with different aspects
     */
    @Test
    void toAreaDto() {
        Area area = new Area();
        area.setAreaID(123);
        area.setAreaName("name");
        area.setCity("city");
        area.setCountry("country");
        area.setState("state");

        AreaDto areaDto = AreaMapper.toAreaDto(area);
        assertEquals(area.getAreaID(),areaDto.getAreaID());
        assertEquals(area.getAreaName(),areaDto.getAreaName());
        assertEquals(area.getCity(),areaDto.getCity());
        assertEquals(area.getCountry(),areaDto.getCountry());
        assertEquals(area.getState(),areaDto.getState());
    }

    /**
     * test case for to area DTO in list
     */
    @Test
    void toAreaDtos() {
        List<Area> areas = new ArrayList<>();
        areas.add(new Area());
        areas.add(new Area());

        List<AreaDto> areaDtos = AreaMapper.toAreaDtos(areas);

        assertNotNull(areaDtos);
        assertEquals(2, areaDtos.size());
    }
}