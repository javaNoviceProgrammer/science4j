package movie_timeline;

public class Utils {
	
	public static String toDoubleDigitString(int value) {
		String result ;
		if (value>9) {
			result = "%d".formatted(value) ;
		}
		else {
			result = "0%d".formatted(value) ;
		}
		return result ;
	}

}
