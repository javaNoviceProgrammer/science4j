package visualizer4j.math;

import java.math.BigDecimal;

public class Rounder {
	
	
	public static String roundString(double f, int decimals) {
		if (f == Double.POSITIVE_INFINITY) return f+"";
		if (f == Double.NEGATIVE_INFINITY) return f+"";
		Double dd = f;
		if (dd.equals(Double.NaN)) return "N/A";
		if (decimals < 1) {
			throw new IllegalArgumentException("n < 1");
		}
		if (decimals > 17) {
			return f+"";
		}
		if (decimals == 0) {
			return 0+"";
		}
		if (f > Math.pow(10,decimals)) return (int)f+"";
		if (f == 0) return "0";


		//BigDecimal signedScale = new BigDecimal(10);
		int pow;
		if (f < 0) {
			pow = (int)Math.floor(Math.log10(-f)) - decimals + 1;
		} else {
			pow = (int)Math.floor(Math.log10(f)) - decimals + 1;
		}
		if (pow < 0) {
			BigDecimal multB = new BigDecimal("10");
			multB = multB.pow(-pow);
			double mult = Math.pow(10, -pow);
			BigDecimal F = new BigDecimal((double)Math.round(mult * f));
			try {
				BigDecimal G = F.divide(multB);
				return G.toString();
			}
			catch (ArithmeticException ex) {}
			return null;
		} else {
			double div = Math.pow(10, pow);
			BigDecimal F = new BigDecimal((double)Math.round(f / div));
			BigDecimal G = F.multiply(new BigDecimal(div));
			return G.toString();			
		}	
	}

	public static double round(double f, int decimals) {
		if (decimals < 1) {
			throw new IllegalArgumentException("n < 1");
		}
		if (decimals > 17) {
			return f;
		}
		if (decimals == 0) {
			return 0;
		}
		final double signedScale = f < 0 ?
				-Math.pow(10, Math.floor(Math.log10(-f)) - decimals + 1) :
					Math.pow(10, Math.floor(Math.log10(f)) - decimals + 1);
				return Math.floor(f / signedScale + 0.5) * signedScale;
	}

	public static float round(float f, int decimals) {
		if (decimals < 1) {
			throw new IllegalArgumentException("n < 1");
		}
		if (decimals > 17) {
			return f;
		}
		if (decimals == 0) {
			return 0;
		}
		final double signedScale = f < 0 ?
				-Math.pow(10, Math.floor(Math.log10(-f)) - decimals + 1) :
					Math.pow(10, Math.floor(Math.log10(f)) - decimals + 1);
				return (float)(Math.floor(f / signedScale + 0.5) * signedScale);
	}

}
