
public class Post 
{
	private static int postcount = 0;
	private String PostID;
	private String UserID;
	private String category;
	private String description;
	private String item_location;
	private String color;
	
	Post()
	{
		PostID = "" + postcount++;
	}
	
	Post(String UserID, String category, String description, String item_location, String color) {
		PostID = "" + postcount++;
		this.UserID = UserID;
		this.category = category;
		this.description = description;
		this.item_location = item_location;
		this.color = color;
	}
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPostID() {
		return PostID;
	}
	public void setPostID(String postID) {
		PostID = postID;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getItem_location() {
		return item_location;
	}
	public void setItem_location(String item_location) {
	this.item_location = item_location;
}
	
}
