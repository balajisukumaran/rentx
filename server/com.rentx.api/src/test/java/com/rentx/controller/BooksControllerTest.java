package com.rentx.controller;

import com.rentx.businessservices.BooksService;
import com.rentx.businessservices.FurnitureService;
import com.rentx.entities.Books;
import com.rentx.entities.Furniture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;


public class BooksControllerTest {
    /**
     * mock injected to Books Controller
     */
    @InjectMocks
    private BooksController booksController;
    /**
     * a mock initiated for books service
     */
    @Mock
    private BooksService booksService;

    /**
     * before each mock to each test case
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        booksController = new BooksController(this.booksService);
    }

    /**
     * test case to get all books category details with test data
     */
    @Test
    public void testGetAllBooksCategoryDetails() {
        List<Books> booksList = new ArrayList<>();
        booksList.add(new Books("Sameer Jain", "2019"));
        when(booksService.getAllBooksDetails()).thenReturn(booksList);

        List<Books> result = booksController.getAllBooksCategoryDetails();

        assertEquals(booksList, result);
        verify(booksService, times(1)).getAllBooksDetails();
    }

    /**
     * test case to add books product details with assert and verify
     */
    @Test
    public void testAddBooksProductDetails() {
        Books books = new Books("Ricky Jonathan", "2023");
        when(booksService.addBook(books)).thenReturn(books);

        Books result = booksController.addBookProductDetails(books);

        assertEquals(books, result);
        verify(booksService, times(1)).addBook(books);
    }

    /**
     * test case to get books product details by id
     */
    @Test
    public void testGetBooksProductDetailsById() {
        Books books = new Books("", "Ramesh Furnitures");
        books.setBooksID(3);
        when(booksService.getBookDetailsById(3)).thenReturn(books);

        ResponseEntity<Books> result = booksController.getBookProductDetailsById(3);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(books, result.getBody());
        verify(booksService, times(1)).getBookDetailsById(3);
    }

    /**
     * test case to get books product details by author
     */
    @Test
    public void testGetBooksProductDetailsByAuthor() {
        List<Books> booksList = new ArrayList<>();
        booksList.add(new Books("Jane Doe", "2020"));
        when(booksService.getBooksDetailsByAuthor("Jane Doe")).thenReturn(booksList);

        List<Books> result = booksController.getBooksProductDetailsByAuthor("Jane Doe");

        assertEquals(booksList, result);
        verify(booksService, times(1)).getBooksDetailsByAuthor("Jane Doe");
    }

}
