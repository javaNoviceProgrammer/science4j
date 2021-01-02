package visualizer4j.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Mapper {
	
	public static Map<String, Float> getMap(Collection<String> e, boolean useLog) {
		// try with numbers
		boolean canNumber = true;
		double min = Double.MAX_VALUE;
		double max = -Double.MAX_VALUE;
		try {
			for (String s : e) {
				double val = Double.parseDouble(s);
			/*	if (val < 1e-18 || val > 1e9) {
					System.out.print("h");
				}*/
				if (useLog) val = Math.log(val);
				if (val < min) {
					min = val;
				}
				if (val > max) {
					max = val;
				/*	if (max > 0) {
						System.out.print(".");
					}*/
				}
			}
		}
		catch (Exception exce) {
			canNumber = false;
		}
		HashMap<String, Float> result = new HashMap<String, Float>();
		if (canNumber) {
			double diff = max-min;
			for (String s : e) {
				double val = Double.parseDouble(s);
				if (useLog) val = Math.log(val);				
				float frac = (float)((val - min)/diff);
				result.put(s, frac);
			}
		} else {
			ArrayList<String> s = new ArrayList<String>(e);
			Collections.sort(s);
			float size = s.size();
			float i = 0;
			for (String str : s) {
				result.put(str, i/size);
				i++;
			}
		}
		return result;
	}
	
	public static Map<String, Float> getMap(Collection<String> e) {
		return getMap(e, false);
	}

}
