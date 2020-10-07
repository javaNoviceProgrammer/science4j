package org.gsl4j.interpolate.twod;

import org.gsl4j.interpolate.Interpolation2D;

public class BilinearInterpolation extends Interpolation2D {

	public BilinearInterpolation(double[] x, double[] y, double[] z) {
		super(x, y, z, BILINEAR);
	}

	public BilinearInterpolation(double[] x, double[] y, double[][] z) {
		super(x, y, z, BILINEAR) ;
	}

}
