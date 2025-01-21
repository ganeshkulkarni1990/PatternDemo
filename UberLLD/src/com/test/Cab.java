package com.test;

public class Cab {

	int id;
	CarType type;
	String driveName;
	boolean isCabAvailable;
	Trip currentTrip;
	Location currentLocation;
	
	public void book(Rider rider)
	{
		this.isCabAvailable = false;
	}
	
	public boolean isCabAvailable()
	{
		return isCabAvailable;
	}
	
}
