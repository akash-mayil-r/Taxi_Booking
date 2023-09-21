package taxiApp.collectionSystem;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import taxiApp.users.Driver;
import taxiApp.util.TaxiUtil.TravelStatus;
import taxiApp.map.Location;
import bankServices.Payment;
import taxiApp.vehicle.Taxi;
import taxiApp.details.Ticket;
import taxiApp.users.Customer;

public class CollectionData {

	private static CollectionData dataObject = null;
    
    public CollectionData(){
    	
    }
  
    public static CollectionData getInstance(){
        if (dataObject == null) {
        	dataObject = new CollectionData();
        }
        return dataObject;
    }
    
	private HashMap<String,Customer> userEmailMap=new HashMap<String,Customer>();
	private HashMap<String,Customer> userPhoneMap=new HashMap<String,Customer>();
	
	public Customer getUserThroughEmail(String emailId){
		return userEmailMap.get(emailId.toLowerCase());
	}
	
	public Customer getUserThroughPhone(String phoneNumber){
 		return userPhoneMap.get(phoneNumber);
	}
	
	public void setUserThroughEmail(String key,Customer user){
		this.userEmailMap.put(key,user);
	}
	
	public void setUserThroughPhone(String key,Customer user){
		this.userEmailMap.put(key,user);
	}
	
	public boolean verifyEmailId(String emailId){
		return userEmailMap.containsKey(emailId.toLowerCase());	
	}
	
	public boolean verifyPhoneNumber(String phoneNumber){
		return userPhoneMap.containsKey(phoneNumber);
	}
	
	public void storeUser() {
		userEmailMap.put("akash@gmail.com",new Customer("Akash","9876543210","akash@gmail.com","akash"));
		userPhoneMap.put("9876543210",userEmailMap.get("akash@gmail.com"));
		userEmailMap.put("akash123@gmail.com",new Customer("Akash_123","9876543211","akash123@gmail.com","Akash@123"));
		userPhoneMap.put("9876543211",userEmailMap.get("akash123@gmail.com"));
	}
	
	private LinkedHashMap<String,Location> locationMap=new LinkedHashMap<>();
	
	public LinkedHashMap<String,Location> getLocationMap(){
		return locationMap;
	}
	
	public void storeLocation(){
		// locationMap.put("Ariyalur",new Location(11.135732972848423, 79.07709776913455,"Ariyalur"));
        // locationMap.put("Chengalpattu",new Location(12.678782677247613, 79.98612656600282,"Chengalpattu"));
        locationMap.put("Chennai",new Location(13.083360549215882, 80.2666923931812,"Chennai"));
        locationMap.put("Coimbatore",new Location(11.02361706669042, 76.95213703674942,"Coimbatore"));
        // locationMap.put("Cuddalore",new Location(11.74864488554501, 79.7707628877199,"Cuddalore"));
        // locationMap.put("Dharmapuri",new Location(12.120742164018996, 78.15673706932331,"Dharmapuri"));
        // locationMap.put("Dindigul",new Location(10.362421297158102, 77.96941493299144,"Dindigul"));
        // locationMap.put("Erode",new Location(11.344234888513109, 77.71580507807226,"Erode"));
        // locationMap.put("Kallakurichi",new Location(11.738673985723718, 78.96429968880302,"Kallakurichi"));
        // locationMap.put("Kanchipuram",new Location(12.818455655424742, 79.69461563330302,"Kanchipuram"));
        // locationMap.put("Kanniyakumari",new Location(8.088344616806072, 77.53812637805777,"Kanniyakumari"));
        // locationMap.put("Karur",new Location(10.959009818353481, 78.07553478045757,"Karur"));
        // locationMap.put("Krishnagiri",new Location(12.52656570850451, 78.21493833199273,"Krishnagiri"));
        locationMap.put("Madurai",new Location(9.931682799935777, 78.1121815643515,"Madurai"));
        // locationMap.put("Mayiladuthurai",new Location(11.10177146405672, 79.65256238634632,"Mayiladuthurai"));
        // locationMap.put("Nagapattinam",new Location(10.763800624206938, 79.84469308583996,"Nagapattinam"));
        // locationMap.put("Namakkal",new Location(11.219506623617026, 78.16675694421257,"Namakkal"));
        // locationMap.put("Nilgiris",new Location(11.41295704865637, 76.6925218401579,"Nilgiris"));
        // locationMap.put("Perambalur",new Location(11.235971499974289, 78.87830141773306,"Perambalur"));
        // locationMap.put("Pudukkottai",new Location(10.38332859416617, 78.80008603299261,"Pudukkottai"));
        //locationMap.put("Ramanathapuram",new Location(9.36289266423924, 78.83958688489149,"Ramanathapuram"));
        // locationMap.put("Ranipet",new Location(12.950195024945515, 79.31798608083906,"Ranipet"));
        // locationMap.put("Salem",new Location(11.674937282110458, 78.13656821740476,"Salem"));
        // locationMap.put("Sivagangai",new Location(9.84321929758331, 78.48054718586879,"Sivagangai"));
        locationMap.put("Tenkasi",new Location(8.956552078966068, 77.3151936385737,"Tenkasi"));
        //locationMap.put("Thanjavur",new Location(10.787211149671387, 79.13764189079063,"Thanjavur"));
        //locationMap.put("Theni",new Location(10.011486749215022, 77.47177287356328,"Theni"));
        locationMap.put("Thoothukudi",new Location(8.76438359233773, 78.1346351303885,"Thoothukudi"));
        //locationMap.put("Tiruchirappalli",new Location(10.790606901146718, 78.70453229809284,"Tiruchirappalli"));
        locationMap.put("Tirunelveli",new Location(8.714717075814175, 77.75639678151389,"Tirunelveli"));
        // locationMap.put("Tirupathur",new Location(12.475442009095195, 78.58545524859767,"Tirupathur"));
        // locationMap.put("Tiruppur",new Location(11.10863618619286, 77.3409832135549,"Tiruppur"));
        // locationMap.put("Tiruvallur",new Location(13.1230388450324, 79.91187849117786,"Tiruvallur"));
        // locationMap.put("Tiruvannamalai",new Location(12.225361685668716, 79.07453753595892,"Tiruvannamalai"));
        // locationMap.put("Tiruvarur",new Location(10.766228425535164, 79.63426815933462,"Tiruvarur"));
        // locationMap.put("Vellore",new Location(12.912164502915113, 79.13048562948546,"Vellore"));
        // locationMap.put("Viluppuram",new Location(11.940560405528567, 79.48594657518757,"Viluppuram"));
        locationMap.put("Virudhunagar",new Location(9.568393164396927, 77.96138939571632,"Virudhunagar"));
	}
	
	ArrayList<String> locationList=new ArrayList<String>();
	
	public boolean verifyLocation(String location){
		return locationMap.containsKey(location);
	}
	
	public ArrayList<String> getLocationList() {
		return locationList;
	}
	public void storeLocationList(){
		for(Map.Entry<String,Location> location:locationMap.entrySet())
    		locationList.add(location.getKey());
	}
	
	private HashMap<String,Taxi> taxiMap=new HashMap<>();
	
	public Taxi getTaxi(String key){
		return taxiMap.get(key);
	}
	
	public void storeTaxi(){
		taxiMap.put("TN01AB4260",new Taxi("Maruti Suzuki Swift","TN01AB4260","Hatchback",(byte)4,true,new Driver("AAA","9876543210","aaa@gmail.com","aaa@123","TN0120200000001",true)));
		taxiMap.put("TN01AB4261",new Taxi("Honda City","TN01AB4261","Sedan",(byte)4,true,new Driver("BBB","9876543211","bbb@gmail.com","bbb@123","TN0120200000002",true)));
		taxiMap.put("TN01AB4262",new Taxi("Tata Nexon","TN01AB4262","SUV",(byte)6,true,new Driver("CCC","9876543212","ccc@gmail.com","ccc@123","TN0120200000003",true)));
		taxiMap.put("TN01AB4263",new Taxi("Maruti Suzuki Baleno","TN01AB4263","Hatchback",(byte)4,true,new Driver("DDD","9876543213","ddd@gmail.com","ddd@123","TN0120200000004",true)));
		taxiMap.put("TN01AB4264",new Taxi("Hyundai Aura","TN01AB4264","Sedan",(byte)4,true,new Driver("EEE","9876543214","eee@gmail.com","eee@123","TN0120200000005",true)));
		taxiMap.put("TN01AB4265",new Taxi("Hyundai Venue","TN01AB4265","SUV",(byte)6,true,new Driver("FFF","9876543215","fff@gmail.com","fff@123","TN0120200000006",true)));
		taxiMap.put("TN01AB4266",new Taxi("Hyundai i20","TN01AB4266","Hatchback",(byte)4,true,new Driver("GGG","9876543216","ggg@gmail.com","ggg@123","TN0120200000007",true)));
		taxiMap.put("TN01AB4267",new Taxi("Skoda Slavia","TN01AB4267","Sedan",(byte)4,true,new Driver("HHH","9876543217","hhh@gmail.com","hhh@123","TN0120200000008",true)));
		taxiMap.put("TN01AB4268",new Taxi("Mahindra XUV700","TN01AB4268","SUV",(byte)6,true,new Driver("III","9876543218","iii@gmail.com","iii@123","TN0120200000009",true)));
		taxiMap.put("TN01AB4269",new Taxi("Tata Tiago","TN01AB4269","Hatchback",(byte)4,true,new Driver("JJJ","9876543219","jjj@gmail.com","jjj@123","TN0120200000010",true)));
		taxiMap.put("TN01AB4270",new Taxi("Maruti Suzuki Dzire","TN01AB4270","Sedan",(byte)4,true,new Driver("KKK","9876543220","kkk@gmail.com","kkk@123","TN0120200000011",true)));
		taxiMap.put("TN01AB4271",new Taxi("MG Hector","TN01AB4271","SUV",(byte)6,true,new Driver("LLL","9876543221","lll@gmail.com","lll@123","TN0120200000012",true)));
	}
	ArrayList<String> taxiList=new ArrayList<>();
	
	public void storeTaxiList(){
		for (Map.Entry<String,Taxi> taxi:taxiMap.entrySet()) {
			taxiList.add(taxi.getKey());
		}
	}
    
	HashMap<String,String> taxiAndLocation=new HashMap<>();
	
	public HashMap<String,String> getTaxiAndLocation(){
		return taxiAndLocation;
	}
	
	public void setTaxi(){
		int count=0;
		for(Map.Entry<String,Location> locations:locationMap.entrySet()){
			for(int i=count;i<taxiList.size();i++){
				taxiAndLocation.put(taxiList.get(i),locations.getKey());
				if(i%3==2){
					count=i+1;
					break;
				}
			}
		}
	}
	
	public ArrayList<Taxi> searchTaxi(String fromLocation){
		ArrayList<Taxi> availableTaxi=new ArrayList<>();
		for (Map.Entry<String,String> location:taxiAndLocation.entrySet()) {
			if(location.getValue().equals(fromLocation)){
				if(getTaxi(location.getKey()).availableStatus==true)
					availableTaxi.add(getTaxi(location.getKey()));
			}
		}
		return availableTaxi;
	}
	
	private LinkedHashMap<String,Ticket> ticketMap=new LinkedHashMap<>();
	
	public boolean verifyTicketId(String ticketId) {
		return ticketMap.containsKey(ticketId);
	}
	
	public LinkedHashMap<String,Ticket> getTickets(){
		return ticketMap;
	}
	
	public Ticket getTicket(String ticketId){
		return ticketMap.get(ticketId);
	}
	
	public void setTicket(String key,Ticket ticket) {
		ticketMap.put(key,ticket);
	}
	
	public ArrayList<Ticket> getTicketHistory(Customer currentUser,TravelStatus value){
		ArrayList<Ticket> tickets=new ArrayList<>();
		for (Map.Entry<String,Ticket> ticket:CollectionData.getInstance().getTickets().entrySet()) {
			if(value == TravelStatus.BOOKED) {
				if(currentUser.getBookedTickets().contains(ticket.getKey())) {
					if(ticket.getValue().userEmailId.equals(currentUser.emailId)){
						if(ticket.getValue().travelStatus == TravelStatus.BOOKED){
							tickets.add(ticket.getValue());
						}
					}
				}
			}
			else if(value == TravelStatus.CANCELLED) {
				if(currentUser.getCancelledTickets().contains(ticket.getKey())) {
					if(ticket.getValue().userEmailId.equals(currentUser.emailId)){
						if(ticket.getValue().travelStatus == TravelStatus.CANCELLED){
							tickets.add(ticket.getValue());
						}
					}
				}
			}
			else if(value == TravelStatus.COMPLETED) {
				if(currentUser.getFinishedTickets().contains(ticket.getKey())) {
					if(ticket.getValue().userEmailId.equals(currentUser.emailId)){
						if(ticket.getValue().travelStatus == TravelStatus.COMPLETED){
							tickets.add(ticket.getValue());
						}
					}
				}
			}
		}
		return tickets;
	}
	
	private HashMap<String,Payment> cards=new HashMap<>();
	
	public Payment getCard(String key) {
		return cards.get(key);
	}
	
	public boolean verifyCardNumber(String cardNumber){
		if(cards.containsKey(cardNumber))
			return true;
		else
			return false;
	}
	
	public void storeCard(){
		cards.put("4123456789012345",new Payment("4123456789012345","AKASH","12/2025","123",50000));
		cards.put("5123456789012345",new Payment("5123456789012345","RAJ","01/2026","234",50000));
		cards.put("6123456789012345",new Payment("6123456789012345","AJAY","06/2027","345",50000));
		cards.put("6123456789012346",new Payment("6123456789012346","ARUN","12/2028","456",50000));
		cards.put("4567890123456789",new Payment("4567890123456789","AAA","12/2030","567",10000));
		cards.put("5678901234567890",new Payment("5678901234567890","BBB","12/2030","678",10000));
	}	
}