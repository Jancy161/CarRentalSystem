package com.hexaware.carrentalsystems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDto {
    private String email;
    private String securityQuestion;
    private String securityAnswer;
    private String newPassword;
}

