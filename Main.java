import java.util.Scanner;
import java.util.Vector;

public class Main {

	private static User currentuser = null;
	public static String createspace(String s, int space) {
		if(s.length() > space)
			return s;
		return s + String.format("%0" + (space - s.length()) + "d", 0).replace('0', ' ');
	}
	public static void main(String[] args) {
		GUI gui = new GUI();
		Scanner input = new Scanner(System.in);
		while(true) {
			while(true) {
				System.out.println("Choose one of the options below:");
				System.out.println("1)Register.\n2)Log in.\n3)Exit.");
				int choice;
				choice = input.nextInt();
				input.nextLine();
				if(choice == 1) 
				{	
					System.out.print("Username: ");
					String username = input.nextLine();
					System.out.print("Password: ");
					String password = input.nextLine();
					System.out.print("Phone number: ");
					String number = input.nextLine();
					if(gui.create_user(username, password, number)) {
						System.out.println("Registered successfully!");
					}
					else
						System.out.println("Failed to register!\nUsername or phone number already in use!");
				}
				else if(choice == 2) 
				{	
					
					System.out.print("Username: ");
					String username = input.nextLine();
					System.out.print("Password: ");
					String password = input.nextLine();
					currentuser = gui.login(username, password);
					if(currentuser == null) {
						System.out.println("Incorrect username or password!");
					}
					else {
						System.out.println("Logged in successfully!\nWelcome " + currentuser.getName() + "!");
						break;
					}
				}
				else if(choice == 3) {
					input.close();
					return;
				}
				else {
					System.out.println("Incorrect input!");
				}
			}
			while(true) {
				System.out.println("Choose one of the options below:");
				System.out.println("1)Post a lost item.\n2)My posts.\n3)Search on posts.\n4)Update password and phone number.\n5)Delete account.\n6)Log out.");
				int choice;
				choice = input.nextInt();
				input.nextLine();
				///Post lost item
				if(choice == 1) {
					System.out.print("Category: ");
					String category = input.nextLine();
					System.out.print("Color: ");
					String color = input.nextLine();
					System.out.print("Location: ");
					String item_location = input.nextLine();
					System.out.print("Description: ");
					String description = input.nextLine();
					if(gui.create_post(currentuser.getUserID(), category, description, item_location, color)) {
						System.out.println("Item successfully posted!");
					}
					else
						System.out.println("Failed to post item!\nThere is a similar item already in the database!");
				}
				////My posts
				else if (choice==2) {
					Vector<Post> ourposts = gui.my_posts(currentuser.getUserID());
					if (ourposts.size() == 0) {
						System.out.println("You don't have any posts!");
						continue;
					}
					System.out.println("Item ID         " + "Item Category         " + "Item Color         " + "Item Location         " + "Item description");
					for (int i = 0; i < ourposts.size(); i++) {
						System.out.println(createspace(ourposts.elementAt(i).getPostID(), 16) + createspace(ourposts.elementAt(i).getCategory(), 22) + createspace(ourposts.elementAt(i).getColor(), 19) + createspace(ourposts.elementAt(i).getItem_location(), 22) + ourposts.elementAt(i).getDescription());
					}
					System.out.println("Do you want to edit any of your posts?(y/n)");
					String update = input.nextLine();
					if (update.equals("y")) {
						System.out.print("Enter the ID of the post you want to edit: ");
						String itemID = input.nextLine();
						if (gui.dbp.getpost(itemID) == null) {
							System.out.println("Invalid input!");
						} else {
							System.out.print("Category: ");
							String category = input.nextLine();
							System.out.print("Color: ");
							String color = input.nextLine();
							System.out.print("Location: ");
							String item_location = input.nextLine();
							System.out.print("Description: ");
							String description = input.nextLine();
							if (gui.dbp.update_post(new Post(null, category, description, item_location, color), itemID)) {
								System.out.println("Post successfully updated!");
							} else
								System.out.println("Failed to update post!\nThere is a similar item already in the database!");
						}
					}
					System.out.println("Do you want to delete any of your posts?(y/n)");
					String delete = input.nextLine();
					if (delete.equals("y")) {
						System.out.print("Enter the ID of the post you want to delete: ");
						String itemID = input.nextLine();
						if (gui.dbp.getpost(itemID) == null) {
							System.out.println("Invalid input!");
						} else {
							gui.dbp.Delete(itemID);
							System.out.println("Post successfully deleted!");
						}
					}
				}
				////Search on Posts
				else if(choice==3)
				{
					System.out.println("Item category:");
					String category=input.nextLine();
					System.out.println("Location of losing the item:");
					String location = input.nextLine();
					Vector<Post> ourposts=gui.Search(category, location);
					if(ourposts.size() == 0){
						System.out.println("No results found...");
						continue;
					}
					System.out.println("Item ID         " + "Item Category         "+"Item Color         "+   "Item Location         "+"Item description");
					for(int i=0 ;i <ourposts.size();i++)
					{
						System.out.println(createspace(ourposts.elementAt(i).getPostID(), 16) + createspace(ourposts.elementAt(i).getCategory(), 22) +createspace(ourposts.elementAt(i).getColor(), 19)+ createspace(ourposts.elementAt(i).getItem_location(), 22) + ourposts.elementAt(i).getDescription());
					}
					System.out.println("Do you want to claim any of the items?(y/n)");
					String claim = input.nextLine();
					if(claim.equals("y")) {
						System.out.println("Enter the ID of the item you want to claim:");
						String itemID = input.nextLine();
						if(gui.dbp.getpost(itemID) == null) {
							System.out.println("Invalid input!");
						}
						else if(gui.dbp.getpost(itemID).getUserID() == currentuser.getUserID()) {
							System.out.println("You can't claim an item you posted yourself!");
						}
						else
							System.out.println("Contact this number to claim your item: " + gui.dbu.Getuser(gui.dbp.getpost(itemID).getUserID()).getPhone_number());
					}
				}
				else if(choice == 4) {
					System.out.print("New password: ");
					String password = input.nextLine();
					System.out.print("New phone number: ");
					String num = input.nextLine();
					gui.dbu.Update_user(new User(currentuser.getName(), password, num), currentuser.getUserID());
				}
				else if(choice == 5) {
					gui.dbp.Delete(currentuser.getUserID());
					gui.dbu.Delete(currentuser.getUserID());
					System.out.println("Your account has been deleted successfully!");
					break;
				}
				////Log out
				else if (choice==6)
				{
					break;
				}
				else
				{
					System.out.println("Invalid Input!");
				}
				
				}
			
		}
	}
}
