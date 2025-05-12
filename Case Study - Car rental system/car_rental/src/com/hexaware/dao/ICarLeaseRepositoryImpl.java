package com.hexaware.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Car;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Lease;
import com.hexaware.entity.Payment;
import com.hexaware.myexceptions.CarNotFoundException;
import com.hexaware.myexceptions.CustomerNotFoundException;
import com.hexaware.myexceptions.LeaseNotFoundException;
import com.hexaware.util.DBConnection;

public class ICarLeaseRepositoryImpl implements ICarLeaseRepository {

    private Connection connection;

    public ICarLeaseRepositoryImpl() {
        this.connection = DBConnection.getConnection();
    }

    // Car Management
    @Override
    public void addCar(Car car) {
        String query = "INSERT INTO Vehicle (make, model, year, dailyRate, status, passengerCapacity, engineCapacity) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getDailyRate());
            stmt.setString(5, car.getStatus());
            stmt.setInt(6, car.getPassengerCapacity());
            stmt.setInt(7, car.getEngineCapacity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCar(int carID) {
        String query = "DELETE FROM Vehicle WHERE vehicleID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, carID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> listAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Vehicle WHERE status = 'available'";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Car car = new Car(
                    rs.getInt("vehicleID"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getInt("year"),
                    rs.getDouble("dailyRate"),
                    rs.getString("status"),
                    rs.getInt("passengerCapacity"),
                    rs.getInt("engineCapacity")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public List<Car> listRentedCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Vehicle WHERE status = 'notAvailable'";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Car car = new Car(
                    rs.getInt("vehicleID"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getInt("year"),
                    rs.getDouble("dailyRate"),
                    rs.getString("status"),
                    rs.getInt("passengerCapacity"),
                    rs.getInt("engineCapacity")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public Car findCarById(int carID) throws CarNotFoundException {
        String query = "SELECT * FROM Vehicle WHERE vehicleID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, carID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Car(
                    rs.getInt("vehicleID"),
                    rs.getString("make"),
                    rs.getString("model"),
                    rs.getInt("year"),
                    rs.getDouble("dailyRate"),
                    rs.getString("status"),
                    rs.getInt("passengerCapacity"),
                    rs.getInt("engineCapacity")
                );
            } else {
                throw new CarNotFoundException("Car with ID " + carID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CarNotFoundException("Database error while retrieving car.");
        }
    }

    // Customer Management
    @Override
    public int addCustomer(Customer customer) {
        String query = "INSERT INTO Customer (firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void removeCustomer(int customerID) {
        String query = "DELETE FROM Customer WHERE customerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Customer> listCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customer";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("customerID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("phoneNumber")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer findCustomerById(int customerID) throws CustomerNotFoundException {
        String query = "SELECT * FROM Customer WHERE customerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getInt("customerID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("phoneNumber")
                );
            } else {
                throw new CustomerNotFoundException("Customer with ID " + customerID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerNotFoundException("Database error while retrieving customer.");
        }
    }

    // Lease Management
    @Override
    public Lease createLease(int customerID, int carID, Date startDate, Date endDate) {
        String query = "INSERT INTO Lease (vehicleID, customerID, startDate, endDate, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, carID);
            stmt.setInt(2, customerID);
            stmt.setDate(3, new java.sql.Date(startDate.getTime()));
            stmt.setDate(4, new java.sql.Date(endDate.getTime()));
            stmt.setString(5, "Daily"); // or "Monthly" based on logic
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int leaseID = rs.getInt(1);
                return new Lease(leaseID, carID, customerID, startDate, endDate, "Daily");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void returnCar(int leaseID) throws LeaseNotFoundException {
        String query = "UPDATE Vehicle SET status = 'available' WHERE vehicleID = (SELECT vehicleID FROM Lease WHERE leaseID = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, leaseID);
            int updated = stmt.executeUpdate();
            if (updated == 0) throw new LeaseNotFoundException("Lease ID not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LeaseNotFoundException("Error returning car.");
        }
    }

    @Override
    public List<Lease> listActiveLeases() {
        List<Lease> leases = new ArrayList<>();
        String query = "SELECT * FROM Lease WHERE endDate >= CURRENT_DATE";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                leases.add(new Lease(
                    rs.getInt("leaseID"),
                    rs.getInt("vehicleID"),
                    rs.getInt("customerID"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    rs.getString("type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leases;
    }

    @Override
    public List<Lease> listLeaseHistory() {
        List<Lease> leases = new ArrayList<>();
        String query = "SELECT * FROM Lease";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                leases.add(new Lease(
                    rs.getInt("leaseID"),
                    rs.getInt("vehicleID"),
                    rs.getInt("customerID"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    rs.getString("type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leases;
    }

    // Payment Handling
    @Override
    public void recordPayment(Lease lease, double amount) {
        String query = "INSERT INTO Payment (leaseID, paymentDate, amount) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, lease.getLeaseID());
            stmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> getPaymentHistory(int customerID) {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT p.* FROM Payment p JOIN Lease l ON p.leaseID = l.leaseID WHERE l.customerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                payments.add(new Payment(
                    rs.getInt("paymentID"),
                    rs.getInt("leaseID"),
                    rs.getDate("paymentDate"),
                    rs.getDouble("amount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public double calculateTotalRevenue() {
        String query = "SELECT SUM(amount) as total FROM Payment";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
