package visualizer4j.utils;

import java.util.ArrayList;
import java.util.List;

public class Normaliser {

	public static List<Double> normalise(List<Double> list) {
		ArrayList<Double> newList = new ArrayList<Double>(list.size());
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;

		for (int i = 0 ; i < list.size() ; i++) {
			double d = list.get(i);
			if (d < min) {
				min = d;
			}
			if (d > max) {
				max = d;
			}
		}

		for (int i = 0 ; i < list.size() ; i++) {
			newList.add((list.get(i) - min)/max);
		}

		return newList;
	}
}
