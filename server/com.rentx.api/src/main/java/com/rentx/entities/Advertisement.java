package com.rentx.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="advertisement")
public class Advertisement {
    /**
     * primary key and auto generated field for advertisement id
     */
    @Id
    @Column(name="advertisement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int advertisementID;
    /**
     * advertisement verification status
     */
    @Column(name = "verification_status")
    private String verificationStatus;
    /**
     * advertisement title
     */
    @Column(name = "advt_title")
    private String advtTitle;
    /**
     * field to check if advertisement is active
     */
    @Column(name = "is_active")
    private String isActive;
    /**
     * advertisement post date
     */
    @Column(name = "post_date")
    private Date postDate;
    /**
     * advertisement expiry date
     */
    @Column(name = "expiry_date")
    private Date expiryDate;
    /**
     * many to many relationship with user and it's user id
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="user_id", referencedColumnName = "userID")
    private User userID;
    /**
     * many to many relationship with product real and product id
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="product_id", referencedColumnName = "productID")
    private ProductReal productID;
}
