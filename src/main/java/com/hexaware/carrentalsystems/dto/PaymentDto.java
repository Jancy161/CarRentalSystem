package com.hexaware.carrentalsystems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDto {
    private int paymentId;

    @NotNull(message = "Reservation ID is required")
    private int reservationId;

    @DecimalMin(value = "0.0")
    private double amount;

    @NotNull(message = "Payment method is required")
    @Pattern(regexp = "CARD|UPI", 
    message = "CARD or UPI")
    private String method;

    @NotNull(message = "Payment status is required")
    @Pattern(regexp = "SUCCESS|FAILED", 
    message = "SUCCESS or FAILED")   
    private String status;


}