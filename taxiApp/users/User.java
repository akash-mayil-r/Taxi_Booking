package taxiApp.users;

public abstract class User {
	public String name;
	public String phoneNumber;
	public String emailId;
	private String password;
	
	public User(String name,String phoneNumber,String emailId,String password) {
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.emailId=emailId;
		this.password=password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}