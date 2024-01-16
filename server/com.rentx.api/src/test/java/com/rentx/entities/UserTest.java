package com.rentx.entities;

import com.rentx.entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class UserTest {
    /**
     * test case for no args constructor for user
     */
    @Test
    public void testNoArgsConstructor() {
        // arrange
        User user = new User();

        // act
        assertNotNull(user);

        // assert
        assertEquals(0, user.getUserID());
        assertNull(user.getPhone());
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getPrefLanguage());
        assertNull(user.getJoiningDate());
        assertNull(user.getPrivacy());
        assertNull(user.getStatus());
        assertEquals(0, user.getAreaId());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getResetToken());
        assertNull(user.getEmail());
        assertEquals(0, user.getIsExists());
    }

    /**
     * test case for all args constructor
     */
    @Test
    public void testAllArgsConstructor() {
        // arrange
        Date joiningDate = new Date();

        // act
        User user = new User(1, "7548895642", "Parth", "Singh", "English", joiningDate,
                "private", "active", 1, "parth_singh", "password123", "reset123", "parth.singh@gmail.com", 1);

        // assert
        assertNotNull(user);
        assertEquals(1, user.getUserID());
        assertEquals("7548895642", user.getPhone());
        assertEquals("Parth", user.getFirstName());
        assertEquals("Singh", user.getLastName());
        assertEquals("English", user.getPrefLanguage());
        assertEquals(joiningDate, user.getJoiningDate());
        assertEquals("private", user.getPrivacy());
        assertEquals("active", user.getStatus());
        assertEquals(1, user.getAreaId());
        assertEquals("parth_singh", user.getLogin());
        assertEquals("password123", user.getPassword());
        assertEquals("reset123", user.getResetToken());
        assertEquals("parth.singh@gmail.com", user.getEmail());
        assertEquals(1, user.getIsExists());
    }

    /**
     * test cases for getter and setter methods
     */
    @Test
    public void testGettersAndSetters() {
        // arrange
        User user = new User();
        user.setUserID(1);
        user.setPhone("7548895642");
        user.setFirstName("Parth");
        user.setLastName("Singh");
        user.setPrefLanguage("English");
        Date joiningDate = new Date();
        user.setJoiningDate(joiningDate);
        user.setPrivacy("private");
        user.setStatus("active");
        user.setAreaId(1);
        user.setLogin("parth_singh");
        user.setPassword("password123");
        user.setResetToken("reset123");
        user.setEmail("parth.singh@gmail.com");
        user.setIsExists(1);

        // act and assert
        assertEquals(1, user.getUserID());
        assertEquals("7548895642", user.getPhone());
        assertEquals("Parth", user.getFirstName());
        assertEquals("Singh", user.getLastName());
        assertEquals("English", user.getPrefLanguage());
        assertEquals(joiningDate, user.getJoiningDate());
        assertEquals("private", user.getPrivacy());
        assertEquals("active", user.getStatus());
        assertEquals(1, user.getAreaId());
        assertEquals("parth_singh", user.getLogin());
        assertEquals("password123", user.getPassword());
        assertEquals("reset123", user.getResetToken());
        assertEquals("parth.singh@gmail.com", user.getEmail());
        assertEquals(1, user.getIsExists());
    }

    /**
     * test case for not equals
     */
    @Test
    public void testNotEquals() {
        // arrange
        User user1 = new User(1, "7548895642", "Parth", "Singh", "English", new Date(),
                "private", "active", 1, "parth_singh", "password123", "reset123", "parth.singh@gmail.com", 1);

        User user2 = new User(2, "7845563214", "Jane", "Smith", "French", new Date(),
                "public", "inactive", 2, "jane_smith", "password456", "reset456", "jane@gmail.com", 0);

        // act and assert
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
}
