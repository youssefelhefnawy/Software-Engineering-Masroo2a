import java.util.Vector;

public class Database_user_handler implements Data_base_handler
{
	Vector<User> u_array=new Vector<User>();
	public Vector<Post> getposts_by_everything(String cat, String loc)
	{return null;}
	
	public int get_size()
	{
		return u_array.size();
	}
	private boolean Authenticate(User user) {
		for (User u: u_array) {
			if(user.getName().equals(u.getName()) || user.getPhone_number().equals(u.getPhone_number()))
				return false;
		}
		return true;
 	}
	public User Getuser(String userid) {
		for(int i =0 ; i < u_array.size();i++)  if(u_array.get(i).getUserID().equals(userid)) return u_array.get(i);
		return null; 
	}
	public User Getuser(String username, String password) {
		for (int i = 0; i < u_array.size(); i++) {
			if(u_array.get(i).getName().equals(username) && u_array.get(i).getPassword().equals(password)) {
				return u_array.get(i);
			}
		}
		return null;
	}
	public Post getpost(String userid) {return null;}
	public boolean Insert_user(User Newuser) {
		if(Authenticate(Newuser)) {
			u_array.add(Newuser);
			return true;
		}
		else return false;
	}
	public boolean Insert_post(Post new_post) {return true;}
	public boolean Delete(String ID) {
		for(int i =0 ; i< u_array.size();i++) {
			if(u_array.get(i).getUserID().equals(ID)) {
				u_array.remove(i);
				return true;
			}
			
		}
		return false;
	}
	public boolean Update_user(User user, String ID) {
		for(int i =0 ; i < u_array.size();i++) {
			if(u_array.get(i).getUserID().equals(ID) && Authenticate(user)) {
				u_array.get(i).setPassword(user.getPassword());
				u_array.get(i).setPhone_number(user.getPhone_number());
				return true;
			}
		}
		return false;
	}
	public Vector<Post> getposts_by_user(String Userid)
	{return null;}
	public boolean update_post(Post post, String ID) {return false;}


}
