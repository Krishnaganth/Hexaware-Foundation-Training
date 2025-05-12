package com.hexaware.entity;

public class Customer {

	/* Attributes of class Customer */
	
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    
    /*Parameterized constructor */
    
    public Customer(int customerID, String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
    
    /* Non-parameterized constructor */	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/* Getters and Setter */
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/* toString */
	
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + "]";
	}
    
}
