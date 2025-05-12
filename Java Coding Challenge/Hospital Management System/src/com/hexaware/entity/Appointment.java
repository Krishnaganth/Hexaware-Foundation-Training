package com.hexaware.entity;

import java.util.Date;

public class Appointment {
	
	/* Attributes of class Doctor */
	
	private int appointmentId;
	private int patientId;
	private int doctorId;
	private Date appointmentDate;
	private String description;
	
	/*Parameterized constructor */
	
	public Appointment(int appointmentId, int patientId, int doctorId, Date string, String description) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentDate = string;
		this.description = description;
	}
	
	/* Default constructor */
	
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* Getters and Setter */
	
	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/* calling - toString */
	
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId
				+ ", appointmentDate=" + appointmentDate + ", description=" + description + "]";
	}
	
	
	
	
	
	
	
	

}
