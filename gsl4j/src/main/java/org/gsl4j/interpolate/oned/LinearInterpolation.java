package org.gsl4j.interpolate.oned;

import org.gsl4j.interpolate.Interpolation1D;

public class LinearInterpolation extends Interpolation1D {

	public LinearInterpolation(double[] x, double[] y) {
		super(x, y, LINEAR) ;
	}

}
