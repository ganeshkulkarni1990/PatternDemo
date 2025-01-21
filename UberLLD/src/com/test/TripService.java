package com.test;

import java.util.HashMap;

public class TripService {

	static int tripId = 1;
	HashMap<Integer, Trip> onGoingtrips;
	HashMap<Integer,Trip> completedTrips;
	
	public TripService(){
		onGoingtrips = new HashMap<Integer,Trip>();
		completedTrips = new HashMap<Integer,Trip>();
	}
	
	public void startTrip(Cab cab,Rider rider)
	{
		Trip trip = new Trip();
		trip.tripId = tripId++;
		trip.rider =  rider;
		trip.cab = cab;
		onGoingtrips.put(tripId,trip);
	}
	
	public void completeTrip(int tripId)
	{
		Trip t = completedTrips.get(tripId);
		onGoingtrips.remove(t);
		completedTrips.put(tripId,t);
	}
}
