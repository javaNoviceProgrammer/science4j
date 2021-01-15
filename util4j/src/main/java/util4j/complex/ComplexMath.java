package util4j.complex;

import util4j.natives.NativeLibraryLoader;
import static java.lang.Math.PI;

/**
 * The functions described in this class provide support for complex numbers.
 * The algorithms take care to avoid unnecessary intermediate underflows and
 * overflows, allowing the functions to be evaluated over as much of the complex
 * plane as possible.
 *
 * For multiple-valued functions the branch cuts have been chosen
 * to follow the conventions of Abramowitz and Stegun. The functions return principal
 * values which are the same as those in GNU Calc, which in turn are the same as
 * those in “Common Lisp, The Language (Second Edition)” and the HP-28/48 series of
 * calculators.
 *
 * @author Meisam
 * @since 1.0
 *
 */
public final class ComplexMath {

	static {
		NativeLibraryLoader.loadLibraries("/libutil4j_c");
	}

	private ComplexMath() {

	}

	// Properties of complex numbers
	/**
	 * This function returns the argument of the complex number z, \arg(z),
	 * where -\pi {@literal <} \arg(z) {@literal <}= \pi.
	 * @param re : real part of complex number
	 * @param im : imaginary part of complex number
	 * @return double value : arg angle of complex number.
	 */
	public static native double arg(double re, double im) ;
	
	public static double argDeg(double re, double im) {
		return arg(re, im) * 180.0/PI ;
	}

	/**
	 * Same as {@link #arg(double, double)}, but takes a {@link ComplexNumber} ;
	 * @param z : a complex number
	 * @return double value : arg angle of complex number.
	 */
	public static double arg(ComplexNumber z) {
		return arg(z.re(), z.im()) ;
	}
	
	public static double argDeg(ComplexNumber z) {
		return argDeg(z.re(), z.im()) ;
	}

	/**
	 * This function returns the magnitude of the complex number z, |z|.
	 * @param re : real part of complex number
	 * @param im : imaginary part of complex number
	 * @return double value : magnitude of complex number.
	 */
	public static native double abs(double re, double im) ;

	public static double abs(ComplexNumber z) {
		return abs(z.re(), z.im()) ;
	}

	/**
	 * This function returns the squared magnitude of the complex number z, |z|^2.
	 * @param re : real part of complex number
	 * @param im : imaginary part of complex number
	 * @return double value : magnitude squared of complex number.
	 */
	public static native double abs2(double re, double im) ;

	public static double abs2(ComplexNumber z) {
		return abs2(z.re(), z.im()) ;
	}

	/**
	 * This function returns the natural logarithm of the magnitude of the
	 * complex number z, \log|z|. It allows an accurate evaluation of \log|z|
	 * when |z| is close to one. The direct evaluation of log(gsl_complex_abs(z))
	 * would lead to a loss of precision in this case.
	 * @param re : real part of complex number
	 * @param im : imaginary part of complex number
	 * @return double value : logabs() of complex number.
	 */
	public static native double logabs(double re, double im) ;

	public static double logabs(ComplexNumber z) {
		return logabs(z.re(), z.im()) ;
	}

	// Complex arithmetic operators

	/**
	 * This function returns the sum of the complex numbers z1 and z2: z1+z2
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param re2 : real part of z2
	 * @param im2 : imaginary part of z2
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] add(double re1, double im1, double re2, double im2) ;

	public static ComplexNumber add(ComplexNumber z1, ComplexNumber z2) {
		return z1.add(z2) ;
	}

	/**
	 * This function returns the difference of the complex numbers z1 and z2: z1-z2.
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param re2 : real part of z2
	 * @param im2 : imaginary part of z2
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] sub(double re1, double im1, double re2, double im2) ;

	public static ComplexNumber sub(ComplexNumber z1, ComplexNumber z2) {
		return z1.subtract(z2) ;
	}

	/**
	 * This function returns the product of the complex numbers z1 and z2: z1*z2.
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param re2 : real part of z2
	 * @param im2 : imaginary part of z2
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] mul(double re1, double im1, double re2, double im2) ;

	public static ComplexNumber mul(ComplexNumber z1, ComplexNumber z2) {
		return z1.multiply(z2) ;
	}

	/**
	 * This function returns the quotient of the complex numbers z1 and z2: z1/z2.
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param re2 : real part of z2
	 * @param im2 : imaginary part of z2
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] div(double re1, double im1, double re2, double im2) ;

	public static ComplexNumber div(ComplexNumber z1, ComplexNumber z2) {
		return z1.divide(z2) ;
	}

	/**
	 * This function returns the sum of the complex number z1 and the real
	 * number x: z1+x
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param x : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] addReal(double re1, double im1, double x) ;

	public static ComplexNumber addReal(ComplexNumber z1, double x) {
		return z1.add(x, 0.0) ;
	}

	/**
	 * This function returns the difference of the complex number z1 and
	 * the real number x: z1-x
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param x : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] subReal(double re1, double im1, double x) ;

	public static ComplexNumber subReal(ComplexNumber z1, double x) {
		return z1.subtract(x, 0.0) ;
	}

	/**
	 * This function returns the product of the complex number z1 and the real
	 * number x: z1*x
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param x : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] mulReal(double re1, double im1, double x) ;

	public static ComplexNumber mulReal(ComplexNumber z1, double x) {
		return z1.multiply(x, 0.0) ;
	}

	/**
	 * This function returns the quotient of the complex number z1 and the real
	 * number x: z1/x
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param x : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] divReal(double re1, double im1, double x) ;

	public static ComplexNumber divReal(ComplexNumber z1, double x) {
		return z1.divide(x, 0.0) ;
	}

	/**
	 * This function returns the sum of the complex number z1 and the
	 * imaginary number iy: z1+iy
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param y : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] addImag(double re1, double im1, double y) ;

	public static ComplexNumber addImag(ComplexNumber z1, double y) {
		return z1.add(0.0, y) ;
	}

	/**
	 * This function returns the difference of the complex number z1
	 * and the imaginary number iy: z1-iy.
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param y : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] subImag(double re1, double im1, double y) ;

	public static ComplexNumber subImag(ComplexNumber z1, double y) {
		return z1.subtract(0.0, y) ;
	}

	/**
	 * This function returns the product of the complex number z1 and the
	 * imaginary number iy: z1*(iy).
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param y : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] mulImag(double re1, double im1, double y) ;

	public static ComplexNumber mulImag(ComplexNumber z1, double y) {
		return z1.multiply(0.0, y) ;
	}

	/**
	 * This function returns the quotient of the complex number z1 and
	 * the imaginary number iy: z1/(iy).
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param y : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] divImag(double re1, double im1, double y) ;

	public static ComplexNumber divImag(ComplexNumber z1, double y) {
		return z1.divide(0.0, y) ;
	}

	/**
	 * This function returns the complex conjugate of the complex
	 * number z: z* = x - i y.
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] conjugate(double re, double im) ;

	public static ComplexNumber conjugate(ComplexNumber z) {
		return z.conjugate() ;
	}

	/**
	 * This function returns the inverse, or reciprocal, of the complex
	 * number z: 1/z = (x - i y)/(x^2 + y^2).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] inverse(double re, double im) ;

	public static ComplexNumber inverse(ComplexNumber z) {
		return z.apply((re,im) -> inverse(re, im)) ;
	}

	/**
	 * This function returns the negative of the complex
	 * number z: -z = (-x) + i(-y).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] negative(double re, double im) ;

	public static ComplexNumber negative(ComplexNumber z) {
		return z.negate() ;
	}

	// Elementary Complex Functions
	/**
	 * This function returns the square root of the complex number z: \sqrt z.
	 * The branch cut is the negative real axis. The result always lies in the
	 * right half of the complex plane.
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] sqrt(double re, double im) ;

	public static ComplexNumber sqrt(ComplexNumber z) {
		return z.apply((re,im) -> sqrt(re, im)) ;
	}

	/**
	 * This function returns the complex square root of the real number x,
	 * where x may be negative.
	 * @param x : a double-precision real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] sqrtReal(double x) ;


	/**
	 * The function returns the complex number z1 raised to the complex power z2, z1^z2.
	 * This is computed as \exp(\log(z1)*z2) using complex logarithms and complex
	 * exponentials.
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param re2 : real part of z2
	 * @param im2 : imaginary part of z2
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] pow(double re1, double im1, double re2, double im2) ;

	public static ComplexNumber pow(ComplexNumber z1, ComplexNumber z2) {
		return z1.apply((re,im) -> pow(re, im, z2.re(), z2.im())) ;
	}

	/**
	 * This function returns the complex number z raised to the real power x, z^x.
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @param x : a double-precision real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] powReal(double re, double im, double x) ;

	public static ComplexNumber powReal(ComplexNumber z, double x) {
		return z.apply((re,im) -> powReal(re, im, x)) ;
	}

	/**
	 * This function returns the complex exponential of the complex number z, \exp(z).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] exp(double re, double im) ;

	public static ComplexNumber exp(ComplexNumber z) {
		return z.apply((re,im) -> exp(re, im)) ;
	}

	/**
	 * This function returns the complex natural logarithm (base e) of the complex
	 * number z, \log(z). The branch cut is the negative real axis.
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] log(double re, double im) ;

	public static ComplexNumber log(ComplexNumber z) {
		return z.apply((re,im) -> log(re, im)) ;
	}

	/**
	 * This function returns the complex base-10 logarithm of the complex
	 * number z, \log_{10} (z).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] log10(double re, double im) ;

	public static ComplexNumber log10(ComplexNumber z) {
		return z.apply((re,im) -> log10(re, im)) ;
	}

	/**
	 * This function returns the complex base-b logarithm of the complex
	 * number z1, \log_b(z1). This quantity is computed as the ratio \log(z1)/\log(b).
	 * @param re1 : real part of z1
	 * @param im1 : imaginary part of z1
	 * @param re2 : real part of b
	 * @param im2 : imaginary part of b
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] logb(double re1, double im1, double re2, double im2) ;

	public static ComplexNumber logb(ComplexNumber z1, ComplexNumber b) {
		return z1.apply((re,im) -> logb(re, im, b.re(), b.im())) ;
	}

	// Complex Trigonometric Functions
	/**
	 * This function returns the complex sine of the complex
	 * number z, \sin(z) = (\exp(iz) - \exp(-iz))/(2i).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] sin(double re, double im) ;

	public static ComplexNumber sin(ComplexNumber z) {
		return z.apply((re,im) -> sin(re, im)) ;
	}

	/**
	 * This function returns the complex cosine of the complex number z, \cos(z) = (\exp(iz) + \exp(-iz))/2.
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] cos(double re, double im) ;

	public static ComplexNumber cos(ComplexNumber z) {
		return z.apply((re,im) -> cos(re, im)) ;
	}

	/**
	 * This function returns the complex tangent of the complex number z, \tan(z) = \sin(z)/\cos(z).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] tan(double re, double im) ;

	public static ComplexNumber tan(ComplexNumber z) {
		return z.apply((re,im) -> tan(re, im)) ;
	}

	/**
	 * This function returns the complex secant of the complex number z, \sec(z) = 1/\cos(z).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] sec(double re, double im) ;

	public static ComplexNumber sec(ComplexNumber z) {
		return z.apply((re,im) -> sec(re, im)) ;
	}

	/**
	 * This function returns the complex cosecant of the complex number z, \csc(z) = 1/\sin(z).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] csc(double re, double im) ;

	public static ComplexNumber csc(ComplexNumber z) {
		return z.apply((re,im) -> csc(re, im)) ;
	}

	/**
	 * This function returns the complex cotangent of the complex number z, \cot(z) = 1/\tan(z).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] cot(double re, double im) ;

	public static ComplexNumber cot(ComplexNumber z) {
		return z.apply((re,im) -> cot(re, im)) ;
	}

	// Inverse Complex Trigonometric Functions
	/**
	 * This function returns the complex arcsine of the complex number z, \arcsin(z).
	 * The branch cuts are on the real axis, less than -1 and greater than 1.
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] arcsin(double re, double im) ;

	public static ComplexNumber arcsin(ComplexNumber z) {
		return z.apply((re,im) -> arcsin(re, im)) ;
	}

	/**
	 * This function returns the complex arcsine of the real number z, \arcsin(z).
	 * For z between -1 and 1, the function returns a real value in the range [-\pi/2,\pi/2].
	 * For z less than -1 the result has a real part of -\pi/2 and a positive imaginary part. For z greater than 1 the result has a real part of \pi/2 and a negative imaginary part.
	 * @param x : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] arcsinReal(double x) ;

	public static ComplexNumber arcsin(double x) {
		return Complex.ofArray(arcsinReal(x)) ;
	}

	/**
	 * This function returns the complex arccosine of the complex number z, \arccos(z).
	 * The branch cuts are on the real axis, less than -1 and greater than 1.
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] arccos(double re, double im) ;

	public static ComplexNumber arccos(ComplexNumber z) {
		return z.apply((re,im) -> arccos(re, im)) ;
	}

	/**
	 * This function returns the complex arccosine of the real number z, \arccos(z).
	 * For z between -1 and 1, the function returns a real value in the range [0,\pi]. For z less than -1 the result has a real part of \pi and a negative imaginary part. For z greater than 1 the result is purely imaginary and positive.
	 * @param x : a real number
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] arccosReal(double x) ;

	public static ComplexNumber arccos(double x) {
		return Complex.ofArray(arccosReal(x)) ;
	}

	/**
	 * This function returns the complex arctangent of the complex number z, \arctan(z).
	 * The branch cuts are on the imaginary axis, below -i and above i.
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] arctan(double re, double im) ;

	public static ComplexNumber arctan(ComplexNumber z) {
		return z.apply((re,im) -> arctan(re, im)) ;
	}

	/**
	 * This function returns the complex arcsecant of the complex number z, \arcsec(z) = \arccos(1/z).
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@code double[]} : an array of (re, im) pair
	 */
	public static native double[] arcsec(double re, double im) ;

	public static ComplexNumber arcsec(ComplexNumber z) {
		return z.apply((re,im) -> arcsec(re, im)) ;
	}

	public static native double[] arcsecReal(double x) ;

	public static native double[] arccsc(double re, double im) ;

	public static ComplexNumber arccsc(ComplexNumber z) {
		return z.apply((re,im) -> arccsc(re, im)) ;
	}

	public static native double[] arccscReal(double x) ;

	public static native double[] arccot(double re, double im) ;

	public static ComplexNumber arccot(ComplexNumber z) {
		return z.apply((re,im) -> arccot(re, im)) ;
	}

	// Complex Hyperbolic Functions
	public static native double[] sinh(double re, double im) ;

	public static ComplexNumber sinh(ComplexNumber z) {
		return z.apply((re,im) -> sinh(re, im)) ;
	}

	public static native double[] cosh(double re, double im) ;

	public static ComplexNumber cosh(ComplexNumber z) {
		return z.apply((re,im) -> cosh(re, im)) ;
	}

	public static native double[] tanh(double re, double im) ;

	public static ComplexNumber tanh(ComplexNumber z) {
		return z.apply((re,im) -> tanh(re, im)) ;
	}

	public static native double[] sech(double re, double im) ;

	public static ComplexNumber sech(ComplexNumber z) {
		return z.apply((re,im) -> sech(re, im)) ;
	}

	public static native double[] csch(double re, double im) ;

	public static ComplexNumber csch(ComplexNumber z) {
		return z.apply((re,im) -> csch(re, im)) ;
	}

	public static native double[] coth(double re, double im) ;

	public static ComplexNumber coth(ComplexNumber z) {
		return z.apply((re,im) -> coth(re, im)) ;
	}

	// Inverse Complex Hyperbolic Functions
	public static native double[] arcsinh(double re, double im) ;

	public static ComplexNumber arcsinh(ComplexNumber z) {
		return z.apply((re,im) -> arcsinh(re, im)) ;
	}

	public static native double[] arccosh(double re, double im) ;

	public static ComplexNumber arccosh(ComplexNumber z) {
		return z.apply((re,im) -> arccosh(re, im)) ;
	}

	public static native double[] arccoshReal(double x) ;

	public static native double[] arctanh(double re, double im) ;

	public static ComplexNumber arctanh(ComplexNumber z) {
		return z.apply((re,im) -> arctanh(re, im)) ;
	}

	public static native double[] arctanhReal(double x) ;

	public static native double[] arcsech(double re, double im) ;

	public static ComplexNumber arcsech(ComplexNumber z) {
		return z.apply((re,im) -> arcsech(re, im)) ;
	}

	public static native double[] arccsch(double re, double im) ;

	public static ComplexNumber arccsch(ComplexNumber z) {
		return z.apply((re,im) -> arccsch(re, im)) ;
	}

	public static native double[] arccoth(double re, double im) ;

	public static ComplexNumber arccoth(ComplexNumber z) {
		return z.apply((re,im) -> arccoth(re, im)) ;
	}

}
