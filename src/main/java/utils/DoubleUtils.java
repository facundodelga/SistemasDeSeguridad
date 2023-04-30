package utils;

public class DoubleUtils {
	public static boolean equals(double d1, double d2, double epsilon) {
		return Math.abs(d1 - d2) < epsilon;
	}
	
	public static boolean equals(double d1, double d2) {
		double epsilon = 0.000001d;
		
		return equals(d1, d2, epsilon);
	}
}
