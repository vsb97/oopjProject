package gov.in.main;

import java.util.ArrayList;
import java.util.Scanner;

import gov.in.domain.User;
import gov.in.exception.NotFoundException;
import gov.in.test.AdminTest;
import gov.in.test.SchemeTest;
import gov.in.test.UserTest;
import gov.in.utilities.FilesOperations;
import gov.in.utilities.Validations;

enum MenuOption {
    EXIT,
    ADD_USER,
    ADD_ADMIN,
    VIEW_ALL_SCHEMES,
    SHOW_DETAILS_OF_PARTICULAR_SCHEME,
    SHOW_SCHEMES_BASED_ON_CATEGORIES,
    LOGIN_USER,
    LOGIN_ADMIN;
}

public class Main {

	private static Scanner sc = new Scanner(System.in);
	private static Validations validate = new Validations();
	
	public static void main(String[] args) {
		// Read data from files 
		FilesOperations.read();
		
		System.out.println("\u001B[38;5;208m========================================================================================================");
		System.out.println("                             \u001B[34m------WELCOME TO THE E-SCHEME PORTAL------\u001B[0m    ");
		System.out.println("                                  सरकारी योजना व्यवस्थापनासाठी विश्वसनीय संचालन सिस्टम                   ");
		System.out.println();
		System.out.println("\u001B[38;5;208m========================================================================================================\u001B[0m");
		
        MenuOption choice;
		
		UserTest ut = UserTest.getReference();
		AdminTest at = AdminTest.getReference();
		SchemeTest st = SchemeTest.getReference();
		
		ut.userList.forEach(ele-> System.out.println(ele.toString()));
		at.adminArr.forEach(ele-> System.out.println(ele.toString()));
		st.schemeArr.forEach(ele-> System.out.println(ele.toString()));
		
		//MenuDrivenCode
		while(true) {
			try {
				choice=Main.menuList();
				switch(choice) {
				case EXIT:
                	// updated data write to files
                    FilesOperations.write();
                    System.out.println("");
                    System.out.println("                             \u001B[34mThank you for using our scheme management system!\u001B[0m");
                    return;
                case ADD_USER:
                	System.out.println("\u001B[33m\n** Add new User **\u001B[0m");
                    User user[]=Main.getUsers(1);
                    ut.addUser(user);
                    break;
                case ADD_ADMIN:
                	System.out.println("\u001B[33m\n  ** Add new Admin **\u001B[0m");
                    at.addAdmin();
                    break;
                case VIEW_ALL_SCHEMES:
                    st.viewAllSchemes();
                    break;
                case SHOW_DETAILS_OF_PARTICULAR_SCHEME:
                    st.showScheme();
                    break;
                case SHOW_SCHEMES_BASED_ON_CATEGORIES:
                    st.showCategoryWise();
                    break;
                case LOGIN_USER:
                    ut.LoginUser();
                    break;
                case LOGIN_ADMIN:
                    at.login();
                    break;
				}
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	private static MenuOption menuList() {
		System.out.println("");
		System.out.println("  0. Exit");
		System.out.println("  1. Add User");
		System.out.println("  2. Add Admin");
		System.out.println("  3. View All Schemes");
		System.out.println("  4. Show details of particular scheme");
		System.out.println("  5. Show Schemes based on categories");
		System.out.println("  6. Login User");
		System.out.println("  7. Login Admin");
		System.out.print("  Enter choice.:");
		return MenuOption.values()[validate.validateInt(sc.nextLine())];
	}
	
	private static User[] getUsers(int count) {
		User[] users = new User[count];
		for (int i = 0; i < count; i++) {
			users[i] = getUser();
		}
		return users;
	}
	
	private static User getUser() {
		User user = new User();
//		sc.nextLine();
        System.out.print(" Enter firstname:");
        user.setFName(validate.validateName(sc.nextLine()));
        System.out.print(" Enter lastname:");
        user.setLName(validate.validateName(sc.nextLine()));
        System.out.print(" Enter contact no. (10 digits):");
        user.setContactNo(validate.validatephone(sc.nextLine())); 
        System.out.print(" Enter email:");
        user.setEmail(validate.validateemail(sc.nextLine())); 
        System.out.print(" Enter address:");
        user.setAddress(sc.nextLine());
        System.out.print(" Enter ID (numeric value):");
        user.setId(validate.validateInt(sc.nextLine()));
        System.out.print(" Enter age (numeric value):");
        user.setAge(validate.validateInt(sc.nextLine()));
        System.out.print(" Select one of the category from following.:");
        user.setCategory(SchemeTest.categoryMenu());
        user.setUserSchemList(new ArrayList<>());
//        sc.nextLine();

        return user;
    }

}
