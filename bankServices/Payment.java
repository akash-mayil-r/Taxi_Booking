package bankServices;

public class Payment {

	String cardNumber;
	String cardHolderName;
	String expiryDate;
	String cvv;
	double balance;
	
	public Payment(String cardNumber,String cardHolderName,String expiryDate,String cvv,double balance){
		this.cardNumber=cardNumber;
		this.cardHolderName=cardHolderName;
		this.expiryDate=expiryDate;
		this.cvv=cvv;
		this.balance=balance;
	}

}
