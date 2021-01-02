package visualizer4j.utils;

public class More {

	public static int countNull(Object ... t) {
		int count = 0;
		for (int i = 0 ; i < t.length ; i++) {
			if (t[i] == null) {
				count++;
			}
		}
		return count;
	}
}
