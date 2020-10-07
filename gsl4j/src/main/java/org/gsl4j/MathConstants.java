package org.gsl4j;

import org.gsl4j.util.NativeLibraryLoader;

/**
 * The library ensures that the standard BSD mathematical constants are defined. For reference, this class has a list of the constants.
 *
 * @author Meisam
 *
 */
public class MathConstants {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private MathConstants() {

	}

	/**
	 * 	The base of exponentials, e
	 */
	public static final double M_E = me() ;

	/**
	 * The base-2 logarithm of e, \log_2 (e)
	 */
	public static final double M_LOG2E = mlog2e() ;

	/**
	 * The base-10 logarithm of e, \log_{10} (e)
	 */
	public static final double M_LOG10E = mlog10e() ;

	/**
	 * 	The square root of two, \sqrt 2
	 */
	public static final double M_SQRT2 = msqrt2() ;

	/**
	 * The square root of one-half, \sqrt{1/2}
	 */
	public static final double M_SQRT1_2 = msqrt12() ;

	/**
	 * The square root of three, \sqrt 3
	 */
	public static final double M_SQRT3 = msqrt3() ;

	/**
	 * The constant pi, \pi
	 */
	public static final double M_PI = mpi() ;

	/**
	 * Pi divided by two, \pi/2
	 */
	public static final double M_PI_2 = mpi2() ;

	/**
	 * Pi divided by four, \pi/4
	 */
	public static final double M_PI_4 = mpi4() ;

	/**
	 * The square root of pi, \sqrt\pi
	 */
	public static final double M_SQRTPI = msqrtpi() ;

	/**
	 * Two divided by the square root of pi, 2/\sqrt\pi
	 */
	public static final double M_2_SQRTPI = m2sqrtpi() ;

	/**
	 * The reciprocal of pi, 1/\pi
	 */
	public static final double M_1_PI = m1pi() ;

	/**
	 * Twice the reciprocal of pi, 2/\pi
	 */
	public static final double M_2_PI = m2pi() ;

	/**
	 * The natural logarithm of ten, \ln(10)
	 */
	public static final double M_LN10 = mln10() ;

	/**
	 * 	The natural logarithm of two, \ln(2)
	 */
	public static final double M_LN2 = mln2() ;

	/**
	 * The natural logarithm of pi, \ln(\pi)
	 */
	public static final double M_LNPI = mlnpi() ;

	/**
	 * 	Euler’s constant, \gamma
	 */
	public static final double M_EULER = meuler() ;

	/**
	 * This macro contains the IEEE representation of positive infinity, +\infty. It is computed from the expression +1.0/0.0.
	 */
	public static final double GSL_POSINF = gslposinf() ;

	/**
	 * This macro contains the IEEE representation of negative infinity, -\infty. It is computed from the expression -1.0/0.0.
	 */
	public static final double GSL_NEGINF = gslneginf() ;

	/**
	 * This macro contains the IEEE representation of the Not-a-Number symbol, NaN. It is computed from the ratio 0.0/0.0.
	 */
	public static final double GSL_NAN = gslnan() ;

	private static native double me() ;
	private static native double mlog2e() ;
	private static native double mlog10e() ;
	private static native double msqrt2() ;
	private static native double msqrt12() ;
	private static native double msqrt3() ;
	private static native double mpi() ;
	private static native double mpi2() ;
	private static native double mpi4() ;
	private static native double msqrtpi() ;
	private static native double m2sqrtpi() ;
	private static native double m1pi() ;
	private static native double m2pi() ;
	private static native double mln10() ;
	private static native double mln2() ;
	private static native double mlnpi() ;
	private static native double meuler() ;
	private static native double gslposinf() ;
	private static native double gslneginf() ;
	private static native double gslnan() ;

	/**
	 * This function returns true if x is not-a-number.
	 * @param x
	 * @return
	 */
	public static native boolean isNaN(double x) ;

	/**
	 * This function returns true if x is infinity and false otherwise.
	 * @param x
	 * @return
	 */
	public static native boolean isInf(double x) ;

	/**
	 * This function returns true if x is positive infinity.
	 * @param x
	 * @return
	 */
	public static native boolean isPositiveInf(double x) ;

	/**
	 * This function returns true if x is negative infinity.
	 * @param x
	 * @return
	 */
	public static native boolean isNegativeInf(double x) ;

	/**
	 * This function returns true if x is a real number, and false if it is infinite or not-a-number.
	 * @param x
	 * @return
	 */
	public static native boolean isFinite(double x) ;

	// precision for special functions
	public static final int GSL_PREC_DOUBLE = 0 ;
	public static final int GSL_PREC_SINGLE = 0 ;
	public static final int GSL_PREC_APPROX = 0 ;

}
