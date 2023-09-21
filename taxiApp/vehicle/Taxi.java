package taxiApp.vehicle;

import taxiApp.users.Driver;

public class Taxi {

	public String name;
	public String number;
	public String type;
	public byte seatCapacity;
	public boolean availableStatus;
	private Driver driver;
	
	public Taxi(String name,String number,String type,byte seatCapacity,boolean availableStatus,Driver driver){
		this.name=name;
		this.number=number;
		this.type=type;
		this.seatCapacity=seatCapacity;
		this.availableStatus=availableStatus;
		this.driver=driver;
	}
	
	public Driver getDriver() {
		return driver;
	}
}
