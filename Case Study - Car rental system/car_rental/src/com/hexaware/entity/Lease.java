package com.hexaware.entity;
import java.util.Date;

public class Lease {
	
	/* Attributes of class Lease */
	
	private int leaseID;
    private int vehicleID;
    private int customerID;
    private Date startDate;
    private Date endDate;
    private String type;
    
    /*Parameterized constructor */
    
	public Lease(int leaseID, int vehicleID, int customerID, Date startDate, Date endDate, String type) {
		super();
		this.leaseID = leaseID;
		this.vehicleID = vehicleID;
		this.customerID = customerID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
	}
	
	/* Non-parameterized constructor */	

	public Lease() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/* Getters and Setter */
	
	public int getLeaseID() {
		return leaseID;
	}

	public void setLeaseID(int leaseID) {
		this.leaseID = leaseID;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/* toString */

	@Override
	public String toString() {
		return "Lease [leaseID=" + leaseID + ", vehicleID=" + vehicleID + ", customerID=" + customerID + ", startDate="
				+ startDate + ", endDate=" + endDate + ", type=" + type + "]";
	}

}
