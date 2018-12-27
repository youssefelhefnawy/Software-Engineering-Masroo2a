import java.util.Vector;

public interface Data_base_handler 
{
	public int get_size();
	public Vector<Post> getposts_by_user(String Userid);
	public Vector<Post> getposts_by_everything(String cat, String loc);
	public User Getuser(String userid);
	public User Getuser(String username, String password);
	public Post getpost(String postid);
	public boolean Insert_user(User Newuser);
	public boolean Insert_post(Post new_post);
	public boolean Delete(String ID);
	public boolean Update_user(User user, String ID);
	public boolean update_post(Post post, String ID);
}
