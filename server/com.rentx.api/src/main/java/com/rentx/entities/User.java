package com.rentx.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * user entity class
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="`user`")
public class User {
    /**
     * primary key and auto generated field user id
     */
    @Id
    @Column(name="userID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    /**
     * phone number field
     */
    @Column(name = "phone")
    private String phone;
    /**
     * first name column to store first name
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * field to store last name
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * preffered language field
     */
    @Column(name = "pref_language")
    private String prefLanguage;
    /**
     * joining date to store origin date
     */
    @Column(name = "joining_date")
    private Date joiningDate;
    /**
     * privacy for user
     */
    @Column(name = "privacy")
    private String privacy;
    /**
     * status field to check user status
     */
    @Column(name = "status")
    private String status;
    /**
     * area id for area
     */
    @Column(name = "area_ID")
    private int areaId;
    /**
     * user name field for user to login
     */
    @Column(name = "login")
    private String login;
    /**
     * passsword to login
     */
    @Column(name = "token")
    private String password;
    /**
     * for reset password use reset token
     */
    @Column(name = "reset_token")
    private String resetToken;
    /**
     * store user email address
     */
    @Column(name = "email")
    private String email;
    /**
     * field for soft delete user
     */
    @Column(name = "isexists")
    private int isExists;
}
