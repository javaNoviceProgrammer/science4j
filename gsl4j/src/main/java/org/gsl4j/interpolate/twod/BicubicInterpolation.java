package org.gsl4j.interpolate.twod;

import org.gsl4j.interpolate.Interpolation2D;

public class BicubicInterpolation extends Interpolation2D {

	public BicubicInterpolation(double[] x, double[] y, double[] z) {
		super(x, y, z, BICUBIC);
	}

	public BicubicInterpolation(double[] x, double[] y, double[][] z) {
		super(x, y, z, BICUBIC) ;
	}

}
