package UserMGMT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class UserManagement {
	static ArrayList<User> al = new ArrayList<>();
	
	static {
		try {
			LoadDataFromfileToArrayList();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void Usermanagement()throws IOException  {
		
		Scanner sc = new Scanner(System.in);
		
		boolean CanIKeepRunningTheProgram = true;
		
		while(CanIKeepRunningTheProgram == true) {
			System.out.println("@@@@WELCOME TO THE USER_MANAGEMENT PROGRAM@@@@@@");
			System.out.println("\n");
			
			System.out.println("What would you like to do?");
			System.out.println("1. Add User");
			System.out.println("2. Edit User");
			System.out.println("3. Delete User");
			System.out.println("4. Search User");
			System.out.println("5. Quit");
			
			int OptionSelectedByUser = sc.nextInt();
			
			if(OptionSelectedByUser == User_Options.QUIT) {
				writedataintoTextfile();
				System.out.println("!!! Program Closed !!!");
				CanIKeepRunningTheProgram = false;
			}
			else if(OptionSelectedByUser == User_Options.ADD_USER) {
				AddUser();
				System.out.println();
			}
			else if(OptionSelectedByUser == User_Options.SEARCH_USER) {
				System.out.print("Enter the User_Name which you wanna search :");
				sc.nextLine();
				String sn = sc.nextLine();
				Search_User(sn);
				System.out.println("\n");
			}
			else if(OptionSelectedByUser == User_Options.DELETE_USER) {
				System.out.print("Enter the User_Name which you wanna Delete :");
				sc.nextLine();
				String su = sc.nextLine();
				Delete_User(su);
				System.out.println("\n");
			}else if(OptionSelectedByUser == User_Options.EDIT_USER) {
				System.out.print("Enter the User_Name which you wanna Edit :");
				sc.nextLine();
				String eu = sc.nextLine();
				Edit_User(eu);
				System.out.println("\n");
			}
		}
	}
	public static void AddUser() {
		Scanner sc = new Scanner(System.in);
		User u = new User();
		
		System.out.println("Enter Username :");
		u.Username = sc.nextLine();
		
		System.out.println("Enter loginname :");
		u.LoginName = sc.nextLine();
		
		System.out.println("Enter password :");
		u.Password = sc.nextLine();
		
		System.out.println("Enter confirmPassword :");
		u.ConfirmPassword = sc.nextLine();
		
		System.out.println("Enter UserRole :");
		u.UserRole = sc.nextLine();
		
		System.out.println("\n");
		System.out.println("Username is :"+u.Username);
		System.out.println("Loginname is :"+u.LoginName);
		System.out.println("Password is :"+u.Password);
		System.out.println("ConfirmPassword is :"+u.ConfirmPassword);
		System.out.println("UserRole is :"+u.UserRole);
		
		al.add(u);
		
	}
	public static void Edit_User(String Username) {
		for(User eu:al) {
			if(eu.Username.equalsIgnoreCase(Username)) {
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Edit User :"+eu.Username);
				
				System.out.print("New UserName is :");
				eu.Username = sc.nextLine();
				
				System.out.print("New LoginName is :");
				eu.LoginName = sc.nextLine();
				
				System.out.print("New Password is :");
				eu.Password = sc.nextLine();
				
				System.out.print("New Conform_Password is :");
				eu.ConfirmPassword = sc.nextLine();
				
				System.out.print("New User_Role is :");
				eu.UserRole = sc.nextLine();
				
				System.out.println("User Information Updated");
				return;
			}
		}
		System.out.println("!!!!USER NOT FOUND!!!!!");
	}
	public static void Search_User(String Username) {
		for(User u:al) {
			if(u.Username.equalsIgnoreCase(Username)) {
				System.out.println("UserName is :"+u.Username);
				System.out.println("loginName is :"+u.LoginName);
				System.out.println("Password is :"+u.Password);
				System.out.println("Conform_Password is :"+u.ConfirmPassword);
				System.out.println("User_Role is :"+u.UserRole);
				
				return;
			}
		}
		System.out.println("!!!!USER NOT FOUND!!!!!");
	}
	public static void Delete_User(String Username) {
		Iterator<User> itr = al.iterator();
		
		while(itr.hasNext()) {
			User u = itr.next();
			if(u.Username.equalsIgnoreCase(Username)) {
				itr.remove();
				System.out.println("User "+u.Username+" has been deleted");
				break;
			}
		}
		
	}
	public static void writedataintoTextfile() throws IOException {
		File file = new File("C:\\Users\\ganuk\\eclipse-workspace\\ShopManagement21sept\\src\\UserMGMT\\user.csv");
		
		FileWriter fw = new FileWriter(file,false);
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(User u:al) {
			bw.write(u.Username+",");
			bw.write(u.LoginName+",");
			bw.write(u.Password+",");
			bw.write(u.ConfirmPassword+",");
			bw.write(u.UserRole+",");
			bw.newLine();
		}
		bw.close();
		fw.close();
		file = null;
	}
	public static void LoadDataFromfileToArrayList() throws IOException {
		File file = new File("C:\\Users\\ganuk\\eclipse-workspace\\ShopManagement21sept\\src\\UserMGMT\\user.csv");
		
		FileReader fr = new FileReader(file);
		
		BufferedReader br = new BufferedReader(fr);
		
		String data = "";
		
		while((data=br.readLine()) != null) {
			User u = new User();
			String[] array = data.split(",");
			if(array.length > 3) {
				u.Username = array[0].trim();
				u.LoginName = array[1].trim();
				u.Password = array[2].trim();
				u.ConfirmPassword = array[3].trim();
				u.UserRole = array[4].trim();
				
				al.add(u);
			}
		}
		br.close();
		fr.close();
		file = null;
	}
	public static boolean ValidateUserAndPassword(String loginName,String password) {
		Iterator<User> itr = al.iterator();
		
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.LoginName.equalsIgnoreCase(loginName) && user.Password.equalsIgnoreCase(password)) {
				return true;
			}
		}
		return false;
	}

}
