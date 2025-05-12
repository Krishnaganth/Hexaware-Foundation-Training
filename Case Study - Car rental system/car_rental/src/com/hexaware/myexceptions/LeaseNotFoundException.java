package com.hexaware.myexceptions;


/*
 * Exception thrown when a lease with a given ID is not found.
 */

public class LeaseNotFoundException extends Exception {
    public LeaseNotFoundException(String message) {
        super(message);
    }
}