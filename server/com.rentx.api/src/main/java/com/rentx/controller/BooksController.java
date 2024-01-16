package com.rentx.controller;

import com.rentx.businessservices.BooksService;
import com.rentx.businessservices.interfaces.IBooksService;
import com.rentx.entities.Books;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/products/books")
public class BooksController {
    /**
     * autowire book service
     */

    private final IBooksService booksService;

    public BooksController(IBooksService booksService) {
        this.booksService = booksService;
    }

    /**
     * get mapping to get All Books Category Details
     * @return book service details
     */
    @GetMapping
    public List<Books> getAllBooksCategoryDetails() {
        return booksService.getAllBooksDetails();
    }

    /**
     * post mapping to add book products details
     * @param book book
     * @return added book in book service
     */
    @PostMapping("/put")
    public Books addBookProductDetails(@RequestBody Books book) {
        return booksService.addBook(book);
    }

    /**
     * get mapping to get book and product details by id
     * @param booksId book id
     * @return response entity ok for book
     */
    @GetMapping("/id/{booksId}")
    public ResponseEntity<Books> getBookProductDetailsById(@PathVariable int booksId) {
        Books book = booksService.getBookDetailsById(booksId);
        return ResponseEntity.ok(book);
    }

    /**
     * get mapping to get Books Product Details By Author
     * @param author author
     * @return book details by author in book service
     */
    @GetMapping("/author/{author}")
    public List<Books> getBooksProductDetailsByAuthor(@PathVariable String author) {
        return booksService.getBooksDetailsByAuthor(author);
    }
}
