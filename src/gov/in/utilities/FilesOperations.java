package gov.in.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gov.in.domain.Admin;
import gov.in.domain.Scheme;
import gov.in.domain.User;
import gov.in.test.AdminTest;
import gov.in.test.SchemeTest;
import gov.in.test.UserTest;

public class FilesOperations {
	
	public static void write(){
		try( ObjectOutputStream outputStream = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream(new File("User.dat"))))){
			outputStream.writeObject(UserTest.userList);
		}
		catch(Exception e) {
			e.printStackTrace();
//			throw new FileException();
		}
		try( ObjectOutputStream outputStream = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream(new File("Admin.dat"))))){
			outputStream.writeObject(AdminTest.adminArr);
		}
		catch(Exception e) {
			e.printStackTrace();
//			throw new FileException();
		}
		try( ObjectOutputStream outputStream = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream(new File("Scheme.dat"))))){
			outputStream.writeObject(SchemeTest.schemeArr);
		}
		catch(Exception e) {
			e.printStackTrace();
//			throw new FileException();
		}
	}
	public static void read(){
		try( ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream( new FileInputStream(new File("User.dat"))))){
			UserTest.userList = (ArrayList<User>) inputStream.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
//			throw new FileException();
		}
		try( ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream( new FileInputStream(new File("Admin.dat"))))){
			AdminTest.adminArr = (ArrayList<Admin>) inputStream.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
//			throw new FileException();
		}
		try( ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream( new FileInputStream(new File("Scheme.dat"))))){
			SchemeTest.schemeArr = (ArrayList<Scheme>) inputStream.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
//			throw new FileException();
		}
	}

}
