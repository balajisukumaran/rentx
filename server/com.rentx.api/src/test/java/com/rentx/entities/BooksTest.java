package com.rentx.entities;

import com.rentx.entities.Books;
import com.rentx.entities.Category;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BooksTest {
    /**
     * a mock instance for category
     */
    @Mock
    private Category mockCategory;

    /**
     *  test case for Parameterized Constructor
     */
    @Test
    void testParameterizedConstructor() {
        //arrange and act
        Books books = new Books(1, "John Doe", "2022");

        //assert
        assertEquals(1, books.getBooksID());
        assertEquals("John Doe", books.getAuthor());
        assertEquals("2022", books.getYearOfPublished());
    }

    /**
     * test case for setter and getters for book
     */
    @Test
    void testSetterAndGetters() {
        //arrange
        Books books = new Books();

        //act
        books.setBooksID(2);
        books.setAuthor("Jane Doe");
        books.setYearOfPublished("2023");

        //assert
        assertEquals(2, books.getBooksID());
        assertEquals("Jane Doe", books.getAuthor());
        assertEquals("2023", books.getYearOfPublished());
    }

    /**
     * test case for many to one relationship
     */
    @Test
    void testManyToOneRelationship() {
        //arrange
        Books books = new Books();

        //act
        books.setCategoryID(mockCategory);

        //assert
        assertEquals(mockCategory, books.getCategoryID());
    }

    /**
     * test case for All Args Constructor
     */
    @Test
    void testAllArgsConstructor() {
        //arrange
        Books books = new Books(1, mockCategory, "Good", "John Doe", "2022");

        //assert
        assertEquals(1, books.getBooksID());
        assertEquals(mockCategory, books.getCategoryID());
        assertEquals("Good", books.getCondition());
        assertEquals("John Doe", books.getAuthor());
        assertEquals("2022", books.getYearOfPublished());
    }

    /**
     * test case for Specific Constructor
     */
    @Test
    void testSpecificConstructor() {
        //arrange and act
        Books books = new Books("John Doe", "2022");

        //assert
        assertEquals(0, books.getBooksID()); // Assuming 0 as default value for booksID
        assertEquals(null, books.getCategoryID());
        assertEquals(null, books.getCondition()); // Assuming null as default value for condition
        assertEquals("John Doe", books.getAuthor());
        assertEquals("2022", books.getYearOfPublished());
    }
}
