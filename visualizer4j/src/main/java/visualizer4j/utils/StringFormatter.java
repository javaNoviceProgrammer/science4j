package visualizer4j.utils;

public class StringFormatter {
	
	public static String zeroPadding(int index, int maxIndex) {
		int zeros = (int)Math.max(Math.ceil(Math.log10(maxIndex)),1);
		return String.format("%0"+zeros+"d", index);
	}

}
