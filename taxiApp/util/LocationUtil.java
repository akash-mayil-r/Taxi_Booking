package taxiApp.util;

import java.util.Scanner;

import taxiApp.collectionSystem.CollectionData;
import taxiApp.map.Location;

public class LocationUtil {
	Scanner scan=new Scanner(System.in);
	MainUtil mainUtilObject=new MainUtil();
	CollectionData dataObject=CollectionData.getInstance();

	public boolean isValidLocation(String location){
		return location.matches("^[A-Za-z]\\w{1,29}$");
	}
	
	public String getLocation() {
		boolean valid=true;
		int count=0,from=0;
		while(valid){
			count= printLocations(null);
//			try {
//				from=scan.nextInt();
//			}
//			catch(Exception e) {
//				scan.nextLine();
//			}
			while(true) {
				System.out.print("\n\n\tSelect PickUp Location : ");
				if(scan.hasNextInt())
					from=scan.nextInt();
				else
					scan.nextLine();
				if(from>0 && from<=count)
					return dataObject.getLocationList().get(from-1);
				else {
					System.out.println("\n\tInvalid Location");
					//valid=mainUtilObject.isContinueOrBack();
				}
			}
		}
		return null;
	}
	
	public String getLocation(String fromLocation) {
		boolean valid=true;
		int count=0,to=0,from=dataObject.getLocationList().indexOf(fromLocation)+1;
		while(valid){
			count= printLocations(fromLocation);
			while(true) {
				System.out.print("\n\n\tSelect Drop Location : ");
				if(scan.hasNextInt())
					to=scan.nextInt();
				else
					scan.nextLine();
				if(to>0 && to<=count){
					if(to>=from)
						return dataObject.getLocationList().get(to);
					else
						return dataObject.getLocationList().get(to-1);
				}			
				else {
					System.out.println("\n\tInvalid Location");
					//valid=mainUtilObject.isContinueOrBack();
				}
			}
		}
		return null;
	}
	
	public int printLocations(String fromLocation){
    	int count=0;
    	System.out.println("\tLocations :\n\t-----------");
	    for(String location:CollectionData.getInstance().getLocationList()){
			if(location.equals(fromLocation))
				continue;
			System.out.print("\t"+(++count)+". "+location+"\t");
	    	if(count%3==0)
	    		System.out.println("\n");
	    }
		return count;
	}
	
	public double distanceCalculate(Location fromLocation,Location toLocation) {
        // Use the Haversine formula to calculate the distance between two locations
        double earthRadius = 6371; // in kilometers
        double dLat = Math.toRadians(toLocation.lat - fromLocation.lat);
        double dLng = Math.toRadians(toLocation.lng - fromLocation.lng);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(fromLocation.lat)) * Math.cos(Math.toRadians(toLocation.lat)) *
                   Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
	
}