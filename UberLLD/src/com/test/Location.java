package com.test;

public class Location {

	int x;
	int y;
	
	int distance(Location l1)
	{
		return (l1.x-this.x)*(l1.x-this.x) - (l1.y-this.y)*(l1.y-this.y);
	}
}
