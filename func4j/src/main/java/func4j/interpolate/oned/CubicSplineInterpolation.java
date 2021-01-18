package func4j.interpolate.oned;

import func4j.interpolate.Interpolation1D;

public class CubicSplineInterpolation extends Interpolation1D {

	public CubicSplineInterpolation(double[] x, double[] y) {
		super(x, y, CUBIC_SPLINE) ;
	}

}
