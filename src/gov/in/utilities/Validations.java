
package gov.in.utilities;

import java.util.Scanner;

public class Validations {

	static Scanner sc =new Scanner(System.in);
	public static final String NAME_PATTERN= "^[A-Za-z]+(\\s[A-Za-z]+)*$";
	public static final String EMAIL_PATTERN= "^[a-zA-Z0-9._%+-]+@[a-zAZ0-9.-]+\\.[a-zA-Z]{2,}$";
	public static final String PHONE_PATTERN= "^\\d{10}$";
	
	public String validateName(String nextLine) {
		while (true) {
			if(nextLine.length()>2 && nextLine.matches(NAME_PATTERN))
				return nextLine;
			System.out.print("Name size should be more than 2\n Enter name.:");
			nextLine = sc.nextLine();
		}
		
	}

	public String validateemail(String nextLine) {
		while (true) {
			if(nextLine.matches(EMAIL_PATTERN))
				return nextLine;
			System.out.print("Email should be valid \n Enter email.:");
			nextLine = sc.nextLine();
		}
	}

	public String validatephone(String nextLine) {
		while (true) {
			if(nextLine.matches(PHONE_PATTERN))
				return nextLine;
			System.out.print("Phone no should be of size 10 and digits only\n Enter phone.:");
			nextLine = sc.nextLine();
		}
	}

	public int validateInt(String nextint) {
		while (true) {
			try {
                int i = Integer.parseInt(nextint);
                return i;

            } catch (NumberFormatException e) {
                System.out.println("Enter valid Integer value.\n Enter again.:");
                nextint = sc.nextLine();
            }

		}

	}

}
