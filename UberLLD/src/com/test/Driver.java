package com.test;

public class Driver {

	int id;
	String name;
	Cab cab;
	boolean isAvailable;
	
	TripService tripService;
	
	public Driver()
	{
		tripService = new TripService();
	}
	
	public void startTrip(Location y){
		//tripService.startTrip(this, rider);
	}
	
	public Trip getBooking()
	{
		//return tripService.startTrip(cab, b);
		return null;
	}
	
	public void endTrip(){
		
	}
	
	public void RegisterCab()
	{
		
	}
	
	public void setIsAvailable(boolean status)
	{
		isAvailable = status;
	}
}
