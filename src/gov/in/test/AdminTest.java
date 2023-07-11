package gov.in.test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import gov.in.domain.Admin;
import gov.in.domain.Scheme;
import gov.in.domain.User;
import gov.in.exception.NotFoundException;
import gov.in.utilities.Validations;

public class AdminTest implements AdminFunctionalities{

	private static Scanner sc = new Scanner(System.in);
	public static ArrayList<Admin> adminArr = new ArrayList<>();
	
	//singleton class.
	private static AdminTest a = new AdminTest();
	private AdminTest() {	}
	static public AdminTest getReference() {
		return a;
	}
	
	private static Validations validate = new Validations();
	
	@Override
	public void login() throws NotFoundException{
		if(AdminTest.adminArr!=null) {
			System.out.print("\n  Enter Admin id.: ");
			int i = validate.validateInt(sc.nextLine());
			Admin a =new Admin();
			a.setId(i);
//			sc.nextLine();
			System.out.print("  Enter password.: ");
			String password = sc.nextLine();
			if(AdminTest.adminArr.contains(a)) {
				if(((adminArr.get(adminArr.indexOf(a))).getPassword()).equals(password)) {
					System.out.println("======================================");
			        System.out.println("           \u001B[34mADMIN PAGE\u001B[0m    ");
			        System.out.println("======================================");
					System.out.println("  Hi "+(adminArr.get(adminArr.indexOf(a))).getFName());
					AdminTest.adminOperations();
				}
				else
					System.err.println("Wrong Password Entered");	
			}
			else {
				System.err.print("Admin ");
				throw new NotFoundException();
			}
		}		
	}
	private static void adminOperations() {
		int choice;
		while((choice=AdminTest.menu())!=0) {
			switch(choice) {
			case 1:
				System.out.println("");
				SchemeTest.getReference().viewAllSchemes();
				break;
			case 2:
				a.addScheme();
				break;
			case 3:
				a.removeScheme();
				break;
			case 4:
				System.out.println("");
				UserTest ut = UserTest.getReference();
				ut.printUser();
				break;
			case 5:
				AdminTest.adminArr.forEach(System.out::println);
				break;
			case 6:
				AdminTest.removeUser();
				break;
			}
		}
	}
	
	private static int menu() {
		System.out.println("\n  0. Exit");
		System.out.println("  1. Show all schemes.");
		System.out.println("  2. Add scheme.");
		System.out.println("  3. Remove scheme");
		System.out.println("  4. View all registered Users");
		System.out.println("  5. View all registered Admins");
		System.out.println("  6. Remove user");
		System.out.print("  Enter your choice.:");
		return validate.validateInt(sc.nextLine());
	}
	
	private static void removeUser() throws NotFoundException{
		System.out.println("Enter user id to remove.:");
		int id = validate.validateInt(sc.nextLine());
		User u = new User();
		u.setId(id);
		if(UserTest.userList.contains(u)) {
			UserTest.userList.remove(UserTest.userList.indexOf(u));
			System.out.println("\u001B[32m\n  User removed successfully!\u001B[0m");
		}
		else
			throw new NotFoundException();
	}
	@Override
	public void addScheme() {
		Scheme s = AdminTest.acceptSchemeRecord();
		if(s!=null) {
			SchemeTest.schemeArr.add(s);
			System.out.println("\u001B[32m\n Scheme Added successfully!\u001B[0m");
		}
	}
	private static Scheme acceptSchemeRecord() throws InputMismatchException{
//		sc.nextLine();
		System.out.print("  Enter Scheme ID.: ");
		String id = sc.nextLine();
		System.out.print("  Enter Scheme name.: ");
		String name = validate.validateName(sc.nextLine());
		System.out.print("  Enter start age.: ");
		int sAge = validate.validateInt(sc.nextLine());
		System.out.print("  Enter end age.: ");
		int eAge = validate.validateInt(sc.nextLine());
//		sc.nextLine();
		System.out.println("  Enter Scheme details.:");
		String details = sc.nextLine();
		String category[] = AdminTest.acceptCategories();
		Scheme s= new Scheme(id,name,sAge,eAge,category,details);
		return s;
	}
	private static String[] acceptCategories() {
		int i = 0; char c;
		String categories[] = new String[5];
		System.out.println("Enter y if this scheme is relevant to students.:");
		c= sc.nextLine().charAt(0);
		if(c=='y' || c=='Y')
			categories[i++]="Student";
		System.out.println("Enter y if this scheme is relevant to retired  person.:");
		c= sc.nextLine().charAt(0);
		if(c=='y' || c=='Y')
			categories[i++]="Retired";
		System.out.println("Enter y if this scheme is relevant to farmer.:");
		c= sc.nextLine().charAt(0);
		if(c=='y' || c=='Y')
			categories[i++]="Farmer";
		System.out.println("Enter y if this scheme is relevant to women.:");
		c= sc.nextLine().charAt(0);
		if(c=='y' || c=='Y')
			categories[i++]="Women";
		System.out.println("Enter y if this scheme is relevant to startup owner.:");
		c= sc.nextLine().charAt(0);
		if(c=='y' || c=='Y')
			categories[i++]="StartUp";
		
		return categories;
	}
	@Override
	public void removeScheme() {
		System.out.print("  Enter Id of scheme you want to remove.:");
		String str = sc.nextLine();
		Scheme s = new Scheme();
		s.setId(str);
		if(SchemeTest.schemeArr.contains(s)) {
			SchemeTest.schemeArr.remove(SchemeTest.schemeArr.indexOf(s));
			if(UserTest.userList!=null) {
				for(User u: UserTest.userList) {
					if(u.getSchemes().contains(s)) {
						u.getSchemes().remove(u.getSchemes().indexOf(s));
					}
				}
			}
			System.out.println("\u001B[32m\n  Removed succesfully.\u001B[0m");
		}
		else
			System.err.println("Scheme with given id NOT available.:");
	}
	@Override
	public void addAdmin() {
		if(AdminTest.adminArr!=null) {
			Admin a = AdminTest.acceptAdmin();
			if(a!=null) {
				AdminTest.adminArr.add(a);
				System.out.println("\u001B[32m\nAdmin Added successfully!\u001B[0m");
			}
			else
				System.out.println("Invalid input!");
		}		
	}
	
	private static Admin acceptAdmin() {
		System.out.print("Enter first name.:");
		String fname = validate.validateName(sc.nextLine());
		System.out.print("Enter last name.:");
		String lname = validate.validateName(sc.nextLine());
		System.out.print("Enter contactNo.:");
		String cNo = validate.validatephone(sc.nextLine());
		System.out.print("Enter email.:");
		String email = validate.validateemail(sc.nextLine());
		System.out.print("Enter address.:");
		String add = sc.nextLine();
		System.out.print("Enter password.:");
		String password = sc.nextLine();
		Admin a = new Admin(fname,lname,cNo,email,add,password);
		return a;
	}
}
