package com.hexaware.carrentalsystems.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hexaware.carrentalsystems.entities.Car.Availability;
import com.hexaware.carrentalsystems.entities.Reservation.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationDto {

    @Min(1)
    private int reservationId;

    @Min(value=1)
    @Max(value=99)
    private int userId;

    @Min(value=5)
    @Max(value=99)
    private int carId;

    @JsonFormat(pattern = "MM/dd/yyyy")
    @Future(message = "Pickup date must be in the future")
    private Date pickupDate;

    @JsonFormat(pattern = "MM/dd/yyyy")
    @Future(message = "Drop-off date must be in the future")
    private Date dropoffDate;

    @Min(value = 0, message = "Total amount must be positive")
    private double totalAmount;

    @NotNull
	@Schema(implementation = Status.class, description = "Reservation status: ACTIVE or CANCELLED or COMPLETED")
    private Status status;

}
