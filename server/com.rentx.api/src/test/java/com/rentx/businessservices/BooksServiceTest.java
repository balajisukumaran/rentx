package com.rentx.businessservices;


import com.rentx.dataaccess.repository.BooksRepository;
import com.rentx.entities.Books;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BooksServiceTest {
    /**
     * mock for books repository
     */
    @Mock
    private BooksRepository booksRepository;
    /**
     * inject mock for books service
     */
    @InjectMocks
    private BooksService booksService;

    /**
     * before each to setup the mock
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test case to get all books details
     */
    @Test
    public void testGetAllBooksDetails() {
        List<Books> booksList = new ArrayList<>();
        booksList.add(new Books("Tammy", "2001"));
        booksList.add(new Books("Sherley", "1998"));
        booksList.add(new Books("Erick", "2013"));

        when(booksRepository.findAll()).thenReturn(booksList);

        List<Books> result = booksService.getAllBooksDetails();

        assertEquals(3, result.size());
        assertEquals("Tammy", result.get(0).getAuthor());
        assertEquals("Sherley", result.get(1).getAuthor());
        assertEquals("Erick", result.get(2).getAuthor());

        verify(booksRepository, times(1)).findAll();
    }

    /**
     * test case to test added books in database with test data
     */
    @Test
    public void testAddBook() {
        Books book = new Books("Jonathan Dave", "2014");

        when(booksRepository.save(book)).thenReturn(book);

        Books result = booksService.addBook(book);

        assertEquals("Jonathan Dave", result.getAuthor());
        assertEquals("2014", result.getYearOfPublished());

        verify(booksRepository, times(1)).save(book);
    }

    /**
     * test case to get the book details by id with mock and test data
     */
    @Test
    public void testGetBookDetailsById() {
        Books book = new Books(3, "Jane Doe", "2020");

        when(booksRepository.findById(3)).thenReturn(Optional.of(book));

        Books result = booksService.getBookDetailsById(3);

        assertEquals(3, result.getBooksID());
        assertEquals("Jane Doe", result.getAuthor());
        assertEquals("2020", result.getYearOfPublished());

        verify(booksRepository, times(1)).findById(3);
    }

    /**
     * test case for the books details by author with test data
     */
    @Test
    public void testGetBooksDetailsByAuthor() {
        List<Books> booksList = new ArrayList<>();
        booksList.add(new Books("Amber", "2021"));
        booksList.add(new Books("Wes", "2023"));

        when(booksRepository.findByAuthor("Wes")).thenReturn(booksList);

        List<Books> result = booksService.getBooksDetailsByAuthor("Wes");

        assertEquals(2, result.size());
        assertEquals("Wes", result.get(1).getAuthor());
        assertEquals("2023", result.get(1).getYearOfPublished());

        verify(booksRepository, times(1)).findByAuthor("Wes");
    }
}