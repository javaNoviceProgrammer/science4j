package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

/**
 * Hypergeometric functions are described in Abramowitz & Stegun, Chapters 13 and 15. These functions are declared in the header file {@code gsl_sf_hyperg.h}.
 *
 * @author Meisam
 *
 */
public class HyperGeometric {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private HyperGeometric() {

	}

	// Hypergeometric Functions

	/**
	 * These routines compute the hypergeometric function {}_0F_1(c,x)
	 * @param c
	 * @param x
	 * @return
	 */
	public static native double hyperg0F1(double c, double x) ;

	/**
	 * These routines compute the confluent hypergeometric function
	 * {}_1F_1(m,n,x) = M(m,n,x) for integer parameters m, n.
	 * @param m
	 * @param n
	 * @param x
	 * @return
	 */
	public static native double hyperg1F1int(int m, int n, double x) ;

	/**
	 * These routines compute the confluent hypergeometric function
	 * {}_1F_1(a,b,x) = M(a,b,x) for general parameters a, b.
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public static native double hyperg1F1(double a, double b, double x) ;

	/**
	 * These routines compute the confluent hypergeometric function U(m,n,x) for integer parameters m, n.
	 * @param m
	 * @param n
	 * @param x
	 * @return
	 */
	public static native double hypergUint(int m, int n, double x) ;

	/**
	 * This routine computes the confluent hypergeometric function U(m,n,x) for integer parameters m, n using the {@code gsl_sf_result_e10} type to return a result with extended range.
	 * @param m
	 * @param n
	 * @param x
	 * @return
	 */
	public static native double[] hypergUintE10(int m, int n, double x) ;

	/**
	 * These routines compute the confluent hypergeometric function U(a,b,x).
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public static native double hypergU(double a, double b, double x) ;

	/**
	 * This routine computes the confluent hypergeometric function U(a,b,x) using the {@code gsl_sf_result_e10} type to return a result with extended range.
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public static native double[] hypergUe10(double a, double b, double x) ;

	/**
	 * These routines compute the Gauss hypergeometric function
	 * {}_2F_1(a,b,c,x) = F(a,b,c,x) for |x| < 1. If the arguments (a,b,c,x) are too close to a singularity then the function can return the error code GSL_EMAXITER when the series approximation converges too slowly. This occurs in the region of x = 1, c - a - b = m for integer m.
	 * @param a
	 * @param b
	 * @param c
	 * @param x
	 * @return
	 */
	public static native double hyperg2F1(double a, double b, double c, double x) ;

	/**
	 * These routines compute the Gauss hypergeometric function
	 * {}_2F_1(a_R + i a_I, aR - i aI, c, x) with complex parameters for |x| < 1.
	 * @param aR
	 * @param aI
	 * @param c
	 * @param x
	 * @return
	 */
	public static native double hyperg2F1conj(double aR, double aI, double c, double x) ;

	/**
	 * These routines compute the renormalized Gauss hypergeometric function
	 * {}_2F_1(a,b,c,x) / \Gamma(c) for |x| < 1.
	 * @param a
	 * @param b
	 * @param c
	 * @param x
	 * @return
	 */
	public static native double hyperg2F1renorm(double a, double b, double c, double x) ;

	/**
	 * These routines compute the renormalized Gauss hypergeometric function
	 * {}_2F_1(a_R + i a_I, a_R - i a_I, c, x) / \Gamma(c) for |x| < 1.
	 * @param aR
	 * @param aI
	 * @param c
	 * @param x
	 * @return
	 */
	public static native double hyperg2F1conjRenorm(double aR, double aI, double c, double x) ;

	/**
	 * These routines compute the hypergeometric function {}_2F_0(a,b,x).
	 * <br>
	 * The series representation is a divergent hypergeometric series. However, for x < 0 we have
	 * {}_2F_0(a,b,x) = (-1/x)^a U(a,1+a-b,-1/x)
	 *
	 * @param a
	 * @param b
	 * @param x
	 * @return
	 */
	public static native double hyperg2F0(double a, double b, double x) ;

}
