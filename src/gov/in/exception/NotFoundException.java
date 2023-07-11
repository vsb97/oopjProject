package gov.in.exception;

public class NotFoundException extends RuntimeException {
	@Override
	public void printStackTrace() {
		System.err.println("NOT FOUND!");
		return;
	}

}
