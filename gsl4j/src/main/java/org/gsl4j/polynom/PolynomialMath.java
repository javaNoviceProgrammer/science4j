package org.gsl4j.polynom;

import org.gsl4j.util.NativeLibraryLoader;

public class PolynomialMath {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private PolynomialMath() {

	}

	// Polynomial Evaluation

	/**
	 * This function evaluates a polynomial with real coefficients for the real variable x.
	 * @param coeffs
	 * @param x
	 * @return
	 */
	public static native double eval(double[] coeffs, double x) ;

	/**
	 * This function evaluates a polynomial with real coefficients for the complex variable z.
	 * @param coeffs
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] eval(double[] coeffs, double re, double im) ;

	/**
	 * This function evaluates a polynomial with complex coefficients for the complex variable z.
	 * @param coeffsReal
	 * @param coeffsImag
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] eval(double[] coeffsReal, double[] coeffsImag, double re, double im) ;

	/**
	 * This function evaluates a polynomial and its derivatives storing the results in the array res of size lenres. The output array contains the values of d^k P(x)/d x^k for the specified value of x starting with k = 0.
	 * @param coeffs
	 * @param n
	 * @param x
	 * @return
	 */
	public static native double[] evalDerivs(double[] coeffs, int n, double x) ;

	// Divided Difference Representation of Polynomials


	// Quadratic Equations

	/**
	 * This function finds the real roots of the quadratic equation,
	 * a x^2 + b x + c = 0
	 * The number of real roots (either zero, one or two) is returned, and their locations are stored in x0 and x1. If no real roots are found then x0 and x1 are not modified. If one real root is found (i.e. if a=0) then it is stored in x0. When two real roots are found they are stored in x0 and x1 in ascending order. The case of coincident roots is not considered special. For example (x-1)^2=0 will have two roots, which happen to have exactly equal values.
	 * The number of roots found depends on the sign of the discriminant b^2 - 4 a c. This will be subject to rounding and cancellation errors when computed in double precision, and will also be subject to errors if the coefficients of the polynomial are inexact. These errors may cause a discrete change in the number of roots. However, for polynomials with small integer coefficients the discriminant can always be computed exactly.
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static native double[] solveQuadraticRealRoots(double a, double b, double c) ;

	/**
	 * This function finds the complex roots of the quadratic equation,
	 * a z^2 + b z + c = 0
	 * The number of complex roots is returned (either one or two) and the locations of the roots are stored in z0 and z1. The roots are returned in ascending order, sorted first by their real components and then by their imaginary components. If only one real root is found (i.e. if a=0) then it is stored in z0.
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static native double[] solveQuadraticComplexRoots(double a, double b, double c) ;

	// Cubic Equations

	/**
	 * This function finds the real roots of the cubic equation,
	 * x^3 + a x^2 + b x + c = 0
	 * with a leading coefficient of unity. The number of real roots (either one or three) is returned, and their locations are stored in x0, x1 and x2. If one real root is found then only x0 is modified. When three real roots are found they are stored in x0, x1 and x2 in ascending order. The case of coincident roots is not considered special. For example, the equation (x-1)^3=0 will have three roots with exactly equal values. As in the quadratic case, finite precision may cause equal or closely-spaced real roots to move off the real axis into the complex plane, leading to a discrete change in the number of real roots.
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static native double[] solveCubicRealRoots(double a, double b, double c) ;

	/**
	 * This function finds the complex roots of the cubic equation,
	 * z^3 + a z^2 + b z + c = 0
	 * The number of complex roots is returned (always three) and the locations of the roots are stored in z0, z1 and z2. The roots are returned in ascending order, sorted first by their real components and then by their imaginary components.
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static native double[] solveCubicComplexRoots(double a, double b, double c) ;

	// General Polynomial Equations

	/**
	 * This function computes the roots of the general polynomial
	 * P(x) = a_0 + a_1 x + a_2 x^2 + \cdots + a_{n-1} x^{n-1}
	 * using balanced-QR reduction of the companion matrix. The parameter n specifies the length of the coefficient array. The coefficient of the highest order term must be non-zero. The function requires a workspace w of the appropriate size. The n-1 roots are returned in the packed complex array z of length 2(n-1), alternating real and imaginary parts.
	 * @param coeffs
	 * @return
	 */
	public static native double[] solvePolynomialComplexRoots(double... coeffs) ;

	// polynomial-polynomial operations
	public static native double[] plus(double[] poly1, double[] poly2) ;
	public static native double[] minus(double[] poly1, double[] poly2) ;
	public static native double[] times(double[] poly1, double[] poly2) ;
	public static native double[] compose(double[] poly1, double[] poly2) ;

	// polynomial-number operation
	public static native double[] plus(double[] poly, double a) ;
	public static native double[] minus(double[] poly, double a) ;
	public static native double[] times(double[] poly, double a) ;
	public static native double[] divides(double[] poly, double a) ;

	// polynomial only operation
	public static native double[] reduce(double[] poly) ;
	public static native double[] ofRoots(double[] re, double[] im) ;
	public static native double[] pow(double[] poly, int s) ;
	public static native double[] diff(double[] poly) ;
	public static native double[] diff(double[] poly, int s) ;
	public static native double[] integrate(double[] poly) ;
	public static native double[] integrate(double[] poly, int s) ;
	public static native double integrate(double[] poly, double start, double end) ;

}
