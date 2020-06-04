package application.exception;

public class GameOverException extends Exception {
	
	public GameOverException() {
		System.err.println("Perdu !");
	}
}

