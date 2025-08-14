package com.hexaware.carrentalsystems.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserDto {
	@Min(value = 1)
	@Max(value = 99)
	 private int userId;
		@Pattern(regexp = "[A-Z][a-z]{1,15}")
	    private String name;

	    @Email
	    private String email;

	   @Pattern(regexp ="[A-Z]...[a-z]{1,3}")
	    private String password;
	   
	   @Pattern(regexp = "User|Admin")
	   private String role; //User, Admin
}
