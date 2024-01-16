package com.rentx.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name="wishlist")
public class Wishlist{
    /**
     * primary and auto generated key wihslist id for wishlist table
     */
    @Id
    @Column(name="wishlistID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistID;
    /**
     * many to many relationship with user for userid
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="userID", referencedColumnName = "userID")
    private User user;
    /**
     * many to many relationship with product for productid
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="productID", referencedColumnName = "productID")
    private ProductReal product;

}
