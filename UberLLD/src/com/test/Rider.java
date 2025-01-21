package com.test;

import java.util.List;

public class Rider {

	int id;
	String name;
	Address address;
	String mobileNumber;
	Location location;
	
	RiderService riderService;
	
	public void searchRides(){
		List<Cab> cabs = riderService.getAllRiders(location);
		cabs.get(0).book(this);
	}
	
	public List<Booking> getBookings()
	{
		//TransactionService.getAllBookings(this);
		return null;
	}
}
