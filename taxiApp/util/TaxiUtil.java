package taxiApp.util;

import java.util.ArrayList;
import java.util.Scanner;

import taxiApp.vehicle.Taxi;
import taxiApp.collectionSystem.CollectionData;
import taxiApp.details.Ticket;
import taxiApp.users.Customer;

public class TaxiUtil {

	Scanner scan=new Scanner(System.in);
	CollectionData dataObject=CollectionData.getInstance();
	MainUtil mainUtilObject=new MainUtil();
	TicketUtil ticketUtilObject=new TicketUtil();
	public enum TravelStatus{
		BOOKED,
		CANCELLED,
		COMPLETED
	}
	
    public double fareCalculate(String taxiType,double distance){
		if(taxiType.equals("Hatchback"))
			return distance*10;
		else if(taxiType.equals("Sedan"))
			return distance*11;
		else
			return distance*12;
	}
    
	public boolean requestTaxi(Taxi taxi){
		return taxi.getDriver().availableStatus;
	}
	
	public void stopTravel(Customer currentUser,TravelStatus travelStatus){
		boolean valid=true;
		ticketUtilObject.printHistory(dataObject.getTicketHistory(currentUser,TaxiUtil.TravelStatus.BOOKED));
		while (valid) {
			System.out.print("\n\tEnter Taxi Ticket ID : ");
			String ticketId=scan.next();
			if(currentUser.getBookedTickets().contains(ticketId)) {
				if(dataObject.verifyTicketId(ticketId)){
					if(travelStatus==TravelStatus.CANCELLED)
						currentUser.setCancelledTickets(ticketId);
					else if(travelStatus==TravelStatus.COMPLETED)
						currentUser.setFinishedTickets(ticketId);
					currentUser.getBookedTickets().remove(ticketId);
					setTravelStatus(ticketId,travelStatus,false);
					//data.ticketMap.get(ticketId).transactionDetails.mo
					valid=false;
				}
				else{
					System.out.println("\n\tInvalid Ticket ID");
					valid=mainUtilObject.isContinueOrBack();
				}
			}
			else{
				System.out.println("\n\tInvalid Ticket ID");
				valid=mainUtilObject.isContinueOrBack();
			}
		}
	}
	
	public void setTravelStatus(String ticketId,TravelStatus travelStatus,boolean availableStatus){
		if(dataObject.getTickets().containsKey(ticketId)){
			Ticket ticket=dataObject.getTicket(ticketId);
			if(ticket.travelStatus == TravelStatus.BOOKED && (travelStatus == TravelStatus.CANCELLED || travelStatus == TravelStatus.COMPLETED)){
				ticket.travelStatus=travelStatus;
				Taxi taxi=dataObject.getTaxi(ticket.taxi.number);
				taxi.availableStatus=!availableStatus;
				taxi.getDriver().availableStatus=!availableStatus;
				if(travelStatus == TravelStatus.BOOKED)
					System.out.println("\n\tTaxi Cancelled Successfully..");
				else if(travelStatus == TravelStatus.COMPLETED) {
					dataObject.getTaxiAndLocation().replace(ticket.taxi.number,ticket.toLocation);
					
					System.out.println("\n\tTaxi Finished Successfully..");
				}
			}
			else if((ticket.travelStatus == TravelStatus.CANCELLED || ticket.travelStatus == TravelStatus.COMPLETED)){
				if(travelStatus == TravelStatus.BOOKED)
					System.out.println("\n\tTaxi is Already Stoped , Booking is Not Possible");
				else if(travelStatus == TravelStatus.CANCELLED)
					System.out.println("\n\tTaxi is Already Stoped , Cancel is Not Possible");
				else if(travelStatus == TravelStatus.COMPLETED)
					System.out.println("\n\tTaxi is Already Stoped , Finish is Not Possible");
			}
			if(travelStatus == TravelStatus.BOOKED){
				ticket.taxi.availableStatus=availableStatus;
				ticket.taxi.getDriver().availableStatus=availableStatus;
			}
		}
		else
			System.out.println("\n\tTicket Not Found");
	}

	boolean isTravelEmpty(Customer currentUser,TravelStatus travelStatus){
		ArrayList<Ticket> tickets= dataObject.getTicketHistory(currentUser,travelStatus);
		if(tickets.isEmpty())
			return true;
		else
			return false;
	}
}