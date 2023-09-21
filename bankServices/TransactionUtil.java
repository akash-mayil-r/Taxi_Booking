package bankServices;

import java.util.Scanner;

import taxiApp.collectionSystem.CollectionData;
import taxiApp.util.DateAndTime;
import taxiApp.util.MainUtil;

public class TransactionUtil {
	
	Scanner scan=new Scanner(System.in);
	MainUtil mainUtilObject=new MainUtil();
	DateAndTime dateAndTimeObject=new DateAndTime();
	CollectionData dataObject=CollectionData.getInstance();
	
	public boolean isValidCardNumber(String cardNumber){
		return cardNumber.matches("^(4|5|6)[0-9]{12}(?:[0-9]{3})?$");
	}
	
	public boolean isValidCardHolderName(String cardHolderName){
		return cardHolderName.matches("^[A-Z\\s*]{3,}$");
	}
	
	public boolean isValidCardDate(String expiryDate){
		return expiryDate.matches("(?:0[1-9]|1[0-2])/2[0-9]{3}");
	}
	
	public boolean isValidCVV(String cvvNumber){
		return cvvNumber.matches("^[0-9]{3,4}$");
	}
	
	public boolean verifyExpiryDate(String str){
		int CurrentYear=dateAndTimeObject.getCurrentYear();
		int currentMonth=dateAndTimeObject.getCurrentMonth();
		String[] date = str.split("/");
		if(Integer.parseInt(date[1])==CurrentYear && currentMonth<=Integer.parseInt(date[0]))
			return true;
		else if(Integer.parseInt(date[1])>CurrentYear)
			return true;
		else 
			return false;
	}
	
	public Transaction pay(double fare){
		byte ch=0;
		String cardNumber="",cardHolderName="",expiryDate="",cvv="";
		boolean valid=true;
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t\t\tPayment Page");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		Transaction transaction=null;
		while (valid) {
			System.out.print("\n\t1. Debit Card or Credit Card\n\n\t2. Cash\n\n\t Select Payment Method : ");
			try{
				ch=scan.nextByte();
				scan.nextLine();
				}
			catch(Exception e){
				scan.nextLine();
			}
			switch(ch){
				case 1:
					boolean validPayment=true;
					while (validPayment) {
						boolean validCard=true,validCardHolder=true,validDate=true,validCVV=true;
						while(validCard){
							System.out.print("\n\tEnter Card Number : ");
							cardNumber=scan.nextLine();
							if(isValidCardNumber(cardNumber))
								validCard=false;
							else
								System.out.println("\n\tInvalid Card Number");
						}
						while(validCardHolder){
							System.out.print("\n\tEnter Card Holder Name : ");
							cardHolderName=scan.nextLine().toUpperCase();
							if(isValidCardHolderName(cardHolderName))
								validCardHolder=false;
							else
								System.out.println("\n\tInvalid Card Holder Name");
						}
						while(validDate){
							System.out.print("\n\tEnter Expiry Date [MM/YYYY] :");
							expiryDate=scan.nextLine();
							if(isValidCardDate(expiryDate) && verifyExpiryDate(expiryDate))
								validDate=false;
							else
								System.out.println("\n\tInvalid Card Expiry Date");	
						}
						while(validCVV){
							System.out.print("\n\tEnter CVV : ");
							cvv=scan.next();
							if(isValidCVV(cvv))
								validCVV=false;
							else
								System.out.println("\n\tInvalid CVV");	
						}
						transaction=new Transaction(fare,(byte)0);
						if (dataObject.verifyCardNumber(cardNumber)) {
							Payment currentCard=new Payment(cardNumber,cardHolderName,expiryDate,cvv,fare);
							Payment defaultCard=dataObject.getCard(cardNumber);
							if(currentCard.cardNumber.equals(defaultCard.cardNumber) && currentCard.cardHolderName.equals(defaultCard.cardHolderName) && currentCard.expiryDate.equals(defaultCard.expiryDate) && currentCard.cvv.equals(defaultCard.cvv)){
								if(currentCard.balance<=defaultCard.balance){
									defaultCard.balance-=currentCard.balance;
									System.out.println("\n\tYour Payment was Paid Successfully...");
									validPayment=false;
									valid=false;
								}
								else{
									System.out.println("\n\tInsufficient Balance in Your Account");
									validPayment=mainUtilObject.isContinueOrBack();
								}
							}
							else{
								System.out.println("\n\tInvalid Card Details");
								validPayment=mainUtilObject.isContinueOrBack();
							}
						}
						else{
							System.out.println("\n\tInvalid Card Details");
							validPayment=mainUtilObject.isContinueOrBack();
						}
					}
				break;
				case 2:
					valid=false;
					transaction=new Transaction(fare,(byte)1);
				break;
				default:
					System.out.println("\n\tInvalid Choice");
			}
		}
		return transaction;
	}
}
