package taxiApp.map;

public class Location {

	public String name;
	public double lat;
    public double lng;
	
	public Location(double lat,double lng,String name) {
        this.lat=lat;
        this.lng=lng;
        this.name=name;
    }

}
