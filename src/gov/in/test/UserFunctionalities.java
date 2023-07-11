package gov.in.test;

import gov.in.domain.User;
import gov.in.exception.NotFoundException;

public interface UserFunctionalities {

	void addUser(User[] users);
	void printUser();
	void LoginUser() throws NotFoundException;
	void showAppliedScheme(int id);
	void viewRelatedSchemes(int id);
	void applyScheme(int id) throws NotFoundException;
	
}
