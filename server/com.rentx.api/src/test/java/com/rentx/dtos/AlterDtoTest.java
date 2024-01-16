package com.rentx.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AlterDtoTest {
    /**
     * test case for records in alter dto
     */
    @Test
    public void testRecord() {
        AlertDto alert1 = new AlertDto("Success", "Operation successful");
        AlertDto alert2 = new AlertDto("Success", "Operation successful");
        AlertDto alert3 = new AlertDto("Error", "Operation failed");
        assertEquals(alert1, alert2);
        assertNotEquals(alert1, alert3);
        assertEquals(alert1.hashCode(), alert2.hashCode());
        assertNotEquals(alert1.hashCode(), alert3.hashCode());
        assertEquals("AlertDto[type=Success, message=Operation successful]", alert1.toString());
    }
}
