package com.rentx.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * new password data transfer object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPasswordDto {
    /**
     * string reset tocken
     */
    String resetToken;
    /**
     * string new password
     */
    String newPassword;
}
