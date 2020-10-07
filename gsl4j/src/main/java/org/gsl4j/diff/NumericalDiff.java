package org.gsl4j.diff;

import org.gsl4j.function.MathFunction;
import org.gsl4j.util.NativeLibraryLoader;

/**
 * The functions described in this chapter compute numerical derivatives by finite differencing. An adaptive algorithm is used to find the best choice of finite difference and to estimate the error in the derivative. These functions are declared in the header file {@code gsl_deriv.h}.
 *
 * @author Meisam
 *
 */
public class NumericalDiff {

	static {
		NativeLibraryLoader.loadLibraries();
		initIDs() ;
	}

	private NumericalDiff() {

	}

	private static native void initIDs() ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive central difference algorithm with a step-size of h. The derivative is returned in result and an estimate of its absolute error is returned in abserr.
	 * <br>The initial value of h is used to estimate an optimal step-size, based on the scaling of the truncation error and round-off error in the derivative calculation. The derivative is computed using a 5-point rule for equally spaced abscissae at x - h, x - h/2, x, x + h/2, x+h, with an error estimate taken from the difference between the 5-point rule and the corresponding 3-point rule x-h, x, x+h. Note that the value of the function at x does not contribute to the derivative calculation, so only 4-points are actually used.
	 * @param func
	 * @param x
	 * @param h
	 * @return
	 */
	public static native double central(MathFunction func, double x, double h) ;

	/**
	 * Same as {@link #central(MathFunction, double, double)} but acts on an array of double values.
	 *
	 * @param func
	 * @param x
	 * @param h
	 * @return
	 */
	public static native double[] central(MathFunction func, double[] x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive forward difference algorithm with a step-size of h. The function is evaluated only at points greater than x, and never at x itself. The derivative is returned in result and an estimate of its absolute error is returned in abserr. This function should be used if f(x) has a discontinuity at x, or is undefined for values less than x.
	 * The initial value of h is used to estimate an optimal step-size, based on the scaling of the truncation error and round-off error in the derivative calculation. The derivative at x is computed using an “open” 4-point rule for equally spaced abscissae at x+h/4, x + h/2, x + 3h/4, x+h, with an error estimate taken from the difference between the 4-point rule and the corresponding 2-point rule x+h/2, x+h.
	 * @param func
	 * @param x
	 * @param h
	 * @return
	 */
	public static native double forward(MathFunction func, double x, double h) ;

	/**
	 * Same as {@link #forward(MathFunction, double, double)} but acts on an array of double values.
	 *
	 * @param func
	 * @param x
	 * @param h
	 * @return
	 */
	public static native double[] forward(MathFunction func, double[] x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive backward difference algorithm with a step-size of h. The function is evaluated only at points less than x, and never at x itself. The derivative is returned in result and an estimate of its absolute error is returned in abserr. This function should be used if f(x) has a discontinuity at x, or is undefined for values greater than x.
	 * This function is equivalent to calling gsl_deriv_forward() with a negative step-size.
	 * @param func
	 * @param x
	 * @param h
	 * @return
	 */
	public static native double backward(MathFunction func, double x, double h) ;

	/**
	 * Same as {@link #backward(MathFunction, double, double)} but acts on an array of double values.
	 *
	 * @param func
	 * @param x
	 * @param h
	 * @return
	 */
	public static native double[] backward(MathFunction func, double[] x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive central difference algorithm with a step-size of h. The derivative is returned in result and an estimate of its absolute error is returned in abserr.
	 * The initial value of h is used to estimate an optimal step-size, based on the scaling of the truncation error and round-off error in the derivative calculation. The derivative is computed using a 5-point rule for equally spaced abscissae at x - h, x - h/2, x, x + h/2, x+h, with an error estimate taken from the difference between the 5-point rule and the corresponding 3-point rule x-h, x, x+h. Note that the value of the function at x does not contribute to the derivative calculation, so only 4-points are actually used.
	 * @param func
	 * @param x
	 * @param h
	 * @return
	 */
	public static native double[] centralWithError(MathFunction func, double x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive forward difference algorithm with a step-size of h. The function is evaluated only at points greater than x, and never at x itself. The derivative is returned in result and an estimate of its absolute error is returned in abserr. This function should be used if f(x) has a discontinuity at x, or is undefined for values less than x.
	 * The initial value of h is used to estimate an optimal step-size, based on the scaling of the truncation error and round-off error in the derivative calculation. The derivative at x is computed using an “open” 4-point rule for equally spaced abscissae at x+h/4, x + h/2, x + 3h/4, x+h, with an error estimate taken from the difference between the 4-point rule and the corresponding 2-point rule x+h/2, x+h.
	 * @param func
	 * @param x
	 * @param h
	 * @return
	 */
	public static native double[] forwardWithError(MathFunction func, double x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive backward difference algorithm with a step-size of h. The function is evaluated only at points less than x, and never at x itself. The derivative is returned in result and an estimate of its absolute error is returned in abserr. This function should be used if f(x) has a discontinuity at x, or is undefined for values greater than x.
	 * This function is equivalent to calling {@code gsl_deriv_forward()} with a negative step-size.
	 * @param func
	 * @param x
	 * @param h
	 * @return
	 */
	public static native double[] backwardWithError(MathFunction func, double x, double h) ;

}
