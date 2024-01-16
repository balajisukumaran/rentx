package com.rentx.businessservices;

import com.rentx.businessservices.interfaces.IBooksService;
import com.rentx.dataaccess.repository.BooksRepository;
import com.rentx.entities.Books;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BooksService implements IBooksService {
    /**
     * autowire Books Repository
     */
    @Autowired
    private BooksRepository bookRepository;

    /**
     * method ot get all books details
     *
     * @return list of books
     */
    @Override
    public List<Books> getAllBooksDetails() {
        return bookRepository.findAll();
    }

    /**
     * method to add books
     *
     * @param book for book
     * @return book repo
     */
    @Override
    public Books addBook(Books book) {
        return bookRepository.save(book);
    }

    /**
     * method to get books details by id
     *
     * @param booksId for book id
     * @return book repo or throws exceptions
     */
    @Override
    public Books getBookDetailsById(int booksId) {
        Optional<Books> books = bookRepository.findById(booksId);

        if (books != null && books.isPresent())
            return books.get();

        throw new ResourceNotFoundException("Book Details with specific bookId not found" + booksId);
    }

    /**
     * method to get book details by author
     *
     * @param author for author of books
     * @return list of book repo
     */
    @Override
    public List<Books> getBooksDetailsByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}
