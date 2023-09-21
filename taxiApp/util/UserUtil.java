package taxiApp.util;

import java.util.Scanner;

import taxiApp.collectionSystem.CollectionData;
import taxiApp.users.Customer;
import taxiApp.users.Passenger;

public class UserUtil{
	
	Scanner scan=new Scanner(System.in);
	CollectionData dataObject=CollectionData.getInstance();

	public boolean isValidUserName(String userName){
		return userName.matches("^[a-zA-Z._\\s*-]{3,}$");
	}
	
	public boolean isValidPhoneNumber(String phoneNumber){
		return phoneNumber.matches("(0|\\+91(\u002D|\\s)?)?[6-9][0-9]{9}");
	}
	
	public boolean isValidEmailId(String emailId){
		return emailId.matches("^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-z"+"A-Z]{2,7}$");
	}
	
	public boolean isValidPassword(String password){
		return password.matches("^(?=.*[0-9])"+"(?=.*[a-z])(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"+"(?=\\S+$).{8,20}$");
	}
	
	public Customer signUp(){	
		boolean valid=true,validUser=true,validPhone=true,vaildEmailId=true,validPassword=true,validConfirmPassword=true;
		String userName="",phoneNumber="",emailId="",password="",confirmPassword="";
		while(valid){
			while (validUser) {
				System.out.print("\n\tEnter User Name : ");
				userName=scan.nextLine();			
				validUser=isValidUserName(userName);
				if(validUser)
					validUser=false;
				else{
					System.out.println("\n\tInvalid User Name");
					validUser=true;
				}
			}
			while (validPhone) {
				System.out.print("\n\tEnter Phone Number : ");
				phoneNumber=scan.nextLine();
				validPhone=isValidPhoneNumber(phoneNumber);
				if(validPhone){
					validPhone=dataObject.verifyPhoneNumber(phoneNumber);
					if(validPhone)
						System.out.println("\n\tPhone Number is Already Registered");
				}
				else{
					System.out.println("\n\tInvalid Phone Number");
					validPhone=true;
				}
			}
			while (vaildEmailId) {
				System.out.print("\n\tEnter Email ID : ");
				emailId=scan.nextLine();
				vaildEmailId=isValidEmailId(emailId);
				if(vaildEmailId){
					vaildEmailId=dataObject.verifyEmailId(emailId);
					if(vaildEmailId)
						System.out.println("\n\tEmail ID is Already Registered");
				}
				else{
					System.out.println("\n\tInvalid Email ID");
					vaildEmailId=true;
				}
			}
			while (validPassword) {
				System.out.print("\n\tEnter Password : ");
				password=scan.nextLine();
				validPassword=isValidPassword(password);
				if(validPassword)
					validPassword=false;
				else{
					System.out.println("\n\tInvalid Password");
					validPassword=true;
				}
			}
			while (validConfirmPassword) {
				System.out.print("\n\tEnter Confirm Password : ");
				confirmPassword=scan.nextLine();
				validConfirmPassword=isValidPassword(password);
				if(validConfirmPassword)
					if(password.equals(confirmPassword))
						validConfirmPassword=false;
				else{
					System.out.println("\n\tInvalid Confirm Password");
					validConfirmPassword=true;
				}
			}
			if(!validUser && !validPhone && !vaildEmailId && !validPassword &&!validConfirmPassword)
				valid=false;
		}
		return new Customer(userName,phoneNumber,emailId.toLowerCase(),password);
	}
	
	public Customer signIn(){
		Customer CurrentUser=null;
		boolean valid=true,validPhone=true,vaildEmailId=true;
		MainUtil mainUtilObject=new MainUtil();
		while (valid) {
			System.out.print("\n\tEnter Phone Number or Email ID : ");
			String phoneOrEmail=scan.nextLine();
			System.out.print("\n\tEnter Password : ");
			String password=scan.nextLine();
			validPhone=isValidPhoneNumber(phoneOrEmail);
			vaildEmailId=isValidEmailId(phoneOrEmail);
			if(validPhone){
				validPhone=dataObject.verifyPhoneNumber(phoneOrEmail);
				if(validPhone){
					CurrentUser=dataObject.getUserThroughPhone(phoneOrEmail);
					if(CurrentUser.getPassword().equals(password))
						valid=false;
					else{
						System.out.println("\n\tInvalid Email ID or Phone Number or Password");
						valid=mainUtilObject.isContinueOrBack();
					}
				}
				else{
					System.out.println("\n\tInvalid Email ID or Phone Number or Password");
					valid=mainUtilObject.isContinueOrBack();
				}
			}
			else if(vaildEmailId){
				vaildEmailId=dataObject.verifyEmailId(phoneOrEmail);
				if(vaildEmailId){	
					CurrentUser=dataObject.getUserThroughEmail(phoneOrEmail);
					if(CurrentUser.getPassword().equals(password))
						valid=false;
					else{
						System.out.println("\n\tInvalid Email ID or Phone Number or Password");
						valid=mainUtilObject.isContinueOrBack();
					}
				}
				else{
					System.out.println("\n\tInvalid Email ID or Phone Number or Password");
					valid=mainUtilObject.isContinueOrBack();
				}
			}
			else{
				System.out.println("\n\tInvalid Email ID or Phone Number or Password");
				valid=mainUtilObject.isContinueOrBack();
			}
		}
		return CurrentUser;
	}
	
	public Passenger getPassengerCount(Customer currentUser,byte seatCapacity) {
    	boolean validPassenger=true;
    	byte passengerCount=0;
    	while (validPassenger) {
			System.out.print("\n\tEnter Passenger Count (Upto "+seatCapacity+") : ");
			try{
				passengerCount=scan.nextByte();
			}
			catch(Exception e){
				scan.nextLine();
			}
			if(passengerCount>=0 && passengerCount<=seatCapacity){
				validPassenger=false;
			}
			else{
				System.out.println("\n\tInvalid Passenger Count");
			}
		}
    	return new Passenger(currentUser.name,currentUser.phoneNumber,currentUser.emailId,currentUser.getPassword(),passengerCount);
    }
}