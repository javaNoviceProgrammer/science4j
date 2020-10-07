package org.gsl4j;

import org.gsl4j.util.NativeLibraryLoader;

public class ElementaryFunctions {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private ElementaryFunctions() {

	}

	/**
	 * This function computes the value of \log(1+x) in a way that is accurate for small x. It provides an alternative to the BSD math function log1p(x).
	 * @param x
	 * @return
	 */
	public static native double log1p(double x) ;

	/**
	 * This function computes the value of \exp(x)-1 in a way that is accurate for small x. It provides an alternative to the BSD math function expm1(x).
	 * @param x
	 * @return
	 */
	public static native double expm1(double x) ;

	/**
	 * This function computes the value of \sqrt{x^2 + y^2} in a way that avoids overflow. It provides an alternative to the BSD math function hypot(x,y).
	 * @param x
	 * @param y
	 * @return
	 */
	public static native double hypot(double x, double y) ;

	/**
	 * This function computes the value of \sqrt{x^2 + y^2 + z^2} in a way that avoids overflow.
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static native double hypot3(double x, double y, double z) ;

	/**
	 * This function computes the value of \arccosh{(x)}. It provides an alternative to the standard math function acosh(x).
	 * @param x
	 * @return
	 */
	public static native double acosh(double x) ;

	/**
	 * This function computes the value of \arcsinh{(x)}. It provides an alternative to the standard math function asinh(x).
	 * @param x
	 * @return
	 */
	public static native double asinh(double x) ;

	/**
	 * This function computes the value of \arctanh{(x)}. It provides an alternative to the standard math function atanh(x).
	 * @param x
	 * @return
	 */
	public static native double atanh(double x) ;

	/**
	 * This function computes the value of x * 2^e. It provides an alternative to the standard math function ldexp(x,e).
	 * @param x
	 * @param n
	 * @return
	 */
	public static native double ldexp(double x, int n) ;

	/**
	 * This function splits the number x into its normalized fraction f and exponent e, such that x = f * 2^e and 0.5 <= f < 1. The function returns f and stores the exponent in e. If x is zero, both f and e are set to zero. This function provides an alternative to the standard math function frexp(x, e).
	 * @param x
	 * @param n
	 * @return
	 */
	public static native double[] frexp(double x, int n) ;

	/**
	 * These routines computes the power x^n for integer n. The power is computed efficiently—for example, x^8 is computed as ((x^2)^2)^2, requiring only 3 multiplications. A version of this function which also computes the numerical error in the result is available as gsl_sf_pow_int_e().
	 * @param x
	 * @param n
	 * @return
	 */
	public static native double powInt(double x, int n) ;

	/**
	 * These functions can be used to compute small integer powers x^2, x^3, etc. efficiently. The functions will be inlined when HAVE_INLINE is defined, so that use of these functions should be as efficient as explicitly writing the corresponding product expression:
	 * @param x
	 * @return
	 */
	public static native double pow2(double x) ;

	/**
	 * These functions can be used to compute small integer powers x^2, x^3, etc. efficiently. The functions will be inlined when HAVE_INLINE is defined, so that use of these functions should be as efficient as explicitly writing the corresponding product expression:
	 * @param x
	 * @return
	 */
	public static native double pow3(double x) ;

	/**
	 * These functions can be used to compute small integer powers x^2, x^3, etc. efficiently. The functions will be inlined when HAVE_INLINE is defined, so that use of these functions should be as efficient as explicitly writing the corresponding product expression:
	 * @param x
	 * @return
	 */
	public static native double pow4(double x) ;

	/**
	 * These functions can be used to compute small integer powers x^2, x^3, etc. efficiently. The functions will be inlined when HAVE_INLINE is defined, so that use of these functions should be as efficient as explicitly writing the corresponding product expression:
	 * @param x
	 * @return
	 */
	public static native double pow5(double x) ;

	/**
	 * These functions can be used to compute small integer powers x^2, x^3, etc. efficiently. The functions will be inlined when HAVE_INLINE is defined, so that use of these functions should be as efficient as explicitly writing the corresponding product expression:
	 * @param x
	 * @return
	 */
	public static native double pow6(double x) ;

	/**
	 * These functions can be used to compute small integer powers x^2, x^3, etc. efficiently. The functions will be inlined when HAVE_INLINE is defined, so that use of these functions should be as efficient as explicitly writing the corresponding product expression:
	 * @param x
	 * @return
	 */
	public static native double pow7(double x) ;

	/**
	 * These functions can be used to compute small integer powers x^2, x^3, etc. efficiently. The functions will be inlined when HAVE_INLINE is defined, so that use of these functions should be as efficient as explicitly writing the corresponding product expression:
	 * @param x
	 * @return
	 */
	public static native double pow8(double x) ;

	/**
	 * These functions can be used to compute small integer powers x^2, x^3, etc. efficiently. The functions will be inlined when HAVE_INLINE is defined, so that use of these functions should be as efficient as explicitly writing the corresponding product expression:
	 * @param x
	 * @return
	 */
	public static native double pow9(double x) ;

	/**
	 * This macro returns the sign of x. It is defined as ((x) >= 0 ? 1 : -1). Note that with this definition the sign of zero is positive (regardless of its IEEE sign bit).
	 * @param x
	 * @return
	 */
	public static native int sign(double x) ;

	/**
	 * This macro evaluates to 1 if n is odd and 0 if n is even. The argument n must be of integer type.
	 * @param x
	 * @return
	 */
	public static native boolean isOdd(int x) ;

	/**
	 * This macro is the opposite of GSL_IS_ODD. It evaluates to 1 if n is even and 0 if n is odd. The argument n must be of integer type.
	 * @param x
	 * @return
	 */
	public static native boolean isEven(int x) ;

	/**
	 * This macro returns the maximum of a and b. It is defined as ((a) > (b) ? (a):(b)).
	 * @param a
	 * @param b
	 * @return
	 */
	public static native double max(double a, double b) ;

	/**
	 * This macro returns the minimum of a and b. It is defined as ((a) < (b) ? (a):(b)).
	 * @param a
	 * @param b
	 * @return
	 */
	public static native double min(double a, double b) ;

	/**
	 * This function determines whether x and y are approximately equal to a relative accuracy epsilon. The relative accuracy is measured using an interval of size 2 \delta, where \delta = 2^k \epsilon and k is the maximum base-2 exponent of x and y as computed by the function frexp(). If x and y lie within this interval, they are considered approximately equal and the function returns 0. Otherwise if x < y, the function returns -1, or if x > y, the function returns +1. Note that x and y are compared to relative accuracy, so this function is not suitable for testing whether a value is approximately zero.
	 * <br>
	 * The implementation is based on the package fcmp by T.C. Belding.
	 * @param x
	 * @param y
	 * @param epsilon
	 * @return
	 */
	public static native int fcmp(double x, double y, double epsilon) ;

	public static native double multiply(double x, double y) ;
	public static native double multiplyError(double x, double dx, double y, double dy) ;


}
