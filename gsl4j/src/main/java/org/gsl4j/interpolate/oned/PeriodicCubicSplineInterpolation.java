package org.gsl4j.interpolate.oned;

import org.gsl4j.interpolate.Interpolation1D;

public class PeriodicCubicSplineInterpolation extends Interpolation1D {

	public PeriodicCubicSplineInterpolation(double[] x, double[] y) {
		super(x, y, CUBIC_SPLINE_PERIODIC);
	}

}
