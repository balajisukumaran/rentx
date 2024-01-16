package com.rentx.entities.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IdentifyByTest {
    /**
     * test case for enum values for email, login , resest token
     */
    @Test
    public void testEnumValues() {
        //arrange
        IdentifyBy[] values = IdentifyBy.values();

        //act and assert
        assertEquals(3, values.length);
        assertEquals(IdentifyBy.Email, values[0]);
        assertEquals(IdentifyBy.Login, values[1]);
        assertEquals(IdentifyBy.ResetToken, values[2]);
    }

    /**
     * test case to test enum values
     */
    @Test
    public void testEnumValueOf() {
        //arrange and act
        IdentifyBy email = IdentifyBy.valueOf("Email");
        IdentifyBy login = IdentifyBy.valueOf("Login");
        IdentifyBy resetToken = IdentifyBy.valueOf("ResetToken");

        //assert
        assertEquals(IdentifyBy.Email, email);
        assertEquals(IdentifyBy.Login, login);
        assertEquals(IdentifyBy.ResetToken, resetToken);
    }

    /**
     * test case for enum ordinal
     */
    @Test
    public void testEnumOrdinal() {
        //arrange and act/assert
        assertEquals(0, IdentifyBy.Email.ordinal());
        assertEquals(1, IdentifyBy.Login.ordinal());
        assertEquals(2, IdentifyBy.ResetToken.ordinal());
    }

    /**
     * test case for enum to string values
     */
    @Test
    public void testEnumToString() {
        //arrange and act/assert
        assertEquals("Email", IdentifyBy.Email.toString());
        assertEquals("Login", IdentifyBy.Login.toString());
        assertEquals("ResetToken", IdentifyBy.ResetToken.toString());
    }

    /**
     * test case enum switch cases
     */
    @Test
    public void testEnumSwitchCase() {
        //arrange
        IdentifyBy identifyBy = IdentifyBy.Login;

        //act
        String result = switch (identifyBy) {
            case Email -> "Email Case";
            case Login -> "Login Case";
            case ResetToken -> "ResetToken Case";
        };

        //assert
        assertEquals("Login Case", result);
    }
}
