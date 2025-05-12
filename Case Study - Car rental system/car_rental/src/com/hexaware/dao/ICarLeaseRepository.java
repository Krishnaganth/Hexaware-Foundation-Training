package com.hexaware.dao;

import java.sql.Date;
import java.util.List;

import com.hexaware.entity.Car;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Lease;
import com.hexaware.entity.Payment;
import com.hexaware.myexceptions.CarNotFoundException;
import com.hexaware.myexceptions.CustomerNotFoundException;
import com.hexaware.myexceptions.LeaseNotFoundException;

public interface ICarLeaseRepository {
	
	
    // Car Management

    public void addCar(Car car);
    public void removeCar(int carId);
    public List<Car> listAvailableCars();
    public List<Car> listRentedCars();
    public Car findCarById(int carId) throws CarNotFoundException;

    // Customer Management

    public int addCustomer(Customer customer);
    public void removeCustomer(int customerId);
    public List<Customer> listCustomers();
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException;

    // Lease Management

    public Lease createLease(int customerId, int carId, Date startDate, Date endDate);
    public void returnCar(int leaseId) throws LeaseNotFoundException;
    public List<Lease> listActiveLeases();
    public List<Lease> listLeaseHistory();

    // Payment Handling

    public void recordPayment(Lease lease, double amount);
    public List<Payment> getPaymentHistory(int customerId);
    public double calculateTotalRevenue();

}

