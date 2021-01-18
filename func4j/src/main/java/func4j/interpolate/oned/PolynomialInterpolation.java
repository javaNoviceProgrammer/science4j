package func4j.interpolate.oned;

import func4j.interpolate.Interpolation1D;

public class PolynomialInterpolation extends Interpolation1D {

	public PolynomialInterpolation(double[] x, double[] y) {
		super(x, y, POLYNOMIAL);
	}

}
