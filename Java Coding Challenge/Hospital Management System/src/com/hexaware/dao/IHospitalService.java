package com.hexaware.dao;

import java.sql.SQLException;
import java.util.List;

import com.hexaware.entity.Appointment;
import com.hexaware.entity.Doctor;
import com.hexaware.entity.Patient;
import com.hexaware.exception.PatientNumberNotFoundException;

public interface IHospitalService {
	
	// Fetch appointment by its ID
    public Appointment getAppointmentById(int appointmentId) throws SQLException, PatientNumberNotFoundException;

    // List all appointments for a specific patient
    public List<Appointment> getAppointmentsForPatient(int patientId) throws SQLException, PatientNumberNotFoundException ;

    // List all appointments for a specific doctor
    public List<Appointment> getAppointmentsForDoctor(int doctorId) throws SQLException;

    // Insert a new appointment into the system
    public boolean scheduleAppointment(Appointment appointment) throws SQLException;

    // Update an existing appointment's details
    public boolean updateAppointment(Appointment appointment) throws SQLException;

    // Cancel an appointment by ID
    public boolean cancelAppointment(int appointmentId) throws SQLException;
    
    // add patient detials
    public boolean addPatient(Patient patient);
    
    //add doctor details
    public boolean addDoctor(Doctor doctor);
    


}
