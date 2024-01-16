package com.rentx.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="books")
public class Books {
    /**
     * primary key book id
     */
    @Id
    @Column(name="booksID")
    private int booksID;
    /**
     * many to many relationship with category and category id
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="catid", referencedColumnName = "category_id")
    private Category categoryID;
    /**
     * to check condition fo book
     */
    @Column(name="book_condition")
    private String condition;
    /**
     * books author name
     */
    @Column(name="author")
    private String author;
    /**
     * year of published for books
     */
    @Column(name="year_of_public")
    private String yearOfPublished;

    /**
     * books method
     * @param author strinf object
     * @param yearOfPublished string
     */
    public Books(String author, String yearOfPublished) {
        this.author = author;
        this.yearOfPublished = yearOfPublished;
    }

    /**
     * books method constructor
     * @param booksID book id
     * @param author author
     * @param yearOfPublished year Of Published
     */
    public Books(int booksID, String author, String yearOfPublished) {
       this.booksID = booksID;
        this.author = author;
        this.yearOfPublished = yearOfPublished;
    }
}
