
public class User 
{
private static int count=0;
private String UserID;
private String Name;
private String Password;
private String Phone_number;
User()
{	
	this.UserID=(""+count);
	count++;
}
User(String name,String pass,String num)
{
	this.Name=name;
	this.Password=pass;
	this.Phone_number=num;
	this.UserID=(""+count);
	count++;
}
public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPhone_number() {
		return Phone_number;
	}
	public void setPhone_number(String phone_number) {
		Phone_number = phone_number;
	}

}
