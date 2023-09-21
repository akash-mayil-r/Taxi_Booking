package taxiApp.details;

import taxiApp.users.Passenger;
import taxiApp.util.TaxiUtil.TravelStatus;
import taxiApp.vehicle.Taxi;
import taxiApp.details.Ticket;
import bankServices.Transaction;

public class Ticket {

	public String ticketId;
	public String userEmailId;
	public String fromLocation;
	public String toLocation;
	public String date;
	public String time;
	public double distance;
	public Passenger passenger;
	public TravelStatus travelStatus;
	public Taxi taxi;
	public Transaction transaction;
	
	public Ticket(String ticketId,String userEmailId,String fromLocation,String toLocation,String date,String time,double distance,Passenger passenger,TravelStatus travelStatus,Taxi taxi,Transaction transaction){
		this.ticketId=ticketId;
		this.userEmailId=userEmailId;
		this.fromLocation=fromLocation;
		this.toLocation=toLocation;
		this.date=date;
		this.time=time;
		this.distance=distance;
		this.passenger=passenger;
		this.travelStatus=travelStatus;
		this.taxi=taxi;
		this.transaction=transaction;
	}
	
	public Ticket() {
		// TODO Auto-generated constructor stub
	}

}