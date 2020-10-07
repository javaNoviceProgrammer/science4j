package org.gsl4j.interpolate;

import org.gsl4j.util.NativeLibraryLoader;

/**
 * This chapter describes functions for performing interpolation. The library provides a variety of interpolation methods, including Cubic, Akima, and Steffen splines. The interpolation types are interchangeable, allowing different methods to be used without recompiling. Interpolations can be defined for both normal and periodic boundary conditions. Additional functions are available for computing derivatives and integrals of interpolating functions. Routines are provided for interpolating both one and two dimensional datasets.
 * <br>These interpolation methods produce curves that pass through each datapoint. To interpolate noisy data with a smoothing curve see Basis Splines.
 * <br>The functions described in this section are declared in the header files {@code gsl_interp.h} and {@code gsl_spline.h}.
 *
 * @author Meisam
 *
 */
public class Interpolation1D {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	/**
	 * Linear interpolation. This interpolation method does not require any additional memory.
	 */
	public static final int LINEAR = 0 ;

	/**
	 * Polynomial interpolation. This method should only be used for interpolating small numbers of points because polynomial interpolation introduces large oscillations, even for well-behaved datasets. The number of terms in the interpolating polynomial is equal to the number of points.
	 */
	public static final int POLYNOMIAL = 1 ;

	/**
	 * Cubic spline with natural boundary conditions. The resulting curve is piecewise cubic on each interval, with matching first and second derivatives at the supplied data-points. The second derivative is chosen to be zero at the first point and last point.
	 */
	public static final int CUBIC_SPLINE = 2 ;

	/**
	 * Cubic spline with periodic boundary conditions. The resulting curve is piecewise cubic on each interval, with matching first and second derivatives at the supplied data-points. The derivatives at the first and last points are also matched. Note that the last point in the data must have the same y-value as the first point, otherwise the resulting periodic interpolation will have a discontinuity at the boundary.
	 */
	public static final int CUBIC_SPLINE_PERIODIC = 3 ;

	/**
	 * Non-rounded Akima spline with natural boundary conditions. This method uses the non-rounded corner algorithm of Wodicka.
	 */
	public static final int AKIMA = 4 ;

	/**
	 * Non-rounded Akima spline with periodic boundary conditions. This method uses the non-rounded corner algorithm of Wodicka.
	 */
	public static final int AKIMA_PERIODIC = 5 ;

	/**
	 * Steffen’s method guarantees the monotonicity of the interpolating function between the given data points. Therefore, minima and maxima can only occur exactly at the data points, and there can never be spurious oscillations between data points. The interpolated function is piecewise cubic in each interval. The resulting curve and its first derivative are guaranteed to be continuous, but the second derivative may be discontinuous.
	 */
	public static final int STEFFEN = 6 ;

	double[] x ;
	double[] y ;
	int type ;

	/**
	 * Given a set of data points (x_1, y_1) ... (x_n, y_n) the routines described in this section compute a continuous interpolating function y(x) such that y(x_i) = y_i. The interpolation is piecewise smooth, and its behavior at the end-points is determined by the type of interpolation used.
	 * @param x
	 * @param y
	 * @param type
	 */
	public Interpolation1D(double[] x, double[] y, int type) {
		this.x = x ;
		this.y = y ;
		this.type = type ;
		cacheData(type) ;
	}

	private native void cacheData(int type) ;

	/**
	 * This function returns the name of the interpolation type used by interp
	 * @return
	 */
	public native String name() ;

	/**
	 * These functions return the minimum number of points required by the interpolation object interp or interpolation type T. For example, Akima spline interpolation requires a minimum of 5 points.
	 * @return
	 */
	public native int minSize() ;

	/**
	 * These functions return the interpolated value of y for a given point x, using the interpolation object interp, data arrays xa and ya and the accelerator acc. When x is outside the range of xa, the error code GSL_EDOM is returned with a value of GSL_NAN for y.
	 * @param x
	 * @return
	 */
	public native double eval(double x) ;

	public native double[] eval(double[] x) ;

	/**
	 * These functions return the derivative d of an interpolated function for a given point x, using the interpolation object interp, data arrays xa and ya and the accelerator acc.
	 * @param x
	 * @return
	 */
	public native double deriv(double x) ;

	public native double[] deriv(double[] x) ;

	/**
	 * These functions return the second derivative d2 of an interpolated function for a given point x, using the interpolation object interp, data arrays xa and ya and the accelerator acc.
	 * @param x
	 * @return
	 */
	public native double deriv2(double x) ;

	public native double[] deriv2(double[] x) ;

	/**
	 * These functions return the numerical integral result of an interpolated function over the range [a, b], using the interpolation object interp, data arrays xa and ya and the accelerator acc.
	 * @param a
	 * @param b
	 * @return
	 */
	public native double integrate(double a, double b) ;

	@Override
	public String toString() {
		return name() ;
	}

}
