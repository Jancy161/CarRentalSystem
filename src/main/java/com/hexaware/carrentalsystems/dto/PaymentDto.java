package com.hexaware.carrentalsystems.dto;

import com.hexaware.carrentalsystems.entities.Payment.Method;
import com.hexaware.carrentalsystems.entities.Payment.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
    @Schema(implementation = Method.class, description = "Payment method:CARD or UPI")
    private Method method;

    @NotNull(message = "Payment status is required")
    @Schema(implementation = Status.class, description = "Payment status: SUCCESS or FAILED")
    private Status status;


}