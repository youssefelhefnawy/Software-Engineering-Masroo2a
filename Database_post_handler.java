import java.util.Vector;

public class Database_post_handler implements Data_base_handler
{
	Vector<Post> p_array=new Vector<Post>();
	public int get_size()
	{
		return p_array.size();
	}
	private boolean Authenticate(Post post) {
		for(Post p: p_array){
			if(post.getCategory().equals(p.getCategory()) && post.getItem_location().equals(p.getItem_location()) && post.getColor().equals(p.getColor())) {
				return false;
			}
		}
		return true;
	}
	public User Getuser(String userid) {return null;}
	public User Getuser(String username, String password) {return null;}
	public Post getpost(String postid) {
		for(int i =0 ; i < p_array.size();i++)  if(p_array.get(i).getPostID().equals(postid)) return p_array.get(i);
		return null; 
	}	
	public Vector<Post> getposts_by_user(String Userid) {
		Vector<Post> p = new Vector<Post>();
		for(int i =0 ; i < p_array.size();i++)  if(p_array.get(i).getUserID().equals(Userid)) p.add(p_array.get(i)) ;
		return p;
	}
	public Vector<Post> getposts_by_everything(String cat, String loc)
	{
		Vector<Post> p = new Vector<Post>();
		for(int i =0 ; i < p_array.size();i++)
		{
			if(p_array.get(i).getCategory().contains(cat) && p_array.get(i).getItem_location().contains(loc))
			{
				p.add(p_array.get(i)) ;	
			}
		}  
		return p;
	}
	public boolean Insert_user(User Newuser) {
		return false;
	}
	public boolean Insert_post(Post new_post) {
		if(Authenticate(new_post))
			p_array.add(new_post);
		else return false;
		return true;
	}
	public boolean Delete(String ID) {
		for(int i =0 ; i< p_array.size();i++) {
			if(p_array.get(i).getPostID().equals(ID)) {
				p_array.remove(i);
				return true;
			}
		}
		return false;
	}
	public boolean Update_user(User user, String ID) {
		return false;
	}
	public boolean update_post(Post post, String ID) {
		for(int i =0 ; i< p_array.size();i++) {
			if(p_array.get(i).getPostID().equals(post.getPostID()) && Authenticate(post)) {
				p_array.get(i).setCategory(post.getCategory());
				p_array.get(i).setColor(post.getColor());
				p_array.get(i).setDescription(post.getDescription());
				p_array.get(i).setItem_location(post.getItem_location());
		        return true;
			}
		}
		return false;
	}
}
