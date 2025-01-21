package com.test;

import java.util.ArrayList;
import java.util.List;

public class RiderService {

	final static double distance = 5d;
	
	List<Cab> cabs;
	
	public List<Cab> getAllRiders(Location l)
	{
		List<Cab> list = new ArrayList<Cab>();
		for(Cab c : cabs)
		{
			if(l.distance(c.currentLocation) <= distance && c.isCabAvailable){
				list.add(c);
			}
		}
		return list;
	}
	
	public List<Cab> getAllSedanCabs(Location l)
	{
		List<Cab> list = new ArrayList<Cab>();
		for(Cab c : cabs)
		{
			if(l.distance(c.currentLocation) <= distance && c.isCabAvailable && c.type==CarType.SEDAN){
				list.add(c);
			}
		}
		return list;
	}
	
	public List<Cab> getAllMiniCabs(Location l)
	{
		List<Cab> list = new ArrayList<Cab>();
		for(Cab c : cabs)
		{
			if(l.distance(c.currentLocation) <= distance && c.isCabAvailable && c.type==CarType.MINI){
				list.add(c);
			}
		}
		return list;
	}
}
