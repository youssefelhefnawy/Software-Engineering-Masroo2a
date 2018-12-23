import java.util.Scanner;
import java.util.Vector;

public class Main {

	private static User currentuser = null;
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
					System.out.println("Description: ");
					String description = input.nextLine();
					System.out.println("Location: ");
					String item_location = input.nextLine();
					System.out.println("Color: ");
					String color = input.nextLine();
					if(gui.create_post(currentuser.getUserID(), category, description, item_location, color)) {
						System.out.println("Item successfully posted!");
					}
					else
						System.out.println("Failed to post item!\nThere is a similar item already in the database!");
				}
				////My posts
				else if (choice==2)
				{
					System.out.println("My Posts :");
					Vector<Post> ourposts=gui.my_posts(currentuser.getUserID());					
					System.out.println("Item ID         " +"Item Color         "+   "Item Location         "+"Item description");
					
					for(int i=0 ;i <ourposts.size();i++)
					{
						System.out.println(ourposts.elementAt(i).getPostID()+"         " + ourposts.elementAt(i).getColor()+"         " + ourposts.elementAt(i).getItem_location()+"         "+ourposts.elementAt(i).getDescription());
					}					
				}
				////Search on Posts
				else if(choice==3)
				{
					System.out.println("Enter keyword(s):");
					String keywords=input.nextLine();
					Vector<Post> ourposts=gui.Search(keywords);
					System.out.println("Item ID         " +"Item Color         "+   "Item Location         "+"Item description");
					for(int i=0 ;i <ourposts.size();i++)
					{
						System.out.println(ourposts.elementAt(i).getPostID()+"         " + ourposts.elementAt(i).getColor()+"         " + ourposts.elementAt(i).getItem_location()+"         "+ourposts.elementAt(i).getDescription());
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
