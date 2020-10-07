package org.gsl4j.interpolate.oned;

import org.gsl4j.interpolate.Interpolation1D;

public class AkimaSplineInterpolation extends Interpolation1D {

	public AkimaSplineInterpolation(double[] x, double[] y) {
		super(x, y, AKIMA);
	}



}
