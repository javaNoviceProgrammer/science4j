package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

/**
 * The Riemann zeta function is defined in Abramowitz & Stegun, Section 23.2.
 * The functions described in this section are declared in the header file {@code gsl_sf_zeta.h}.
 * <br>
 * The Riemann zeta function is defined by the infinite sum:
 * <br>
 * \zeta(s) = \sum_{k=1}^\infty k^{-s}
 *
 * @author Meisam
 *
 */
public class Zeta {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Zeta() {

	}

	// Riemann Zeta IntegralFunction1D
	/**
	 * These routines compute the Riemann zeta function \zeta(n) for integer n, n \ne 1.
	 * @param n
	 * @return
	 */
	public static native double zetaInt(int n) ;

	/**
	 * These routines compute the Riemann zeta function \zeta(s) for arbitrary s, s \ne 1.
	 * @param s
	 * @return
	 */
	public static native double zeta(double s) ;

	// Riemann Zeta IntegralFunction1D Minus One
	/**
	 * These routines compute \zeta(n) - 1 for integer n, n \ne 1.
	 * @param n
	 * @return
	 */
	public static native double zetam1Int(int n) ;

	/**
	 * These routines compute \zeta(s) - 1 for arbitrary s, s \ne 1.
	 * @param x
	 * @return
	 */
	public static native double zetam1(double x) ;

	// Hurwitz Zeta IntegralFunction1D
	/**
	 * These routines compute the Hurwitz zeta function \zeta(s,q) for s > 1, q > 0.
	 * @param s
	 * @param q
	 * @return
	 */
	public static native double hzeta(double s, double q) ;

	// Eta IntegralFunction1D
	/**
	 * These routines compute the eta function \eta(n) for integer n.
	 * @param n
	 * @return
	 */
	public static native double etaInt(int n) ;

	/**
	 * These routines compute the eta function \eta(s) for arbitrary s.
	 * @param s
	 * @return
	 */
	public static native double eta(double s) ;

}
