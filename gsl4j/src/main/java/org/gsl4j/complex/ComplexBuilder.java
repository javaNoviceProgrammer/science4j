package org.gsl4j.complex;

import java.io.Serializable;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This class represents mutable complex numbers.
 * For math operations between complex numbers use {@code ComplexBuilder} class.
 *
 * @author Meisam
 * @since 1.0
 *
 */
public class ComplexBuilder implements Serializable, ComplexNumber {

	private static final long serialVersionUID = 1L;

	private double re ;
	private double im ;

	// auxiliary
	double x ;
	double y ;

	public static boolean debug = false ;
	public static long count = 0 ;

	// default constructor
	/**
	 * Default constructor: re=0.0, im=0.0
	 */
	public ComplexBuilder() {
		this.re = 0.0 ;
		this.im = 0.0 ;
		if(debug) {
			count++ ;
			System.out.println("ComplexBuilder count = " + count);
		}
	}

	// cartesian constructor
	/**
	 * Cartesian constructor : z = re+j*im
	 * @param re : real part of the initial complex value
	 * @param im : imaginary part of the initial complex value
	 */
	public ComplexBuilder(double re, double im) {
		this.re = re ;
		this.im = im ;
		if(debug) {
			count++ ;
			System.out.println("ComplexBuilder count = " + count);
		}
	}

	// base constructor
	/**
	 * Complex constructor: z=x+jy
	 * @param z : initial {@link Complex} value
	 */
	public ComplexBuilder(Complex z) {
		this.re = z.re() ;
		this.im = z.im() ;
		if(debug) {
			count++ ;
			System.out.println("ComplexBuilder count = " + count);
		}
	}

	// copy constructor
	/**
	 * Default copy constructor
	 * @param z : initial complex value
	 */
	public ComplexBuilder(ComplexBuilder z) {
		this.re = z.re ;
		this.im = z.im ;
		if(debug) {
			count++ ;
			System.out.println("ComplexBuilder count = " + count);
		}
	}

	/**
	 * Real part of z: Re(z)
	 * @return real part of a complex number.
	 */
	@Override
	public double re() {
		return re ;
	}

	/**
	 * Imaginary part of z: Im(z)
	 * @return imaginary part of a complex number.
	 */
	@Override
	public double im() {
		return im ;
	}

	@Override
	public ComplexNumber conjugate() {
		im *= -1.0 ;
		return this ;
	}

	@Override
	public ComplexBuilder getBuilder() {
		return this ;
	}

	@Override
	public ComplexNumber cachedBuilder() {
		return this ;
	}

	public Complex toComplex() {
		return Complex.ofRect(re, im) ;
	}

	public double[] toArray() {
		return new double[] {re, im} ;
	}

	public double[] toArrayPolar() {
		double r = ComplexMath.abs(re, im) ;
		double theta = ComplexMath.arg(re, im) ;
		return new double[] {r, theta} ;
	}

	@Override
	public String toString() {
		String formatter = "%." + Complex.DISPLAY_ACCURACY + "f" ;
		String stReal = String.format(formatter, re) ;
		String stImag = String.format(formatter, Math.abs(im)) ;
		if (im == 0)
			return stReal + "";
		if (re == 0 && im > 0)
			return "j" + stImag;
		if (re == 0 && im < 0)
			return "-j" + stImag;
		if (im < 0)
			return stReal + "-" + "j" + stImag;
		return stReal + "+" + "j" + stImag;
	}

	/**
	 * This method resets the state of the complex builder to
	 * its initial state (0.0,0.0).
	 */
	public void reset() {
		re = 0.0 ;
		im = 0.0 ;
	}

//	public void set(double re, double im) {
//		this.re = re ;
//		this.im = im ;
//	}
//
//	public void set(ComplexNumber z) {
//		this.re = z.re() ;
//		this.im = z.im() ;
//	}

	public ComplexBuilder set(double re, double im) {
		this.re = re ;
		this.im = im ;
		return this ;
	}

	public ComplexBuilder set(ComplexNumber z) {
		this.re = z.re() ;
		this.im = z.im() ;
		return this ;
	}

	public ComplexBuilder shiftLeft(ComplexNumber z) {
		this.re = z.re() ;
		this.im = z.im() ;
		return this ;
	}

	public ComplexBuilder shiftLeft(double z) {
		this.re = z ;
		return this ;
	}

	//*********** support for complex math functions *************

	@Override
	public ComplexBuilder apply(BiFunction<Double, Double, double[]> func) {
		double[] result = func.apply(this.re, this.im) ;
		this.re = result[0] ;
		this.im = result[1] ;
		return this ;
	}

	@Override
	public ComplexNumber apply(Function<ComplexNumber, ComplexNumber> func) {
		ComplexNumber z = func.apply(this) ;
		this.re = z.re() ;
		this.im = z.im() ;
		return this ;
	}

	//*********** support for algebraic operations *************

	public static ComplexBuilder valueOf(double v) {
		return new ComplexBuilder(v, 0.0) ;
	}

	public static ComplexBuilder valueOf(ComplexNumber v) {
		return new ComplexBuilder(v.re(), v.im()) ;
	}

	/*----- addition ------*/

	@Override
	public ComplexBuilder add(double v) {
		re += v ;
		return this ;
	}

	@Override
	public ComplexBuilder addRev(double v) {
		re += v ;
		return this ;
	}

	@Override
	public ComplexBuilder add(double re, double im) {
		this.re += re ;
		this.im += im ;
		return this ;
	}

	@Override
	public ComplexBuilder addRev(double re, double im) {
		this.re += re ;
		this.im += im ;
		return this ;
	}

	@Override
	public ComplexBuilder add(ComplexNumber v) {
		re += v.re() ;
		im += v.im() ;
		return this ;
	}

	@Override
	public ComplexBuilder addRev(ComplexNumber v) {
		re += v.re() ;
		im += v.im() ;
		return this ;
	}

	/*----- subtraction ------*/

	@Override
	public ComplexBuilder subtract(double v) {
		re = re - v ;
		return this ;
	}

	@Override
	public ComplexBuilder subtractRev(double v) {
		re = v - re ;
		return this ;
	}

	@Override
	public ComplexBuilder subtract(double re, double im) {
		this.re -= re ;
		this.im -= im ;
		return this ;
	}

	@Override
	public ComplexBuilder subtractRev(double re, double im) {
		this.re = re - this.re ;
		this.im = im - this.im ;
		return this ;
	}

	@Override
	public ComplexBuilder subtract(ComplexNumber v) {
		re = re - v.re() ;
		im = im - v.im() ;
		return this ;
	}

	@Override
	public ComplexBuilder subtractRev(ComplexNumber v) {
		re = v.re() - re ;
		im = v.im() - im ;
		return this ;
	}

	/*----- multiplication ------*/

	@Override
	public ComplexBuilder multiply(double v) {
		re *= v ;
		im *= v ;
		return this ;
	}

	@Override
	public ComplexBuilder multiplyRev(double v) {
		re *= v ;
		im *= v ;
		return this ;
	}

	@Override
	public ComplexBuilder multiply(double re, double im) {
		double real = this.re * re - this.im * im ;
		double imag = this.re * im + this.im * re ;
		this.re = real ;
		this.im = imag ;
		return this ;
	}

	@Override
	public ComplexBuilder multiplyRev(double re, double im) {
		this.re = this.re * re - this.im * im ;
		this.im = this.re * im + this.im * re ;
		return this ;
	}

	@Override
	public ComplexBuilder multiply(ComplexNumber v) {
		double real = re * v.re() - im * v.im() ;
		double imag = re * v.im() + im * v.re() ;
		this.re = real;
		this.im = imag ;
		return this ;
	}

	@Override
	public ComplexBuilder multiplyRev(ComplexNumber v) {
		double real = re * v.re() - im * v.im() ;
		double imag = re * v.im() + im * v.re() ;
		this.re = real ;
		this.im = imag ;
		return this ;
	}

	/*----- division ------*/

	@Override
	public ComplexBuilder divide(double v) {
		re = re/v ;
		im = im/v ;
		return this ;
	}

	@Override
	public ComplexBuilder divideRev(double v) {
		double mag = re*re+im*im ;
		re = v/mag * re ;
		im = v/mag * (-im) ;
		return this ;
	}

	@Override
	public ComplexBuilder divide(double re, double im) {
		double mag = re*re+im*im ;
		double real = (this.re*re+this.im*im)/mag ;
		double imag = (-this.re*im+this.im*re)/mag ;
		this.re = real ;
		this.im = imag ;
		return this ;
	}

	@Override
	public ComplexBuilder divideRev(double re, double im) {
		double mag = this.re*this.re + this.im*this.im ;
		double real = (this.re*re+this.im*im)/mag ;
		double imag = (this.re*im-this.im*re)/mag ;
		this.re = real ;
		this.im = imag ;
		return this ;
	}

	@Override
	public ComplexBuilder divide(ComplexNumber v) {
		double mag = v.re()*v.re()+v.im()*v.im() ;
		double real = (re*v.re()+im*v.im())/mag ;
		double imag = (-re*v.im()+im*v.re())/mag ;
		re = real ;
		im = imag ;
		return this ;
	}

	@Override
	public ComplexBuilder divideRev(ComplexNumber v) {
		double mag = re*re+im*im ;
		double real = (re*v.re()+im*v.im())/mag ;
		double imag = (re*v.im()-im*v.re())/mag ;
		re = real ;
		im = imag ;
		return this ;
	}

	/*----- negation ------*/

	public ComplexBuilder negate() {
		re *= -1.0 ;
		im *= -1.0 ;
		return this ;
	}


}
