package com.hexaware.carrentalsystems.dto;

import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class FeedbackDto {

    @Min(1)
    private int feedbackId;

    @Min(value = 1)
	//@Max(value = 199)
    private int userId;

    @Min(value = 5)
	//@Max(value = 99)
    private int carId;

    @Min(value = 1)
    @Max(value = 5)
    private int rating;

    @NotBlank
    private String comment;
}
