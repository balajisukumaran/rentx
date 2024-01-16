package com.rentx.businessservices.interfaces;

import com.rentx.entities.Books;
import java.util.List;

public interface IBooksService {

    /**
     * method to get all books details
     * @return list of all book details
     */
    List<Books> getAllBooksDetails();

    /**
     * method to add books
     * @param book for book
     * @return book object
     */
    Books addBook(Books book);

    /**
     * method to get book details by book id
     * @param booksId for book id
     * @return object for book details
     */
    Books getBookDetailsById(int booksId);

    /**
     * method to get books details by author
     * @param author for author of books
     * @return list for the  books by author
     */
    List<Books> getBooksDetailsByAuthor(String author);
}
