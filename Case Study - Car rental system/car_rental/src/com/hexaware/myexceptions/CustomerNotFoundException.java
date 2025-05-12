package com.hexaware.myexceptions;

/*
 * Exception thrown when a customer with a given ID is not found.
 */

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
