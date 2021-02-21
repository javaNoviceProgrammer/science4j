package func4j.special;

import util4j.natives.NativeLibraryLoader;

//import func4j.natives.NativeLibraryLoader;

/**
 * Information on the properties of the Logarithm function
 * can be found in Abramowitz {@literal &} Stegun, Chapter 4. The functions described in
 * this section are declared in the header file gsl_sf_log.h.
 *
 * @author Meisam
 *
 */
public class Logarithm {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Logarithm() {

	}

	// Logarithm and Related Functions

	/**
	 * These routines compute the logarithm of x, \log(x), for x {@literal >} 0.
	 * @param x
	 * @return
	 */
	public static native double log(double x) ;

	/**
	 * These routines compute the logarithm of the magnitude of x, \log(|x|), for x \ne 0.
	 * @param x
	 * @return
	 */
	public static native double logAbs(double x) ;

	/**
	 * This routine computes the complex logarithm of z = z_r + i z_i. The results are returned as lnr, theta such that \exp(lnr + i \theta) = z_r + i z_i, where \theta lies in the range [-\pi,\pi].
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] complexLog(double re, double im) ;

	/**
	 * These routines compute \log(1 + x) for x {@literal >} -1 using an algorithm that is accurate for small x.
	 * @param x
	 * @return
	 */
	public static native double log1plusx(double x) ;

	/**
	 * These routines compute \log(1 + x) - x for x {@literal >} -1 using an algorithm that is accurate for small x.
	 * @param x
	 * @return
	 */
	public static native double log1plusxMx(double x) ;


}
