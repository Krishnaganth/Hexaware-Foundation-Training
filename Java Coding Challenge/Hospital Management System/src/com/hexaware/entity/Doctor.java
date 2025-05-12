package com.hexaware.entity;

public class Doctor {
	
	/* Attributes of class Doctor */
	
	private int doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String contactNumber;
	
    /*Parameterized constructor */
    
    public Doctor(int doctorId, String firstName, String lastName, String specialization, String contactNumber) {
		super();
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialization = specialization;
		this.contactNumber = contactNumber;
	}

    /* Default constructor */
    
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* Getters and Setter */

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/* calling - toString */
	
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", specialization=" + specialization + ", contactNumber=" + contactNumber + "]";
	}
    
	
	
    
    
    

}
