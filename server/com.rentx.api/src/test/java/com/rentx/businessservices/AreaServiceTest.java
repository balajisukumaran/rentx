package com.rentx.businessservices;

import com.rentx.dataaccess.repository.AreaRepository;
import com.rentx.dtos.AreaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.rentx.entities.Area;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.*;

class AreaServiceTest {
    /**
     * mock injection
     */
    @InjectMocks
    AreaService areaService;
    /**
     * mock data for area repository
     */
    @Mock
    AreaRepository areaRepository;

    /**
     * before each to setup the mocks
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        areaService = new AreaService();
        areaService.setAreaRepository(areaRepository);
    }

    /**
     * test case to find all data in area
     */
    @Test
    void findAll() {
        List<Area> areas = new ArrayList<>();
        areas.add(new Area());
        areas.add(new Area());

        when(areaRepository.findAll()).thenReturn(areas);

        List<AreaDto> areaDtos = areaService.findAll();

        assertEquals(areas.size(), areaDtos.size());
    }
}