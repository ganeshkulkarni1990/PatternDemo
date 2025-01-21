package com.test;

import java.util.ArrayList;
import java.util.List;

public class DriverRegisterService {

	
	List<Cab> cabs;
	
	public DriverRegisterService()
	{
		cabs = new ArrayList<Cab>();
	}
	
	public void registerCab(Cab b)
	{
		cabs.add(b);
	}
	
}
