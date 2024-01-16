package com.rentx.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * user data transfer object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    /**
     * integer user id
     */
    private int userID;
    /**
     * string phone number
     */
    private String phone;
    /**
     * string first name
     */
    private String firstName;
    /**
     * string last name
     */
    private String lastName;
    /**
     * string preffered language
     */
    private String prefLanguage;
    /**
     * date joining
     */
    private Date joiningDate;
    /**
     * string privacy
     */
    private String privacy;
    /**
     * string status
     */
    private String status;
    /**
     * integer area id
     */
    private int areaId;
    /**
     * string login
     */
    private String login;
    /**
     * string token
     */
    private String token;
    /**
     * string email
     */
    private String email;
    /**
     * string reset token
     */
    private String resetToken;
    /**
     * boolean is exists
     */
    private boolean isexists;
}