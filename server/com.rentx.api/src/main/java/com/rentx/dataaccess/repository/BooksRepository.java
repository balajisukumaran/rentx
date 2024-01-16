package com.rentx.dataaccess.repository;

import com.rentx.entities.Books;
import com.rentx.entities.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
    /**
     * method to find books by author
     * @param author author
     * @return object of author
     */
    List<Books> findByAuthor(String author);

    /**
     * method to find Books By Books ID
     * @param bookId book Id
     * @return book id
     */
    Books findBooksByBooksID(int bookId);

    /**
     * method to delete by book id
     * @param bookId for book id
     */
    void deleteByBooksID(int bookId);
}
