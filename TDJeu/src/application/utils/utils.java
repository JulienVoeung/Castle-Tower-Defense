package application.utils;

public class utils {
	
	public static double getAngle(double monstreX, double monstreY, int tourelleX, int tourelleY) {
		return Math.toDegrees(Math.atan2(tourelleY - monstreY, tourelleX - monstreX) - Math.PI / 2);
	}
}
