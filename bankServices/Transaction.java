package bankServices;

import taxiApp.util.MainUtil;

public class Transaction {

	MainUtil mainUtilObject=new MainUtil();
	public String id;
	boolean status;
	public double fare;
	byte mode;
	final static byte paymentMethod[]={0,1};
	
	public Transaction(double fare,byte mode){
		this.id=mainUtilObject.getId();
		this.fare=fare;
		this.mode=mode;
		if(mode==0)
			this.status=true;
		else
			this.status=false;
	}
	
}