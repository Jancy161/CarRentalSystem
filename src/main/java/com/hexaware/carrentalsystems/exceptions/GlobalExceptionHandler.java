package com.hexaware.carrentalsystems.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * @ExceptionHandler(CarNotFoundException.class)
	 * 
	 * @ResponseStatus(reason= "Requested car Id is not found",
	 * code=HttpStatus.NOT_FOUND) public void handleCarNotFound() { }
	 */

	 @ExceptionHandler(CarNotFoundException.class)
	    public ResponseEntity<String> handleCarNotFound(CarNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	 
	 
	 @ExceptionHandler(UserNotFoundException.class)
	 public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
	     return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(ReservationNotFoundException.class)
	    public ResponseEntity<Object> handleReservationNotFound(ReservationNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	 
	 @ExceptionHandler(PaymentNotFoundException.class)
	    public ResponseEntity<Object> handleGenericException(Exception ex) {
	        return new ResponseEntity<> ( ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(FeedbackNotFoundException.class)
	    public ResponseEntity<Object> handleGenericException(FeedbackNotFoundException ex) {
	        return new ResponseEntity<> ( ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
