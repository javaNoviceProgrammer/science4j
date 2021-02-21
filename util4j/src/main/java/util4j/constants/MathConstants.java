package util4j.constants;

import util4j.natives.NativeLibraryLoader;

/**
 * The library ensures that the standard BSD mathematical constants are defined.
 * For reference, this class has a list of the constants.
 *
 * @author Meisam
 *
 */
public class MathConstants {

	static {
		NativeLibraryLoader.loadLibraries("/libutil4j_c");
	}

	private MathConstants() {

	}

	/**
	 * 	The base of exponentials, e
	 */
	public static final double E = 2.71828182845904523536028747135      /* e */ ;

	/**
	 * The base-2 logarithm of e, \log_2 (e)
	 */
	public static final double LOG2E = 1.44269504088896340735992468100      /* log_2 (e) */ ;

	/**
	 * The base-10 logarithm of e, \log_{10} (e)
	 */
	public static final double LOG10E = 0.43429448190325182765112891892      /* log_10 (e) */ ;

	/**
	 * 	The square root of two, \sqrt 2
	 */
	public static final double SQRT2 = 1.41421356237309504880168872421      /* sqrt(2) */ ;

	/**
	 * The square root of one-half, \sqrt{1/2}
	 */
	public static final double SQRT1_2 = 0.70710678118654752440084436210      /* sqrt(1/2) */ ;

	/**
	 * The square root of three, \sqrt 3
	 */
	public static final double SQRT3 = 1.73205080756887729352744634151      /* sqrt(3) */ ;

	/**
	 * The constant pi, \pi
	 */
	public static final double PI = 3.14159265358979323846264338328      /* pi */ ;

	/**
	 * Pi divided by two, \pi/2
	 */
	public static final double PI_2 = 1.57079632679489661923132169164      /* pi/2 */ ;

	/**
	 * Pi divided by four, \pi/4
	 */
	public static final double PI_4 = 0.78539816339744830961566084582     /* pi/4 */ ;

	/**
	 * The square of pi, \pi^2
	 */
	public static final double PI2 = 9.86960440108936      /* pi */ ;
	
	/**
	 * The square root of pi, \sqrt\pi
	 */
	public static final double SQRTPI = 1.77245385090551602729816748334      /* sqrt(pi) */ ;

	/**
	 * Two divided by the square root of pi, 2/\sqrt\pi
	 */
	public static final double TWO_OVER_SQRTPI = 1.12837916709551257389615890312      /* 2/sqrt(pi) */ ;

	/**
	 * The reciprocal of pi, 1/\pi
	 */
	public static final double ONE_OVER_PI = 0.31830988618379067153776752675      /* 1/pi */ ;

	/**
	 * Twice the reciprocal of pi, 2/\pi
	 */
	public static final double TWO_OVER_PI = 0.63661977236758134307553505349      /* 2/pi */ ;

	/**
	 * The natural logarithm of ten, \ln(10)
	 */
	public static final double LN10 = 2.30258509299404568401799145468      /* ln(10) */ ;

	/**
	 * 	The natural logarithm of two, \ln(2)
	 */
	public static final double LN2 = 0.69314718055994530941723212146      /* ln(2) */ ;

	/**
	 * The natural logarithm of pi, \ln(\pi)
	 */
	public static final double LNPI = 1.14472988584940017414342735135      /* ln(pi) */ ;

	/**
	 * 	Euler’s constant, \gamma
	 */
	public static final double EULER = 0.57721566490153286060651209008      /* Euler constant */ ;

	/**
	 * This macro contains the IEEE representation of positive infinity, +\infty. It is computed from the expression +1.0/0.0.
	 */
	public static final double POS_INF = gslposinf() ;

	/**
	 * This macro contains the IEEE representation of negative infinity, -\infty. It is computed from the expression -1.0/0.0.
	 */
	public static final double NEG_INF = gslneginf() ;

	/**
	 * This macro contains the IEEE representation of the Not-a-Number symbol, NaN. It is computed from the ratio 0.0/0.0.
	 */
	public static final double NAN = gslnan() ;

	private static native double gslposinf() ;
	private static native double gslneginf() ;
	private static native double gslnan() ;

	/**
	 * This function returns true if x is not-a-number.
	 * @param x : real double-precision number
	 * @return {@code boolean} : true if x is NaN
	 */
	public static native boolean isNaN(double x) ;

	/**
	 * This function returns true if x is infinity and false otherwise.
	 * @param x : real double-precision number
	 * @return {@code boolean} : true if x is Inf
	 */
	public static native boolean isInf(double x) ;

	/**
	 * This function returns true if x is positive infinity.
	 * @param x : real double-precision number
	 * @return {@code boolean} : true if x is positive Inf
	 */
	public static native boolean isPositiveInf(double x) ;

	/**
	 * This function returns true if x is negative infinity.
	 * @param x : real double-precision number
	 * @return {@code boolean} : true if x is negative Inf
	 */
	public static native boolean isNegativeInf(double x) ;

	/**
	 * This function returns true if x is a real number, and false if it is infinite or not-a-number.
	 * @param x : real double-precision number
	 * @return {@code boolean} : true if x is not Inf
	 */
	public static native boolean isFinite(double x) ;

}
