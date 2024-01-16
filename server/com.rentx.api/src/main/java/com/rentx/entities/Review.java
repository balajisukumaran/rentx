package com.rentx.entities;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Getter
@Setter
@Table(name="review")
public class Review{

    @Id
    @Column(name="reviewID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewID;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="userID", referencedColumnName = "userID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="productID", referencedColumnName = "productID")
    private ProductReal product;

    @Transient
    private int productID;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private int rating;

    public int getProductId() {
        return product != null ? product.getProductID() : null;
    }
}
