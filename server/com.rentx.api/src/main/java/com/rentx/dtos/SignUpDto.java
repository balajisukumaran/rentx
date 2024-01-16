package com.rentx.dtos;

import java.util.Date;

/**
 * sign up data transfer object
 */
public record SignUpDto (String firstName, String lastName, String phone, String prefLanguage, Date joiningDate,  String privacy, String status, int areaId, String email, String login, char[] password) { }