package com.hexaware.main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.HospitalServiceImpl;
import com.hexaware.entity.Appointment;
import com.hexaware.entity.Doctor;
import com.hexaware.entity.Patient;
import com.hexaware.exception.PatientNumberNotFoundException;

public class MainModule {
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HospitalServiceImpl hs = new HospitalServiceImpl();

        while (true) {
            System.out.println("\n===== HOSPITAL MANAGEMENT MENU =====");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Appointments for Patient");
            System.out.println("5. View Appointments for Doctor");
            System.out.println("6. Update Appointment");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
            
                case 1:
                	// Add new Patient
                    Patient p = new Patient();
                    System.out.print("Enter Patient ID: ");
                    p.setPatientId(sc.nextInt());
                    sc.nextLine();
      
                    System.out.print("Enter First Name: ");
                    p.setFirstName(sc.nextLine());
                    
                    System.out.print("Enter Last Name: ");
                    p.setLastName(sc.nextLine());
                    
                    System.out.print("Enter DOB (yyyy-mm-dd): ");
                    p.setDateOfBirth(Date.valueOf(sc.nextLine()));
                    
                    System.out.print("Enter Gender: ");
                    p.setGender(sc.nextLine());
                    
                    System.out.print("Enter Contact Number: ");
                    p.setContactNumber(sc.nextLine());
                    
                    System.out.print("Enter Address: ");
                    p.setAddress(sc.nextLine());

                    hs.addPatient(p); // Call method to insert patient
                    break;

                case 2:
                	// Add new Doctor
                    Doctor d = new Doctor();
                    System.out.print("Enter Doctor ID: ");
                    d.setDoctorId(sc.nextInt());
                    sc.nextLine();
                    
                    System.out.print("Enter First Name: ");
                    d.setFirstName(sc.nextLine());
                    
                    System.out.print("Enter Last Name: ");
                    d.setLastName(sc.nextLine());
                    
                    System.out.print("Enter Specialization: ");
                    d.setSpecialization(sc.nextLine());
                    
                    System.out.print("Enter Contact Number: ");
                    d.setContactNumber(sc.nextLine());

                    hs.addDoctor(d); // Call method to insert doctor
                    break;
     
                case 3:
                	//Schedule a new appointment 
                    Appointment a = new Appointment();

                    System.out.print("Enter Appointment ID: ");
                    a.setAppointmentId(sc.nextInt());

                    System.out.print("Enter Patient ID: ");
                    a.setPatientId(sc.nextInt());

                    System.out.print("Enter Doctor ID: ");
                    a.setDoctorId(sc.nextInt());

                    System.out.print("Enter Appointment Date (yyyy-mm-dd): ");
                    a.setAppointmentDate(Date.valueOf(sc.next()));

                    System.out.print("Enter Description: ");
                    sc.nextLine();
                    a.setDescription(sc.nextLine());

                    hs.scheduleAppointment(a); // Call method to schedule appointment
                    break;

                case 4:
                	//View appointments for a patient
                    System.out.print("Enter Patient ID: ");
                    int pid = sc.nextInt();
                    try {
                        List<Appointment> patientApps = hs.getAppointmentsForPatient(pid);
                        patientApps.forEach(System.out::println);
                    } catch (PatientNumberNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                	//View appointments for a doctor
                    System.out.print("Enter Doctor ID: ");
                    int did = sc.nextInt();
                    List<Appointment> doctorApps = hs.getAppointmentsForDoctor(did);
                    doctorApps.forEach(System.out::println);
                    break;

                case 6:
                	//Update an existing appointment
                    Appointment updateApp = new Appointment();

                    System.out.print("Enter Appointment ID to Update: ");
                    updateApp.setAppointmentId(sc.nextInt());

                    System.out.print("Enter New Appointment Date (yyyy-mm-dd): ");
                    updateApp.setAppointmentDate(Date.valueOf(sc.next()));

                    System.out.print("Enter New Description: ");
                    sc.nextLine(); // consume newline
                    updateApp.setDescription(sc.nextLine());

                    hs.updateAppointment(updateApp); // Call method to update appointment
                    break;

                case 7:
                	//Cancel an appointment 
                    System.out.print("Enter Appointment ID to Cancel: ");
                    int aid = sc.nextInt();
                    hs.cancelAppointment(aid); // Call method to delete appointment
                    break;

                case 8:
                	//Exit the application 
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                	//Handle invalid input 
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
