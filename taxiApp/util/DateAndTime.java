package taxiApp.util;

import java.util.Calendar;
import java.util.Scanner;

public class DateAndTime {

	Scanner scan=new Scanner(System.in);
	public String getCurrentDay() {
    	Calendar cal=Calendar.getInstance();
	    return String.format("%td-%tb-%tY %ta",cal,cal,cal,cal);
    }
	
	public String getCurrentDate() {
    	Calendar cal=Calendar.getInstance();
	    return String.format("%td%tm%tY",cal,cal,cal);
    }
    
	public String getCurrentTime() {
    	Calendar cal=Calendar.getInstance();
	    return String.format("%tr",cal);
    }
    
	public int getCurrentMonth() {
    	Calendar cal=Calendar.getInstance();
	    return Integer.parseInt(String.format("%tm",cal));
    }
    
    public int getCurrentYear() {
    	Calendar cal=Calendar.getInstance();
	    return Integer.parseInt(String.format("%tY",cal));
    }
    
    public String timeCalculate(double distance,int speed) {
    	String time="";
    	time+=String.format("%02d",(int) Math.floor(distance/speed));
    	distance=(distance/speed)-(int) Math.floor(distance/speed);
    	time+=":";
    	time+=String.format("%02d",(int) Math.floor(distance*60));
    	distance=(distance*60)-(int) Math.floor(distance*60);
    	time+=":";
    	time+=String.format("%02d",(int) Math.round(distance*60));
    	return time;
    }
    
    public boolean checkLeapYear(int year){
    	return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }
    
    public boolean validDate(String date) {
    	if(date.matches("([1-9]|0[1-9]|1[0-9]|2[0-9]|3[01])-([1-9]|0[1-9]|1[012])-([0-9]{4})")) {
			String dateSplited[]=date.split("-");
			if((Integer.parseInt(dateSplited[1])==1 || Integer.parseInt(dateSplited[1])==3 || Integer.parseInt(dateSplited[1])==5 || Integer.parseInt(dateSplited[1])==7 || Integer.parseInt(dateSplited[1])==8 || Integer.parseInt(dateSplited[1])==10 || Integer.parseInt(dateSplited[1])==12) && Integer.parseInt(dateSplited[0])<=31)
				return true;
			else if((Integer.parseInt(dateSplited[1])==4 || Integer.parseInt(dateSplited[1])==6 || Integer.parseInt(dateSplited[1])==9 || Integer.parseInt(dateSplited[1])==11 ) && Integer.parseInt(dateSplited[0])<=30)
				return true;
			else if(Integer.parseInt(dateSplited[1])==2) {
				if(checkLeapYear(Integer.parseInt(dateSplited[2]))) {
					if(Integer.parseInt(dateSplited[0])<=29)
						return true;
					else
						return false;
				}
				else {
					if(Integer.parseInt(dateSplited[0])<=28)
						return true;
	    			else
	    				return false;
				}
			}
			else
				return false;
		}
		else
			return false;
    }
    public void getDateAndTime() {
    	String date="",time="",currentDate="";
    	while(true) {
    		System.out.print("Enter the Date [DD-MM-YYYY] : ");
    		date=scan.next();
    		if(validDate(date)) {
    			
    		}
    	}
    }
}