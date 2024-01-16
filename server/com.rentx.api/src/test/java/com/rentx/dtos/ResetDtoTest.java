package com.rentx.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ResetDtoTest
{
    @Test
    public void testResetDto() {
        ResetDto resetDto = ResetDto.builder().flag("testFlag").build();
        assertEquals("testFlag", resetDto.getFlag());
        ResetDto resetDto2 = ResetDto.builder().flag("testFlag").build();
        assertEquals(resetDto, resetDto2);
    }
}
