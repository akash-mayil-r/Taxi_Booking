package taxiApp.users;

public class Passenger extends User {

	public byte passengerCount;
	
	public Passenger(String name,String phoneNumber,String emailId,String password,byte passengerCount) {
		super(name,phoneNumber,emailId,password);
		this.passengerCount=passengerCount;
	}
}
