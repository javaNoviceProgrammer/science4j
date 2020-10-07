package org.gsl4j.complex;

import org.gsl4j.util.NativeLibraryLoader;


/**
 * The functions described in this class provide support for complex numbers. The algorithms take care to avoid unnecessary intermediate underflows and overflows, allowing the functions to be evaluated over as much of the complex plane as possible.
 * For multiple-valued functions the branch cuts have been chosen
 * to follow the conventions of Abramowitz and Stegun. The functions return principal values which are the same as those in GNU Calc, which in turn are the same as those in
 * “Common Lisp, The Language (Second Edition)” and the HP-28/48 series of calculators.
 *
 * @author Meisam
 *
 */
public class ComplexMath {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private ComplexMath() {

	}

	// Properties of complex numbers
	/**
	 * This function returns the argument of the complex number z, \arg(z), where -\pi < \arg(z) <= \pi.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double arg(double re, double im) ;

	/**
	 * This function returns the magnitude of the complex number z, |z|.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double abs(double re, double im) ;

	/**
	 * This function returns the squared magnitude of the complex number z, |z|^2.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double abs2(double re, double im) ;

	/**
	 * This function returns the natural logarithm of the magnitude of the complex number z, \log|z|. It allows an accurate evaluation of \log|z| when |z| is close to one. The direct evaluation of log(gsl_complex_abs(z)) would lead to a loss of precision in this case.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double logabs(double re, double im) ;

	// Complex arithmetic operators

	/**
	 * This function returns the sum of the complex numbers a and b, z=a+b.
	 * @param re1
	 * @param im1
	 * @param re2
	 * @param im2
	 * @return
	 */
	public static native double[] add(double re1, double im1, double re2, double im2) ;

	/**
	 * This function returns the difference of the complex numbers a and b, z=a-b.
	 * @param re1
	 * @param im1
	 * @param re2
	 * @param im2
	 * @return
	 */
	public static native double[] sub(double re1, double im1, double re2, double im2) ;

	/**
	 * This function returns the product of the complex numbers a and b, z=a*b.
	 * @param re1
	 * @param im1
	 * @param re2
	 * @param im2
	 * @return
	 */
	public static native double[] mul(double re1, double im1, double re2, double im2) ;

	/**
	 * This function returns the quotient of the complex numbers a and b, z=a/b.
	 * @param re1
	 * @param im1
	 * @param re2
	 * @param im2
	 * @return
	 */
	public static native double[] div(double re1, double im1, double re2, double im2) ;

	/**
	 * This function returns the sum of the complex number a and the real number x, z=a+x.
	 * @param re1
	 * @param im1
	 * @param x
	 * @return
	 */
	public static native double[] addReal(double re1, double im1, double x) ;

	/**
	 * This function returns the difference of the complex number a and the real number x, z=a-x.
	 * @param re1
	 * @param im1
	 * @param x
	 * @return
	 */
	public static native double[] subReal(double re1, double im1, double x) ;

	/**
	 * This function returns the product of the complex number a and the real number x, z=ax.
	 * @param re1
	 * @param im1
	 * @param x
	 * @return
	 */
	public static native double[] mulReal(double re1, double im1, double x) ;

	/**
	 * This function returns the quotient of the complex number a and the real number x, z=a/x.
	 * @param re1
	 * @param im1
	 * @param x
	 * @return
	 */
	public static native double[] divReal(double re1, double im1, double x) ;

	/**
	 * This function returns the sum of the complex number a and the imaginary number iy, z=a+iy.
	 * @param re1
	 * @param im1
	 * @param y
	 * @return
	 */
	public static native double[] addImag(double re1, double im1, double y) ;

	/**
	 * This function returns the difference of the complex number a and the imaginary number iy, z=a-iy.
	 * @param re1
	 * @param im1
	 * @param y
	 * @return
	 */
	public static native double[] subImag(double re1, double im1, double y) ;

	/**
	 * This function returns the product of the complex number a and the imaginary number iy, z=a*(iy).
	 * @param re1
	 * @param im1
	 * @param y
	 * @return
	 */
	public static native double[] mulImag(double re1, double im1, double y) ;

	/**
	 * This function returns the quotient of the complex number a and the imaginary number iy, z=a/(iy).
	 * @param re1
	 * @param im1
	 * @param y
	 * @return
	 */
	public static native double[] divImag(double re1, double im1, double y) ;

	/**
	 * This function returns the complex conjugate of the complex number z, z* = x - i y.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] conjugate(double re, double im) ;

	/**
	 * This function returns the inverse, or reciprocal, of the complex number z, 1/z = (x - i y)/(x^2 + y^2).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] inverse(double re, double im) ;

	/**
	 * This function returns the negative of the complex number z, -z = (-x) + i(-y).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] negative(double re, double im) ;

	// Elementary Complex Functions
	/**
	 * This function returns the square root of the complex number z, \sqrt z. The branch cut is the negative real axis. The result always lies in the right half of the complex plane.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] sqrt(double re, double im) ;

	/**
	 * This function returns the complex square root of the real number x, where x may be negative.
	 * @param x
	 * @return
	 */
	public static native double[] sqrtReal(double x) ;

	/**
	 * The function returns the complex number z raised to the complex power a, z^a. This is computed as \exp(\log(z)*a) using complex logarithms and complex exponentials.
	 * @param re1
	 * @param im1
	 * @param re2
	 * @param im2
	 * @return
	 */
	public static native double[] pow(double re1, double im1, double re2, double im2) ;

	/**
	 * This function returns the complex number z raised to the real power x, z^x.
	 * @param re
	 * @param im
	 * @param x
	 * @return
	 */
	public static native double[] powReal(double re, double im, double x) ;

	/**
	 * This function returns the complex exponential of the complex number z, \exp(z).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] exp(double re, double im) ;

	/**
	 * This function returns the complex natural logarithm (base e) of the complex number z, \log(z). The branch cut is the negative real axis.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] log(double re, double im) ;

	/**
	 * This function returns the complex base-10 logarithm of the complex number z, \log_{10} (z).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] log10(double re, double im) ;

	/**
	 * This function returns the complex base-b logarithm of the complex number z, \log_b(z). This quantity is computed as the ratio \log(z)/\log(b).
	 * @param re1
	 * @param im1
	 * @param re2
	 * @param im2
	 * @return
	 */
	public static native double[] logb(double re1, double im1, double re2, double im2) ;

	// Complex Trigonometric Functions
	/**
	 * This function returns the complex sine of the complex number z, \sin(z) = (\exp(iz) - \exp(-iz))/(2i).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] sin(double re, double im) ;

	/**
	 * This function returns the complex cosine of the complex number z, \cos(z) = (\exp(iz) + \exp(-iz))/2.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] cos(double re, double im) ;

	/**
	 * This function returns the complex tangent of the complex number z, \tan(z) = \sin(z)/\cos(z).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] tan(double re, double im) ;

	/**
	 * This function returns the complex secant of the complex number z, \sec(z) = 1/\cos(z).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] sec(double re, double im) ;

	/**
	 * This function returns the complex cosecant of the complex number z, \csc(z) = 1/\sin(z).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] csc(double re, double im) ;

	/**
	 * This function returns the complex cotangent of the complex number z, \cot(z) = 1/\tan(z).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] cot(double re, double im) ;

	// Inverse Complex Trigonometric Functions
	/**
	 * This function returns the complex arcsine of the complex number z, \arcsin(z). The branch cuts are on the real axis, less than -1 and greater than 1.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] arcsin(double re, double im) ;

	/**
	 * This function returns the complex arcsine of the real number z, \arcsin(z). For z between -1 and 1, the function returns a real value in the range [-\pi/2,\pi/2]. For z less than -1 the result has a real part of -\pi/2 and a positive imaginary part. For z greater than 1 the result has a real part of \pi/2 and a negative imaginary part.
	 * @param x
	 * @return
	 */
	public static native double[] arcsinReal(double x) ;

	/**
	 * This function returns the complex arccosine of the complex number z, \arccos(z). The branch cuts are on the real axis, less than -1 and greater than 1.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] arccos(double re, double im) ;

	/**
	 * This function returns the complex arccosine of the real number z, \arccos(z). For z between -1 and 1, the function returns a real value in the range [0,\pi]. For z less than -1 the result has a real part of \pi and a negative imaginary part. For z greater than 1 the result is purely imaginary and positive.
	 * @param x
	 * @return
	 */
	public static native double[] arccosReal(double x) ;

	/**
	 * This function returns the complex arctangent of the complex number z, \arctan(z). The branch cuts are on the imaginary axis, below -i and above i.
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] arctan(double re, double im) ;

	/**
	 * This function returns the complex arcsecant of the complex number z, \arcsec(z) = \arccos(1/z).
	 * @param re
	 * @param im
	 * @return
	 */
	public static native double[] arcsec(double re, double im) ;
	public static native double[] arcsecReal(double x) ;
	public static native double[] arccsc(double re, double im) ;
	public static native double[] arccscReal(double x) ;
	public static native double[] arccot(double re, double im) ;

	// Complex Hyperbolic Functions
	public static native double[] sinh(double re, double im) ;
	public static native double[] cosh(double re, double im) ;
	public static native double[] tanh(double re, double im) ;
	public static native double[] sech(double re, double im) ;
	public static native double[] csch(double re, double im) ;
	public static native double[] coth(double re, double im) ;

	// Inverse Complex Hyperbolic Functions
	public static native double[] arcsinh(double re, double im) ;
	public static native double[] arccosh(double re, double im) ;
	public static native double[] arccoshReal(double x) ;
	public static native double[] arctanh(double re, double im) ;
	public static native double[] arctanhReal(double x) ;
	public static native double[] arcsech(double re, double im) ;
	public static native double[] arccsch(double re, double im) ;
	public static native double[] arccoth(double re, double im) ;



}
