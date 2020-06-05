package application.utils;

public class utils {

	// (b� + c� - a�) / 2 * b * c
	
	public static double getAngle(int monstreX, int monstreY, int tourelleX, int tourelleY) {
		return Math.toDegrees(Math.atan2(tourelleY - monstreY, tourelleX - monstreX) - Math.PI / 2);
	}
}
