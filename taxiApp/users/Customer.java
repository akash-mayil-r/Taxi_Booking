package taxiApp.users;

import java.util.ArrayList;
import java.util.Scanner;

import taxiApp.vehicle.Taxi;
import taxiApp.collectionSystem.CollectionData;
import taxiApp.details.Ticket;
import bankServices.Transaction;
import bankServices.TransactionUtil;
import taxiApp.users.Customer;
import taxiApp.util.DateAndTime;
import taxiApp.util.LocationUtil;
import taxiApp.util.MainUtil;
import taxiApp.util.TaxiUtil;
import taxiApp.util.TicketUtil;
import taxiApp.util.TaxiUtil.TravelStatus;
import taxiApp.util.UserUtil;

public class Customer extends User {
	
	Scanner scan=new Scanner(System.in);
	UserUtil userUtilObject=new UserUtil();
	MainUtil mainUtilObject=new MainUtil();
	TaxiUtil taxiUtilObject=new TaxiUtil();
	TicketUtil ticketUtilObject=new TicketUtil();
	LocationUtil locationUtilObject=new LocationUtil();
	DateAndTime dateAndTimeObject=new DateAndTime();
	CollectionData dataObject=CollectionData.getInstance();
	TransactionUtil transactionUtilObject=new TransactionUtil();
	private ArrayList<String> bookedTickets;
	private ArrayList<String> cancelledTickets;
	private ArrayList<String> finishedTickets;
	
	public Customer(String name,String phoneNumber,String emailId,String password) {
		super(name,phoneNumber,emailId,password);
		this.bookedTickets=new ArrayList<String>();
		this.cancelledTickets=new ArrayList<String>();
		this.finishedTickets=new ArrayList<String>();
	}
	
	public ArrayList<String> getBookedTickets() {
		return bookedTickets;
	}
	
	public ArrayList<String> getCancelledTickets() {
		return cancelledTickets;
	}
	
	public ArrayList<String> getFinishedTickets() {
		return finishedTickets;
	}
	
	public void setBookedTickets(String ticketId) {
		bookedTickets.add(ticketId);
	}
	
	public void setCancelledTickets(String ticketId) {
		cancelledTickets.add(ticketId);
	}
	
	public void setFinishedTickets(String ticketId) {
		finishedTickets.add(ticketId);
	}
	
	public void bookTaxi(){
		boolean valid=true;
		MainUtil mainUtilObject=new MainUtil();
		String fromLocation=locationUtilObject.getLocation();
		System.out.println("\n\tSelected PickUp Location : "+fromLocation+"\n");
		String toLocation=locationUtilObject.getLocation(fromLocation);
		System.out.print("\n\tSelected Drop Location : "+toLocation+"\n");
		while(valid){
			boolean validBook=true;
			ArrayList<Taxi> availableTaxi=dataObject.searchTaxi(fromLocation);
			ArrayList<Double> fare=new ArrayList<>();
			if(availableTaxi.isEmpty()){
				System.out.println("\n\tTaxies Not Available...");
				validBook=false;
				valid=false;
				availableTaxi.clear();
				fare.clear();
			}			
			while(validBook){
				
				int c=0,taxiNo=0;
			
				double distance=Double.parseDouble(String.format("%.3f",locationUtilObject.distanceCalculate(dataObject.getLocationMap().get(fromLocation),dataObject.getLocationMap().get(toLocation))));
				
				System.out.println("\n\tTravel Distance "+fromLocation+" to "+toLocation+" is "+distance+" KM ");

				System.out.println("\n\t---------------Available Taxies from "+fromLocation+"------------");
				for (Taxi taxi:availableTaxi) {
					fare.add(Double.parseDouble(String.format("%.2f",taxiUtilObject.fareCalculate(taxi.type,distance))));
					System.out.println("\n\t"+(++c)+". "+taxi.type+"  RS."+Math.round(taxiUtilObject.fareCalculate(taxi.type,distance))+"\tSeat Capacity : "+taxi.seatCapacity);
				}
				System.out.print("\n\t---------------------------------------------------------------------\n\tSelect Taxi : ");
				try{
					taxiNo=scan.nextInt();
				}
				catch(Exception e){
					scan.nextLine();
				}
				if(taxiNo>0 && taxiNo<=c){
					if(taxiUtilObject.requestTaxi(availableTaxi.get(taxiNo-1))){
						Passenger passenger=userUtilObject.getPassengerCount(this,availableTaxi.get(taxiNo-1).seatCapacity);
						//System.out.println("Driver is Request Accepted\n");
						availableTaxi.get(taxiNo-1).availableStatus=false;
						availableTaxi.get(taxiNo-1).getDriver().availableStatus=false;
						Transaction transaction=transactionUtilObject.pay(fare.get(taxiNo-1));
						String ticketId=mainUtilObject.getId();
					    Ticket ticket=new Ticket(ticketId,this.emailId,fromLocation,toLocation,dateAndTimeObject.getCurrentDay(),dateAndTimeObject.getCurrentTime(),distance,passenger,TravelStatus.BOOKED,availableTaxi.get(taxiNo-1),transaction);
						dataObject.setTicket(ticket.ticketId,ticket);
						this.bookedTickets.add(ticket.ticketId);
						System.out.println("\n\tTravel Started\n");
						ticketUtilObject.printTicketDetails(ticket.ticketId);
						validBook=false;
						valid=false;
					}
					else{
						System.out.println("\n\tRequest Not Accepted");
						availableTaxi.remove(taxiNo-1);	
					}
				}			
				else{
					System.out.println("\n\tInvalid Taxi Choice");
					valid=validBook=mainUtilObject.isContinueOrBack();
				}
			}	
		}
	}
	
}