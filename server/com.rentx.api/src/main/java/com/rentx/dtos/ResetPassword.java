package com.rentx.dtos;

import java.util.Date;

/**
 * reset password data transfer object
 */
public record ResetPassword (int userId, String firstName, String lastName, String phone, String prefLanguage, Date joiningDate, String privacy, String status, int areaId, String email, String login, char[] password, String resetToken) { }