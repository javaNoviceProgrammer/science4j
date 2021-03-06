package org.gsl4j.domain;

public class SquareDomain implements Domain2d {

	double xmin, xmax ;
	double ymin, ymax ;

	private SquareDomain(double xmin, double xmax, double ymin, double ymax) {
		this.xmin = xmin ;
		this.xmax = xmax ;
		this.ymin = ymin ;
		this.ymax = ymax ;
	}

	public static SquareDomain fromCenterAndWidth(double xcenter, double ycenter, double width) {
		double xmin = xcenter - 0.5*width ;
		double xmax = xcenter + 0.5*width ;
		double ymin = ycenter - 0.5*width ;
		double ymax = ycenter + 0.5*width ;
		return new SquareDomain(xmin, xmax, ymin, ymax) ;
	}

	public static SquareDomain fromLowerLeftAndUpperRight(double xll, double yll, double xur, double yur) {
		return new SquareDomain(xll, xur, yll, yur) ;
	}

	@Override
	public double var1Min() {
		return xmin ;
	}

	@Override
	public double var1Max() {
		return xmax ;
	}

	@Override
	public double var2Min(double var1) {
		return ymin ;
	}

	@Override
	public double var2Max(double var1) {
		return ymax ;
	}



}
