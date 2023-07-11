package gov.in.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import gov.in.domain.Scheme;
import gov.in.domain.User;
import gov.in.domain.UserScheme;
import gov.in.exception.NotFoundException;
import gov.in.utilities.Validations;

public class UserTest {
	
	static Scanner sc = new Scanner(System.in);
	public static ArrayList<User> userList = new ArrayList<>();
	
	private static Validations validate = new Validations();

	private static UserTest ut = new UserTest();
	private UserTest() {	}
	static public UserTest getReference() {
		return ut;
	}
	
	public void addUser(User[] users) {
		for (User user : users) {
			UserTest.userList.add(user);
		}
		System.out.println("\u001B[32m\n User added successfully.\u001B[0m");

	}

	public void printUser() {
		
		UserTest.userList.forEach(System.out::println);

	}

	public void LoginUser() throws NotFoundException{
		boolean validID = false;
		int id = 0;
		User user = null;
		while (!validID) {
				System.out.print("\n Enter ID (numeric value):");
				id = validate.validateInt(sc.nextLine());
				for (User u : userList) {
					if (u.getId() == id) {
						user = u;
						validID = true;
						break;
					}
					else
						validID = false;
				}
		}
		System.out.println("======================================");
        System.out.println("           \u001B[34mUSER PAGE\u001B[0m    ");
        System.out.println("======================================");
        
		System.out.println("\n    Welcome, " + user.getFName() + "!\n");

		boolean loggedIn = true;
		while (loggedIn) {
			System.out.println("  Choose an option:");
			System.out.println("  1. View all schemes");
			System.out.println("  2. Show details of a particular scheme");
			System.out.println("  3. View all schemes related to me");
			System.out.println("  4. Show schemes based on categories");
			System.out.println("  5. Show my applied schemes");
			System.out.println("  6. Apply for a scheme");
			System.out.println("  7. Logout");

			int choice = 0;
				choice = validate.validateInt(sc.nextLine());
			switch (choice) {
			case 1:
				SchemeTest.getReference().viewAllSchemes();
				break;
			case 2:
				SchemeTest.getReference().showScheme();
				break;
			case 3:
				viewRelatedSchemes(id);
				break;
			case 4:
				SchemeTest.getReference().showCategoryWise();
				break;
			case 5:
				showAppliedScheme(id);
				break;
			case 6:
				applyScheme(id);
				break;
			case 7:
				loggedIn = false;
				System.out.println("\n  Logged out. Thank you for Using our service!\n");
				break;
			default:
				System.out.println("\n  Invalid choice. Please enter a valid option number(1-7).\n");
				break;
			}
		}

	}

	private void showAppliedScheme(int id) {
		User u = new User();
		u.setId(id);
		User real = userList.get(userList.indexOf(u));
		System.out.println();
		for(UserScheme s : real.getSchemes()) {
			System.out.println(s);
		}
	}

	private void viewRelatedSchemes(int id) {
		User u = new User();
		u.setId(id);
		User real = userList.get(userList.indexOf(u));
		System.out.println();
		for(Scheme scheme : SchemeTest.schemeArr) {
			for(String cat : scheme.getCategory()) {
				if(cat!=null && cat.equals(real.getCategory())) {
					if(real.getAge()>= scheme.getSAge() && real.getAge()<= scheme.getEAge()) {
					System.out.println(scheme.toString());
					break;
					}
				}
			}
			
		}
	
	}

	private void applyScheme(int id) throws NotFoundException{
		System.out.print("  Enter scheme Id : ");
		String schemeid = sc.nextLine();
		Scheme s = new Scheme();
		s.setId(schemeid);

		if (SchemeTest.schemeArr.contains(s)) {
			User u = new User();
			int flag =0;
			u.setId(id);
			Scheme value = SchemeTest.schemeArr.get(SchemeTest.schemeArr.indexOf(s));
			UserScheme us = new UserScheme(value,LocalDate.now(),false);
			User user=userList.get(userList.indexOf(u));
			ArrayList<UserScheme> list = user.getSchemes();
			for(String category: value.getCategory()) {
				if(category!=null && category.equals(user.getCategory()) && user.getAge()>= value.getSAge() && user.getAge()<= value.getEAge()) {
					list.add(us);flag=1;
					System.out.println("\u001B[32m   Applied successfully.\u001B[0m");
					break;}
			}
			if(flag==0)
				System.err.println("You are not eligible for this scheme!");
			}
		else {
			System.err.print("\n  Scheme ");
			throw new NotFoundException();
		}

	}

}