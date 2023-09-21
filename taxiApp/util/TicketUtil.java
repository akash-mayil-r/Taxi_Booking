package taxiApp.util;

import java.util.ArrayList;

import taxiApp.collectionSystem.CollectionData;
import taxiApp.details.Ticket;
import taxiApp.util.TaxiUtil.TravelStatus;

public class TicketUtil {

	public void printTicketDetails(String ticketId){
		if(CollectionData.getInstance().getTickets().containsKey(ticketId)){
			Ticket ticket=CollectionData.getInstance().getTicket(ticketId);	
			System.out.println("\n\t-----------------Ticket Details-------------------");
			System.out.println("\n\tDate : "+ticket.date);
			//System.out.println("\t\tTime : "+ticket.time);
			System.out.println("\n\tTime Duration 	: "+new DateAndTime().timeCalculate(ticket.distance,45));
			System.out.println("\n\tTaxi Number 	: "+ticket.taxi.number);
			System.out.println("\n\tTicket ID 	: "+ticket.ticketId);
			System.out.println("\n\tFrom Location 	: "+ticket.fromLocation);
			System.out.println("\n\tTo Location 	: "+ticket.toLocation);
			System.out.println("\n\tDistance in KM	: "+ticket.distance);
			System.out.println("\n\tTransaction ID 	: "+ticket.transaction.id);
			System.out.println("\n\tFare in RS	: "+ticket.transaction.fare);
			System.out.println("\n\tPassenger Count : "+ticket.passenger.passengerCount);
			System.out.println("\n\t--------------------------------------------------");
		}
		else
			System.out.println("\n\tTicket Not Found");
	}
	
	public void printHistory(ArrayList<Ticket> tickets){
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\tTicket ID\tFrom Location\tTo Location\tDistance\tFare\tTravelStatus Passenger Count\tTaxi Details\t\tDriver Details");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (Ticket ticket:tickets) {
			System.out.print("\t"+ticket.ticketId+"\t");
			System.out.print(ticket.fromLocation+" \t");
			System.out.print(ticket.toLocation+"\t");
			System.out.print(ticket.distance+"  \t");
			System.out.print(ticket.transaction.fare+"\t");
			if (ticket.travelStatus == TravelStatus.BOOKED)
				System.out.print("Booked\t");
			else if(ticket.travelStatus == TravelStatus.CANCELLED)
				System.out.print("Cancelled\t");
			else if(ticket.travelStatus == TravelStatus.COMPLETED)
				System.out.print("Completed\t");
			System.out.print(ticket.passenger.passengerCount+"\t");
			System.out.print("Number : "+ticket.taxi.number+"\t");
			System.out.print("Name : "+ticket.taxi.getDriver().name+"\t");
			System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tName : "+ticket.taxi.name+"\t");
			System.out.println("Phone Number : "+ticket.taxi.getDriver().phoneNumber+"\t");
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
}
