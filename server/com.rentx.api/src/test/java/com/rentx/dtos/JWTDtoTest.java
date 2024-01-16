package com.rentx.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class JWTDtoTest {
    /**
     * test case for the JWTD to record
     */
    @Test
    public void testJWTDtoRecord() {
        JWTDto jwtDto1 = new JWTDto("token123");
        JWTDto jwtDto2 = new JWTDto("token123");
        JWTDto jwtDto3 = new JWTDto("differentToken");
        assertEquals(jwtDto1, jwtDto2);
        assertNotEquals(jwtDto1, jwtDto3);
        assertEquals(jwtDto1.hashCode(), jwtDto2.hashCode());
        assertNotEquals(jwtDto1.hashCode(), jwtDto3.hashCode());
        assertEquals("JWTDto[JWT=token123]", jwtDto1.toString());
    }
}
