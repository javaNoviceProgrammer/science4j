package func4j.interpolate.oned;

import func4j.interpolate.Interpolation1D;

public class SteffenInterpolation extends Interpolation1D {

	public SteffenInterpolation(double[] x, double[] y) {
		super(x, y, STEFFEN);
	}

}
