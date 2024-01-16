package com.rentx.controller;

import com.rentx.businessservices.interfaces.IAreaService;
import com.rentx.dtos.AreaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AreaControllerTest {
    /**
     * a mock for Area Service
     */
    @Mock
    private IAreaService AreaService;
    /**
     * area controller
     */
    private AreaController AreaController;

    /**
     * before each method for all test case
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        AreaController = new AreaController(AreaService);
    }

    /**
     * test case to find all in area list
     */
    @Test
    void findAll() {
        List<AreaDto> AreaList = new ArrayList<>();

        when(AreaService.findAll()).thenReturn(AreaList);

        List<AreaDto> result = AreaController.findAll();

        assertEquals(AreaList, result);
    }
}
