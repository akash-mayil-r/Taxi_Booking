package taxiApp.util;

import java.util.Random;
import java.util.Scanner;
import taxiApp.collectionSystem.CollectionData;
import taxiApp.users.Customer;
import taxiApp.util.TaxiUtil.TravelStatus;

public class MainUtil {
	Scanner scan=new Scanner(System.in);
	CollectionData dataObject=CollectionData.getInstance();
	public boolean isContinueOrBack(){
		byte ch=0;
		boolean valid=true;
		System.out.println("\n\t1. Continue\n\n\t2. Back");
		while(valid){
			System.out.print("\n\tEnter your Choice : ");
			try{
				ch=scan.nextByte();
				scan.nextLine();
			}
			catch(Exception e){
				scan.nextLine();
			}
			switch(ch){
				case 1:
					return true;
				case 2:
					return false;
				default:
					System.out.println("\n\tInValid Choice");
			}
		}
		return false;
	}
	
	public String getId() {
		Random random=new Random();
		int ranNum=random.nextInt((int) Math.pow(10,10));
		return String.format("%s%010d",new DateAndTime().getCurrentDate(),ranNum);
	}
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		CollectionData dataObject=CollectionData.getInstance();
		UserUtil userUtilObject=new UserUtil();
		TaxiUtil taxiUtilObject=new TaxiUtil();
		TicketUtil ticketUtilObject=new TicketUtil();
		Customer currentUser=null;
		dataObject.storeUser();
		dataObject.storeLocation();
		dataObject.storeLocationList();
		dataObject.storeTaxi();
		dataObject.storeTaxiList();
		dataObject.setTaxi();
		dataObject.storeCard();
		while(true){
			boolean valid=true;
			System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t\t\tTaxi Booking");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("\t1. SignUp\n\n\t2. SignIn\n\n\t3. Exit\n\n\tEnter the Choice : ");
			byte choice=0;
			choice=scan.nextByte();		
			switch(choice){
				case 1:
					System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("\t\t\t\t\t\t\tSignUp Page");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					Customer user=userUtilObject.signUp();
					System.out.println("\n\tThanks for Successfully Signing Up our Taxi Booking Website ...\n");
					dataObject.setUserThroughEmail(user.emailId.toLowerCase(),user);
					dataObject.setUserThroughPhone(user.phoneNumber,user); 					
				break;
				case 2:
					System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("\t\t\t\t\t\t\tSignIn Page");
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					currentUser=userUtilObject.signIn();
					if(currentUser!=null){
						System.out.println("\n\tHi "+currentUser.name+" !\n\n\tWelcome to Successfully Signed In our Taxi Booking Website ...\n");
						while(valid){
							System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							System.out.println("\t\t\t\t\t\t\tHomepage");
							System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
							System.out.print("\n\t1. Book Taxi\n\n\t2. Cancel Taxi\n\n\t3. Finish Travel\n\n\t4. Travel History\n\n\t5. SignOut\n\n\tEnter yout Choice : ");
							byte ch=0;
							try{
								ch=scan.nextByte();
								scan.nextLine();
							}
							catch(Exception e){
								scan.nextLine();
							}
							switch(ch){
								case 1:
									System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
									System.out.println("\t\t\t\t\t\t\tBooking Page");
									System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
									currentUser.bookTaxi();
								break;
								case 2:
									if(!taxiUtilObject.isTravelEmpty(currentUser,TaxiUtil.TravelStatus.BOOKED))
										new TaxiUtil().stopTravel(currentUser,TaxiUtil.TravelStatus.CANCELLED);
								break;
								case 3:
									if(!taxiUtilObject.isTravelEmpty(currentUser,TaxiUtil.TravelStatus.BOOKED))
										new TaxiUtil().stopTravel(currentUser,TaxiUtil.TravelStatus.COMPLETED);
								break;
								case 4:
									boolean validHistory=true;
									while(validHistory){
										System.out.print("\n\n\t1. Current or Upcomming Travel\n\n\t2. Completed Travel\n\n\t3. Cancelled Travel\n\n\t4. Back\n\n\tEnter your Choice : ");
										byte ch1=0;
										try{
											ch1=scan.nextByte();
											scan.nextLine();
										}
										catch(Exception e){
											scan.nextLine();
										}
										switch (ch1) {
											case 1:
												//userUtilObject.printTicket();
												System.out.println("-------------------------Current or Upcomming Travel------------------------------");
												ticketUtilObject.printHistory(dataObject.getTicketHistory(currentUser,TravelStatus.BOOKED));
											break;
											case 2:
												System.out.println("---------------------------Completed Travel History-------------------------------");
												ticketUtilObject.printHistory(dataObject.getTicketHistory(currentUser,TravelStatus.COMPLETED));
											break;
											case 3:
												System.out.println("---------------------------Cancelled Travel History-------------------------------");
												ticketUtilObject.printHistory(dataObject.getTicketHistory(currentUser,TravelStatus.CANCELLED));
											break;
											case 4:
												validHistory=false;
											break;
											default:
												System.out.println("\n\tInvalid Chioce...");
										}
									}
								break;
								case 5:
									currentUser=null;
									valid=false;
								break;
								default:
									System.out.println("\n\tInvalid Chioce...");
							}
						}
					}
				break;
				case 3:
					System.exit(0);
					scan.close();
				break;
				default:
					System.out.println("Invalid Chioce...");
			} 
		}
	}
}