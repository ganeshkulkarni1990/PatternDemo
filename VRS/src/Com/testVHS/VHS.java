package Com.testVHS;

import java.text.SimpleDateFormat;
/*The system will support the renting of different automobiles like cars, trucks, SUVs, vans, and motorcycles.
Each vehicle should be added with a unique barcode and other details, including a parking stall number which helps to locate the vehicle.
The system should be able to retrieve information like which user took a particular vehicle or what vehicles have been rented out by a specific user.
Users should be able to search the vehicle inventory and reserve any available vehicle.
The system should collect a late-fee for vehicles returned after the due date.

*/
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum VehicleType
{
	CAR,
	TRUCK,
	SUV,
	VAN,
	MOTORCYCLE
}

class ParkingLot
{
	int parkingLotId;
	int floor;	
}

class Vehicle
{
	long vehicleId;
	long barCode;
	boolean isAvailable;
	VehicleType type;
	ParkingLot parkingLost;
	User bookedBy;
	double rent;
	Date startDate;
	Date endDate;
	
	Vehicle(long vehicleId, long barCode,VehicleType type)
	{
		this.vehicleId = vehicleId;
		this.barCode = barCode;
		this.type = type;
		//by default its available when created later can be added to parking lot
	}
	public void setRent(int rent)
	{
		this.rent = rent;
	}
	
	public void setUser(User user)
	{
		this.bookedBy = user;
	}
	
	public String toString()
	{
		return this.vehicleId+" "+type;
	}
}

enum BookingStaus
{
	NOT_BOOKED,
	BOOKED,
	COMPLETED
}

class Trasaction
{
	long trasactionId;
	long vehicleId;
	long userId;
	BookingStaus status;
	
	public Trasaction(long id,long id1,long id2)
	{
		this.trasactionId = id2;
		this.vehicleId = id1;
		this.userId = id2;
	}
	
	public void updateBookingStatus(BookingStaus status)
	{
		this.status = status;
	}
	
	public BookingStaus getUpdateBookingStatus()
	{
		return status;
	}
	
	public String toString()
	{
		return this.trasactionId+" "+vehicleId+" "+userId+" "+status;
	}
}

class User
{
	long userId;
	String email;	
	
	User(long userId, String email)
	{
		this.userId = userId;
		this.email = email;
	}
	
	public String toString()
	{
		return this.userId+" "+this.email;
	}
	
	//user functionality 
	//user will create the vehicle and search //criteria based filtering
	//searchVehicle
	//bookVehicle
	//submit vehicle
	//payRent
	
}

interface VHS1
{
	int bookVehicle(Vehicle v);
	List<Vehicle> getFreeVehicles();
	List<Vehicle> getBookedVehicles();
	Vehicle serachVehicle(Vehicle v);
	void submitVehicle(Vehicle v);
}

class VHSImpl
{
	int name;
	String address;
	int id; //reg id
	
	List<Vehicle> inventory; //containing all the vehicles will check based on isAvailable
	List<User> users;
	List<Trasaction> transactionList;
	
	static long tid = 10000;
	VHSImpl()
	{
		inventory = new ArrayList<Vehicle>();
		users = new ArrayList<User>();
		transactionList = new ArrayList<Trasaction>();
	}
	
	public int addVehicle(Vehicle v)
	{
		//check if already there else add
		inventory.add(v);
		return 0;
	}
	
	public void removeVehicle(Vehicle v)
	{
		//check if already there else add
		inventory.remove(v);
	}
	
	public int registerUser(User u)
	{
		//if already contains the user return false;
		//else register
		users.add(u);
		return 0;
	}
	
	public List<Vehicle> searchVehicleByType(VehicleType type) //returns all the the vehicles both booked and non booked
	{
		List<Vehicle> ans = new ArrayList<Vehicle>();
		for(Vehicle v : inventory)
		{
			if(v.type == type)
			{
				ans.add(v);
			}
		}
		return ans;
	}
	
	public List<Vehicle> searchAvailableVehicle(VehicleType type)
	{
		List<Vehicle> ans = new ArrayList<Vehicle>();
		for(Vehicle v : inventory)
		{
			if(v.type == type && v.isAvailable == true)
			{
				ans.add(v);
			}
		}
		return ans;
	}
	
	public List<Vehicle> searchAvailableVehicle()
	{
		List<Vehicle> ans = new ArrayList<Vehicle>();
		for(Vehicle v : inventory)
		{
			if(v.isAvailable == true)
			{
				ans.add(v);
			}
		}
		return ans;
	}
	
	public List<Vehicle> getBookedVehicles()
	{
		List<Vehicle> ans = new ArrayList<Vehicle>();
		for(Vehicle v : inventory)
		{
			if(v.isAvailable == false)
			{
				ans.add(v);
			}
		}
		return ans;
	}
	
	public List<Vehicle> getBookedVehicles(VehicleType type)
	{
		List<Vehicle> ans = new ArrayList<Vehicle>();
		for(Vehicle v : inventory)
		{
			if(v.type == type && v.isAvailable == false)
			{
				ans.add(v);
			}
		}
		return ans;
	}
	
	public double submitVehicle(Vehicle v)
	{
		v.isAvailable = true;
		inventory.add(v);
		User u = v.bookedBy;
		for(Trasaction t: transactionList)
		{
			if(t.vehicleId == v.vehicleId)
			{
				t.updateBookingStatus(BookingStaus.COMPLETED);
			}
		}
		return calcRent(v);
	}
	
	public double calcRent(Vehicle v)
	{
		Date sDate = v.startDate;
		Date eDate = v.endDate;
		
		if(eDate.compareTo(new Date())>1) //get Current Date and checkif exceeds booking date;
		{
			long diff = eDate.getTime() - sDate.getTime();
			System.out.println("Vehicle is returned after the booking end date adding fine 200.00");
			return diff + 200.00;
		}
		else
		{
			long diff = eDate.getTime() - sDate.getTime();
			return diff * v.rent;
		}
	}
	
	public long bookVehicle(Vehicle v)
	{
		Trasaction t =  new Trasaction(tid,v.vehicleId,v.bookedBy.userId);
		t.updateBookingStatus(BookingStaus.BOOKED);
		transactionList.add(t);
		v.isAvailable = false; //booked vehicle
		return t.trasactionId;
	}
	
	public List<Trasaction> showTransactionHistory(long userId)
	{
		List<Trasaction> ans = new ArrayList<Trasaction>();
		for(Trasaction t: transactionList)
		{
			if(t.userId == userId)
				ans.add(t);
		}
		return ans;
	}
}

class Util
{
	static double MOTORCYCLE_PENALTY = 100;
	static double CAR_PENALTY = 200;
	static double TRUCK_PENALTY = 300;
	static double VAN_PENALTY = 200;
	static double SUV_PENALTY = 150;
	
	static long barCode = 100001;
	static long getUqiqueBarcode()
	{
		barCode++;
		return barCode;
	}
}

public class VHS
{
	public static void main(String ...args)throws Exception
	{
		VHSImpl rs = new VHSImpl();
		User u1 = new User(100,"Ganesh@gmail.com");
		if(rs.registerUser(u1)==1)
		{
			System.out.print("Already exists");
		}
		User u2 = new User(101,"abc@gmail.com");
		if(rs.registerUser(u2)==1)
		{
			System.out.print("Already exists");
		}
		
		//create Dummy Vehicle
		//rent charges per day
		
		System.out.println("Creating the Dummy vehicles and Adding it to inventory");
		Vehicle v1 = new Vehicle(1,Util.getUqiqueBarcode(),VehicleType.CAR);
		v1.setRent(100);
		v1.isAvailable =  true;
		Vehicle v2 = new Vehicle(2,Util.getUqiqueBarcode(),VehicleType.MOTORCYCLE);
		v2.setRent(50);
		v2.isAvailable =  true;
		Vehicle v3 = new Vehicle(3,Util.getUqiqueBarcode(),VehicleType.TRUCK);
		v3.setRent(200);
		v3.isAvailable =  true;
		Vehicle v4 = new Vehicle(4,Util.getUqiqueBarcode(),VehicleType.SUV);
		v4.setRent(150);
		v4.isAvailable =  true;
		Vehicle v5 = new Vehicle(5,Util.getUqiqueBarcode(),VehicleType.VAN);
		v5.setRent(150);
		v5.isAvailable =  true;
		Vehicle v6 = new Vehicle(6,Util.getUqiqueBarcode(),VehicleType.MOTORCYCLE);
		v6.setRent(50);
		v6.isAvailable =  true;
	
		rs.addVehicle(v1);
		rs.addVehicle(v2);
		rs.addVehicle(v3);
		rs.addVehicle(v4);
		rs.addVehicle(v5);
		rs.addVehicle(v6);
	
		System.out.println("Added all the Vehicles to Inventory...");
		
		System.out.println("Listing Current Available Inventory");
		
		List<Vehicle> availables = rs.searchAvailableVehicle();
		System.out.println(availables);
		
		// dummy inventory is ready
		
		//user searching the vehicle and booking the vehicle
		
		System.out.println("User:"+u1+" Searching CAR Vehicles");
		List<Vehicle> list = rs.searchVehicleByType(VehicleType.CAR);
		System.out.println("User:"+u1+" Got all available CAR Vehicles");
		Vehicle v = list.get(0);
		v.setUser(u1);
	
		String sDate1="21/04/2020";  
	    Date sDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
	    
	    String eDate1="25/04/2020";
	    Date eDate = new SimpleDateFormat("dd/MM/yyyy").parse(eDate1);  
	    
	    v.startDate = sDate;
		v.endDate = eDate;
		
		rs.bookVehicle(v);
		
		System.out.println("User:"+u1+" Has booked CAR Vehicles: "+ v);
		
		List<Vehicle> booked = rs.getBookedVehicles();
		System.out.println("Booked vehicles are:"+ booked);
		
		System.out.println("Listing all the Booked Vehicles: "+booked);
				
		availables = rs.searchAvailableVehicle();
		System.out.println("Listing all Available Vehicles: "+availables);
		
		System.out.println("User is submmting the Vehicle: "+booked);
		
		double rentMoney = rs.submitVehicle(v);
		System.out.println("User "+ u1 +" paid : "+rentMoney);
		
		availables = rs.searchAvailableVehicle();
		System.out.println(availables);
		
		
		System.out.println("Showing Trasaction History for User:"+u1);
		System.out.println(rs.showTransactionHistory(u1.userId));
		//user1 is listing all his booked vehicles
		
	}
}


