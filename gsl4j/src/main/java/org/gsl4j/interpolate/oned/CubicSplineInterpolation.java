package org.gsl4j.interpolate.oned;

import org.gsl4j.interpolate.Interpolation1D;

public class CubicSplineInterpolation extends Interpolation1D {

	public CubicSplineInterpolation(double[] x, double[] y) {
		super(x, y, CUBIC_SPLINE) ;
	}

}
