package taxiApp.users;

import java.util.Scanner;

import taxiApp.util.MainUtil;
import taxiApp.util.TaxiUtil;
import taxiApp.util.TicketUtil;
import taxiApp.collectionSystem.CollectionData;


public class Driver extends User {
	
	public String licenseNumber;
	public boolean availableStatus;
	
	Scanner scan=new Scanner(System.in);
	MainUtil mainUtilObject=new MainUtil();
	CollectionData dataObject=CollectionData.getInstance();
	TaxiUtil taxiUtilObject=new TaxiUtil();
	TicketUtil ticketUtilObject=new TicketUtil();
	
	public Driver(String name,String phoneNumber,String emailId,String password,String licenseNumber,boolean availableStatus) {
		super(name,phoneNumber,emailId,password);
		this.licenseNumber=licenseNumber;
		this.availableStatus=availableStatus;
	}
	
}