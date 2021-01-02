package visualizer4j.utils;


public class Percentage {

	private float fper = 0;

	public Percentage(int per) {
		if ((per >= 0) && (per <= 100)) {
			fper = per;
		}
	}

	public float getPercentage() {
		return fper;
	}
}
