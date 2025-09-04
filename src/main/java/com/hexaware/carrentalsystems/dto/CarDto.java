package com.hexaware.carrentalsystems.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CarDto {
	

	
	@Min(value = 5)
	//@Max(value = 99)
	    private int carId;
	@Pattern(regexp = "[A-Z][a-z]{2,30}" )
		private String brand;
	@Pattern(regexp = "[A-Z][a-z]{2,30}" )	
	    private String model;
	@Min(800)  
	    private double pricePerDay;
	    
	 
	@NotNull
	@Pattern(regexp = "AVAILABLE|BOOKED", 
    message = "AVAILABLE or BOOKED")
	private String availability;

	

}
