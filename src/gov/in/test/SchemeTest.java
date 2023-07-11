package gov.in.test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import gov.in.domain.Scheme;
import gov.in.exception.NotFoundException;
import gov.in.utilities.Validations;

//singleton
public class SchemeTest {
	
	public static ArrayList<Scheme>  schemeArr = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);
	private static Validations validate = new Validations();
	
	private static SchemeTest s = new SchemeTest();
	private SchemeTest() {	}
	static public SchemeTest getReference() {
		return s;
	}
	
	public void viewAllSchemes() {
		if(schemeArr!=null) {
			schemeArr.sort(null);
			schemeArr.forEach(System.out::println);
		}
	}
	
	public void showScheme() throws NotFoundException{
		if(schemeArr!=null) {
			System.out.print("Enter scheme ID.:");
			String id = sc.nextLine();
			Scheme s = new Scheme();
			s.setId(id);
				if(schemeArr.contains(s)) {
					System.out.println("Details of the required scheme.:");
					System.out.println(schemeArr.get(schemeArr.indexOf(s)).toString());
				}
				else {
					throw new NotFoundException();
//					return;
				}
		}
	}
	
	public void showCategoryWise() {
		if(schemeArr!=null) {
			String c = SchemeTest.categoryMenu();
			for(Scheme s: schemeArr) {
				for(String str: s.getCategory()) {
					if(str!=null && str.equals(c)) {
						System.out.println(s.toString());
						break;
					}
				}
			}
		}
	}

	public static String categoryMenu() throws InputMismatchException{
		System.out.println("  1.Student");
		System.out.println("  2.Retired");
		System.out.println("  3.Farmer");
		System.out.println("  4.Women");
		System.out.println("  5.Startup");
		System.out.print("  Enter choice.:");
		int i= validate.validateInt(sc.nextLine());
		if((i) == 1)
			return "Student";
		else if((i) == 2)
			return "Retired";
		else if((i) == 3)
			return "Farmer";
		else if((i) == 4)
			return "Women";
		else if((i) == 5)
			return "StartUp";
		else {
			System.out.println("Enter valid no.:");
			SchemeTest.categoryMenu();
			return "";
		}
	}
}
