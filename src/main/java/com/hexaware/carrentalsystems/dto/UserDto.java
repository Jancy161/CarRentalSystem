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
	
	 public interface OnCreate {}
	    public interface OnUpdate {}
	    
	@Min(value = 1)
	//@Max(value = 99)
	 private int userId;
		@Pattern(regexp = "[A-Z][a-z]{1,15}")
	    private String name;

	    @Email
	    private String email;

	    @Pattern(
	    	    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
	    	    message = "Password must contain uppercase, lowercase, digit, and special character",
	    	    groups = OnCreate.class
	    	)
	    	private String password;


	   
	   
	   @Pattern(regexp = "User|Admin")
	   private String role; //User, Admin
}
