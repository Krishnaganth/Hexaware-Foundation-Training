package com.hexaware.main;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.ICarLeaseRepositoryImpl;
import com.hexaware.entity.Car;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Lease;
import com.hexaware.myexceptions.CarNotFoundException;
import com.hexaware.myexceptions.CustomerNotFoundException;
import com.hexaware.myexceptions.LeaseNotFoundException;

public class MainModule {
	
	public static void main(String[] args) {
		ICarLeaseRepositoryImpl repo = new ICarLeaseRepositoryImpl();
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			//MAIN MENU
	        System.out.println("\n===== CAR RENTAL SYSTEM MENU =====");
	        System.out.println("1. Add New Customer");
	        System.out.println("2. Update Customer Information");
	        System.out.println("3. Retrieve Customer Details");
	        System.out.println("4. List all Customer Details");
	        System.out.println("5. Add New Car");
	        System.out.println("6. Update Car Availability");
	        System.out.println("7. Retrieve Car Information");
	        System.out.println("8. List all available cars");
	        System.out.println("9. List all Rented Cars");
	        System.out.println("10. Create Lease (Daily/Monthly)");
	        System.out.println("11. Calculate Lease Cost");
	        System.out.println("12. List all Activelease");
	        System.out.println("13. List Lease History");
	        System.out.println("14. Record Payment for Lease");
	        System.out.println("15. Retrieve Payment History for Customer");
	        System.out.println("16. Calculate Total Revenue from Payments");
	        System.out.println("0. Exit");
	        System.out.print("Enter your choice: ");
	        choice = sc.nextInt();

	        try {
	            switch (choice) {
	                
	                //Add a new Customer
	                case 1:
	                    sc.nextLine();
	                    System.out.print("Enter First Name: ");
	                    String firstName = sc.nextLine();
	                    System.out.print("Enter Last Name: ");
	                    String lastName = sc.nextLine();
	                    System.out.print("Enter Email: ");
	                    String email = sc.nextLine();
	                    System.out.print("Enter Phone Number: ");
	                    String phone = sc.nextLine();
	                    Customer customer = new Customer(0, firstName, lastName, email, phone);
	                    repo.addCustomer(customer);
	                    System.out.println("Customer added successfully!");
	                    break;
	                    
	                //Update the Customer Info
	                case 2:
	                    System.out.print("Enter Customer ID to update: ");
	                    int updateID = sc.nextInt();
	                    sc.nextLine();
	                    Customer existingCustomer = repo.findCustomerById(updateID);
	                    System.out.print("Enter New First Name: ");
	                    String newFirst = sc.nextLine();
	                    System.out.print("Enter New Last Name: ");
	                    String newLast = sc.nextLine();
	                    System.out.print("Enter New Email: ");
	                    String newEmail = sc.nextLine();
	                    System.out.print("Enter New Phone Number: ");
	                    String newPhone = sc.nextLine();
	                    repo.removeCustomer(updateID);
	                    Customer updated = new Customer(0, newFirst, newLast, newEmail, newPhone);
	                    int newCustomerId = repo.addCustomer(updated);
	                    System.out.println("Customer updated successfully! and Your New Customer ID: " + newCustomerId);
	                    break;
	                
	                //Find Customer by ID
	                case 3:
	                    System.out.print("Enter Customer ID: ");
	                    int custID = sc.nextInt();
	                    Customer foundCust = repo.findCustomerById(custID);
	                    System.out.println("Customer Name: " + foundCust.getFirstName() + " " + foundCust.getLastName());
	                    System.out.println("Email: " + foundCust.getEmail());
	                    System.out.println("Phone Number: " + foundCust.getPhoneNumber());
	                    break;
	                
	                //List All Customers with Info
	                case 4:
	                    List<Customer> customers = repo.listCustomers();
	                    if (customers.isEmpty()) {
	                        System.out.println("No customers available at the moment.");
	                    } else {
	                        System.out.println("Customer List:");
	                        for (Customer customer1 : customers) {
	                            System.out.println("Customer ID: " + customer1.getCustomerID());
	                            System.out.println("First Name: " + customer1.getFirstName());
	                            System.out.println("Last Name: " + customer1.getLastName());
	                            System.out.println("Email: " + customer1.getEmail());
	                            System.out.println("Phone Number: " + customer1.getPhoneNumber());
	                            System.out.println("-----------------------------------");
	                        }
	                    }
	                    break;

	                //Add a new Car
	                case 5:
	                    sc.nextLine();
	                    System.out.print("Enter Make: ");
	                    String make = sc.nextLine();
	                    System.out.print("Enter Model: ");
	                    String model = sc.nextLine();
	                    System.out.print("Enter Year: ");
	                    int year = sc.nextInt();
	                    System.out.print("Enter Daily Rate: ");
	                    double rate = sc.nextDouble();
	                    sc.nextLine();
	                    System.out.print("Enter Status (available/notAvailable): ");
	                    String status = sc.nextLine();
	                    if (!status.equalsIgnoreCase("available") && !status.equalsIgnoreCase("notAvailable")) {
	                        System.out.println("Invalid status. Please enter 'available' or 'notAvailable'.");
	                        break; // exit this case without adding the car
	                    }
	                    System.out.print("Enter Passenger Capacity: ");
	                    int passengers = sc.nextInt();
	                    System.out.print("Enter Engine Capacity: ");
	                    int engine = sc.nextInt();
	                    Car car = new Car(0, make, model, year, rate, status, passengers, engine);
	                    repo.addCar(car);
	                    System.out.println("Car added successfully!");
	                    break;

	                //Update the car availablity
	                case 6:
	                    System.out.print("Enter Lease ID: ");
	                    int leaseID = sc.nextInt();
	                    repo.returnCar(leaseID);
	                    System.out.println("Car marked as available!");
	                    break;

	                //Retrieve the Car Info
	                case 7:
	                    System.out.print("Enter Car ID: ");
	                    int carID = sc.nextInt();
	                    Car foundCar = repo.findCarById(carID);
	                    System.out.println("Car Details: " + foundCar.getMake() + " " + foundCar.getModel());
	                    System.out.println("Year: " + foundCar.getYear());
	                    System.out.println("Daily Rate: ₹" + foundCar.getDailyRate());
	                    System.out.println("Status: " + foundCar.getStatus());
	                    System.out.println("Passenger Capacity: " + foundCar.getPassengerCapacity());
	                    System.out.println("Engine Capacity: " + foundCar.getEngineCapacity() + " cc");	                    
	                    break;
	                
	                //List all Available Cars
	                case 8:
	                    List<Car> availableCars = repo.listAvailableCars();
	                    if (availableCars.isEmpty()) {
	                        System.out.println(" No available cars at the moment.");
	                    } else {
	                        System.out.println("Available Cars:");
	                        for (Car car1 : availableCars) {
	                            System.out.println("Vehicle ID: " + car1.getVehicleID());
	                            System.out.println("Make: " + car1.getMake());
	                            System.out.println("Model: " + car1.getModel());
	                            System.out.println("Year: " + car1.getYear());
	                            System.out.println("Daily Rate: ₹" + car1.getDailyRate());
	                            System.out.println("Status: " + car1.getStatus());
	                            System.out.println("Passenger Capacity: " + car1.getPassengerCapacity());
	                            System.out.println("Engine Capacity: " + car1.getEngineCapacity() + " cc");
	                            System.out.println("-----------------------------------");
	                        }
	                    }
	                    break;
  
	                //List the Rented Cars
	                case 9:
	                    List<Car> rentedCars = repo.listRentedCars();
	                    if (rentedCars.isEmpty()) {
	                        System.out.println("No rented cars currently.");
	                    } else {
	                        System.out.println("Rented Cars:");
	                        for (Car car1 : rentedCars) {
	                            System.out.println("Vehicle ID: " + car1.getVehicleID());
	                            System.out.println("Make: " + car1.getMake());
	                            System.out.println("Model: " + car1.getModel());
	                            System.out.println("Year: " + car1.getYear());
	                            System.out.println("Daily Rate: ₹" + car1.getDailyRate());
	                            System.out.println("Status: " + car1.getStatus());
	                            System.out.println("Passenger Capacity: " + car1.getPassengerCapacity());
	                            System.out.println("Engine Capacity: " + car1.getEngineCapacity() + " cc");
	                            System.out.println("-----------------------------------");
	                        }
	                    }
	                    break;

	                //Create a new Lease
	                case 10:
	                    System.out.print("Enter Customer ID: ");
	                    int leaseCustID = sc.nextInt();
	                    System.out.print("Enter Car ID: ");
	                    int leaseCarID = sc.nextInt();
	                    sc.nextLine();
	                    System.out.print("Enter Lease Type (Daily/Monthly): ");
	                    String leaseType = sc.nextLine();
	                    System.out.print("Enter Lease Start Date (yyyy-mm-dd): ");
	                    String sDate = sc.next();
	                    System.out.print("Enter Lease End Date (yyyy-mm-dd): ");
	                    String eDate = sc.next();
	                    Date startDate = java.sql.Date.valueOf(sDate);
	                    Date endDate = java.sql.Date.valueOf(eDate);
	                    Lease lease = repo.createLease(leaseCustID, leaseCarID, startDate, endDate);
	                    System.out.println("Lease created with ID: " + lease.getLeaseID());
	                    break;

	                //Estimate Lease Cost
	                case 11:
	                    System.out.print("Enter Car ID: ");
	                    int cid = sc.nextInt();
	                    sc.nextLine();
	                    System.out.print("Enter Lease Type (Daily/Monthly): ");
	                    String type = sc.nextLine();
	                    System.out.print("Enter Lease Start Date (yyyy-mm-dd): ");
	                    Date sD = java.sql.Date.valueOf(sc.next());
	                    System.out.print("Enter Lease End Date (yyyy-mm-dd): ");
	                    Date eD = java.sql.Date.valueOf(sc.next());
	                    Car selectedCar = repo.findCarById(cid);
	                    long duration = ChronoUnit.DAYS.between(sD.toLocalDate(), eD.toLocalDate());
	                    double cost = 0;
	                    if (type.equalsIgnoreCase("Daily")) {
	                        cost = duration * selectedCar.getDailyRate();
	                    } else {
	                        long months = duration / 30;
	                        cost = months * selectedCar.getDailyRate() * 30;
	                    }
	                    System.out.println("Estimated Lease Cost: ₹" + cost);
	                    break;
	                
	                //List the Active Leases
	                case 12:
                        List<Lease> activeLeases = repo.listActiveLeases();
                        if (activeLeases.isEmpty()) {
                            System.out.println("No active leases at the moment.");
                        } else {
                            System.out.println("Active Leases:");
                            for (Lease lease1 : activeLeases) {
                                // Print lease details
                                System.out.println("Lease ID: " + lease1.getLeaseID());
                                System.out.println("Vehicle ID: " + lease1.getVehicleID());
                                System.out.println("Customer ID: " + lease1.getCustomerID());
                                System.out.println("Start Date: " + lease1.getStartDate());
                                System.out.println("End Date: " + lease1.getEndDate());
                                System.out.println("Lease Type: " + lease1.getType());
                                System.out.println("-----------------------------------");
                            }
                        }
                        break;
                    
                    //Lease History
	                case 13:
                        List<Lease> leaseHistory = repo.listLeaseHistory();
                        if (leaseHistory.isEmpty()) {
                            System.out.println("No lease history available.");
                        } else {
                            System.out.println("Lease History:");
                            for (Lease lease1 : leaseHistory) {
                                System.out.println("Lease ID: " + lease1.getLeaseID());
                                System.out.println("Vehicle ID: " + lease1.getVehicleID());
                                System.out.println("Customer ID: " + lease1.getCustomerID());
                                System.out.println("Start Date: " + lease1.getStartDate());
                                System.out.println("End Date: " + lease1.getEndDate());
                                System.out.println("Type: " + lease1.getType());
                                System.out.println("-----------------------------------");
                            }
                        }
                        break;

                    //Record Payment of customer
	                case 14:
	                    System.out.print("Enter Lease ID: ");
	                    int leasePayID = sc.nextInt();
	                    System.out.print("Enter Payment Amount: ");
	                    double payAmt = sc.nextDouble();
	                    Lease payLease = new Lease();
	                    payLease.setLeaseID(leasePayID);
	                    repo.recordPayment(payLease, payAmt);
	                    System.out.println("Payment recorded!");
	                    break;

	                //Payment History
	                case 15:
	                    System.out.print("Enter Customer ID: ");
	                    int custPayID = sc.nextInt();
	                    repo.getPaymentHistory(custPayID).forEach(p ->
	                            System.out.println("Payment ID: " + p.getPaymentID() + ", Amount: ₹" + p.getAmount()));
	                    break;

	                //Total Revenue 
	                case 16:
	                    double revenue = repo.calculateTotalRevenue();
	                    System.out.println("Total Revenue from Payments: ₹" + revenue);
	                    break;

	                //Exits the stystem
	                case 0:
	                    System.out.println("Exiting system. Goodbye!");
	                    break;

	                //Invalid input
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        } catch (CarNotFoundException | CustomerNotFoundException | LeaseNotFoundException e) {
	            System.out.println("Error: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("Unexpected error: " + e.getMessage());
	        }

	    } while (choice != 0);

	    sc.close(); //closes the scanner
	}

}