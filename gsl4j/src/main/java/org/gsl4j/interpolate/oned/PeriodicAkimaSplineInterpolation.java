package org.gsl4j.interpolate.oned;

import org.gsl4j.interpolate.Interpolation1D;

public class PeriodicAkimaSplineInterpolation extends Interpolation1D {

	public PeriodicAkimaSplineInterpolation(double[] x, double[] y) {
		super(x, y, AKIMA_PERIODIC);
	}

}
