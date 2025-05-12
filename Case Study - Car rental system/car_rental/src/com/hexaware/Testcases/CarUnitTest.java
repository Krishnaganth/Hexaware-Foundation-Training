package com.hexaware.Testcases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.hexaware.dao.ICarLeaseRepositoryImpl;
import com.hexaware.entity.Car;
import com.hexaware.util.DBConnection;

/*
 * Unit test class for testing Car functionalities
 * in the Car Rental System using JUnit 5.
 */

public class CarUnitTest {

    private static ICarLeaseRepositoryImpl carService;

    /*
     * This method runs once before all tests.
     * It initializes the service object for testing.
     */
    @BeforeAll
    public static void setup() {
        carService = new ICarLeaseRepositoryImpl();
    }

    /*
     * Test to verify database connection is successfully established.
     */
    
    @Test
    void testDBConnection() {
        try {
            Connection conn = DBConnection.getConnection();
            assertNotNull(conn, "DB connection failed");
            System.out.println("Database connection test passed.");
        } catch (Exception e) {
            fail("Exception occurred while testing DB connection: " + e.getMessage());
        }
    }

    /*
     * Test to check if a car is added and listed under available cars.
     */
    
    @Test
    void testAddCarSuccess() {
        try {
            Car car = new Car(0, "Nissan", "Altima", 2023, 52.00, "available", 7, 1200);
            carService.addCar(car);

            boolean exists = carService.listAvailableCars()
                .stream()
                .anyMatch(c -> c.getMake().equals("Nissan") && c.getModel().equals("Altima"));

            assertTrue(exists, "Car should be added and listed as available");
        } catch (Exception e) {
            fail("Exception occurred while adding car: " + e.getMessage());
        }
    }
}
