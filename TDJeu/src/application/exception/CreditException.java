package application.exception;

public class CreditException extends Exception {
	
	public CreditException() {
		System.err.println("Cr�dits insuffisants");
	}
}

