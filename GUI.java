import java.util.Vector;

public class GUI 
{
	Data_base_handler dbu=new Database_user_handler();
	Data_base_handler dbp=new Database_post_handler();

	public User login(String username, String password) {
		return dbu.Getuser(username, password);
	}
	public Vector<Post> my_posts(String id)
	{
		return dbp.getposts_by_user(id);	
	}
	public boolean create_post(String UserID, String category, String description, String item_location, String color) {
		return dbp.Insert_post(new Post(UserID, category, description, item_location, color));
	}
	public boolean create_user(String name,String pass,String num)
	{
		return dbu.Insert_user(new User(name, pass, num));
	}
	public Vector<Post> Search(String keywords)
	{	
		return dbp.getposts_by_everything(keywords);
	}
}