package com.rentx.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="purchase")
public class Purchase {
    /**
     * primary key and auto generated puchase id
     */
    @Id
    @Column(name="purchaseID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseID;
    /**
     * many to many relationship with user for user id with buyer id
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="buyer_id", referencedColumnName = "userID")
    private User buyerID;
    /**
     * many to many relationship with user for user id with seller id
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="seller_id", referencedColumnName = "userID")
    private User sellerID;
    /**
     * product id
     */
    @Column(name="productID")
    private int productID;
    /**
     * many to many relationship with advertisement for advertisement id with advertisement id
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn (name="advertisementID", referencedColumnName = "advertisement_id")
    private Advertisement advertisementID;
    /**
     * date of sale for product
     */
    @Column(name="date_of_sale")
    private LocalDateTime date_of_sale;
    /**
     * field to check whether amount is paid or not
     */
    @Column(name="paid")
    private double paid;
    /**
     * is product on emi or not
     */
    @Column(name="is_emi")
    private int is_emi;

    /**
     * constructor method for purchase
     * @param purchaseID purchaseID
     * @param date_of_sale date of sale
     */
    public Purchase(int purchaseID, LocalDateTime date_of_sale) {
        this.purchaseID = purchaseID;
        this.date_of_sale = date_of_sale;
    }
}
