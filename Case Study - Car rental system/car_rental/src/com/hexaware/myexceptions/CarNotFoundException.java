package com.hexaware.myexceptions;


/*
 * Exception thrown when a car with a given ID is not found in the database.
 */

public class CarNotFoundException extends Exception {
    public CarNotFoundException(String message) {
        super(message);
    }
}