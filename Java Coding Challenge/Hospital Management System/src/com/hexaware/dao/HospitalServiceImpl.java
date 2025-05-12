package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Appointment;
import com.hexaware.entity.Doctor;
import com.hexaware.entity.Patient;
import com.hexaware.exception.PatientNumberNotFoundException;
import com.hexaware.util.DBConnection;

public class HospitalServiceImpl implements IHospitalService{
	
	// Establish database connection
	Connection conn = DBConnection.getConnection();
	
	// Add a new patient to the database
	@Override
	public boolean addPatient(Patient patient) {
	    try {
	        String query = "INSERT INTO Patient (patientId, firstName, lastName, dateOfBirth, gender, contactNumber, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, patient.getPatientId());
	        pst.setString(2, patient.getFirstName());
	        pst.setString(3, patient.getLastName());
	        pst.setDate(4, new java.sql.Date(patient.getDateOfBirth().getTime()));
	        pst.setString(5, patient.getGender());
	        pst.setString(6, patient.getContactNumber());
	        pst.setString(7, patient.getAddress());

	        int rows = pst.executeUpdate();
	        System.out.println("Patient inserted successfully.");
	        return rows > 0;
	    } catch (SQLException e) {
	        System.out.println("Error inserting patient: " + e.getMessage());
	    }
	    return false;
	}
	
	// Add a new doctor to the database
	@Override
	public boolean addDoctor(Doctor doctor) {
	    try {
	        String query = "INSERT INTO Doctor (doctorId, firstName, lastName, specialization, contactNumber) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, doctor.getDoctorId());
	        pst.setString(2, doctor.getFirstName());
	        pst.setString(3, doctor.getLastName());
	        pst.setString(4, doctor.getSpecialization());
	        pst.setString(5, doctor.getContactNumber());

	        int rows = pst.executeUpdate();
	        System.out.println("Doctor inserted successfully.");
	        return rows > 0;
	    } catch (SQLException e) {
	        System.out.println("Error inserting doctor: " + e.getMessage());
	    }
	    return false;
	}

	// Fetch a specific appointment by its ID
    @Override
    public Appointment getAppointmentById(int appointmentId) throws PatientNumberNotFoundException {
        try {
            String query = "SELECT * FROM Appointment WHERE appointmentId = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, appointmentId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getDate("appointmentDate"),
                    rs.getString("description")
                );
            }
            else {
                throw new PatientNumberNotFoundException("Appointment ID " + appointmentId + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching appointment by ID: " + e.getMessage());
        }
        return null;
    }

    // Get all appointments for a specific patient
    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
        List<Appointment> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Appointment WHERE patientId = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, patientId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Appointment app = new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getDate("appointmentDate"),
                    rs.getString("description")
                );
                list.add(app);
            }
            
            if (list.isEmpty()) {
                throw new PatientNumberNotFoundException("No appointments found for Patient ID: " + patientId);
            }
            
        } catch (SQLException e) {
            System.out.println("Error fetching appointments for patient: " + e.getMessage());
        }
        return list;
    }

    // Get all appointments for a specific doctor
    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Appointment WHERE doctorId = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, doctorId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Appointment app = new Appointment(
                    rs.getInt("appointmentId"),
                    rs.getInt("patientId"),
                    rs.getInt("doctorId"),
                    rs.getDate("appointmentDate"),
                    rs.getString("description")
                );
                list.add(app);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching appointments for doctor: " + e.getMessage());
        }
        return list;
    }

    // Schedule a new appointment
    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        try {
            String query = "INSERT INTO Appointment (appointmentId, patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, appointment.getAppointmentId());
            pst.setInt(2, appointment.getPatientId());
            pst.setInt(3, appointment.getDoctorId());
            pst.setDate(4, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            pst.setString(5, appointment.getDescription());

            int rows = pst.executeUpdate();
            System.out.println("Appointment scheduled.");
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error scheduling appointment: " + e.getMessage());
        }
        return false;
    }

    // Update an existing appointment
    @Override
    public boolean updateAppointment(Appointment appointment) {
        try {
            String query = "UPDATE Appointment SET appointmentDate = ?, description = ? WHERE appointmentId = ?";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setDate(1, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            pst.setString(2, appointment.getDescription());
            pst.setInt(3, appointment.getAppointmentId());

            int rows = pst.executeUpdate();
            System.out.println("Appointment updated.");
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating appointment: " + e.getMessage());
        }
        return false;
    }

    // Cancel an appointment by ID
    @Override
    public boolean cancelAppointment(int appointmentId) {
        try {
            String query = "DELETE FROM Appointment WHERE appointmentId = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, appointmentId);

            int rows = pst.executeUpdate();
            System.out.println("Appointment cancelled.");
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error cancelling appointment: " + e.getMessage());
        }
        return false;
    }
}