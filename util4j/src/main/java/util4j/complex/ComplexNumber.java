package util4j.complex;

import java.util.function.BiFunction;
import java.util.function.Function;

import util4j.math.AlgebraicEntity;

/**
 * This is the base interface for defining a complex number.
 * It provides two methods "re()" and "im()" and support for algebraic operations.
 *
 * @author Meisam
 * @since 1.0
 *
 */
public interface ComplexNumber extends AlgebraicEntity<ComplexNumber> {

	/**
	 * Real part of a complex number
	 * @return the real part
	 */
	double re() ;

	/**
	 * Imaginary part of a complex number
	 * @return the imaginary part
	 */
	double im() ;

	/**
	 * Conjugation of a complex number
	 * @return the conjugated complex number (z=x-jy)
	 */
	ComplexNumber conjugate() ;

	/**
	 * Requesting an appropriate builder (real or complex) for the number.
	 * @return a builder : {@link ComplexBuilder} or {@link RealBuilder}
	 */
	ComplexNumber getBuilder() ;

	ComplexNumber cachedBuilder() ;

	/**
	 * A functional interface for applying any of {@link ComplexMath} methods.
	 * @param func : a function to apply to a pair of (re,im) numbers.
	 * @return complex number : result of applying the function
	 */
	ComplexNumber apply(BiFunction<Double, Double, double[]> func) ;

	/**
	 * An arbitrary function interface for applying to a complex number.
	 * @param func : a function to apply to a {@link ComplexNumber}.
	 * @return {@link ComplexNumber} : result of function evaluation.
	 */
	ComplexNumber apply(Function<ComplexNumber, ComplexNumber> func) ;

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	/**
	 * Addition of a double number to a complex number.
	 * @param v : real double number
	 * @return {@link ComplexNumber}
	 */
	ComplexNumber add(double v) ;

	/**
	 * Reverse addition of a double number to a complex number.
	 * @param v : real double number
	 * @return {@link ComplexNumber}
	 */
	ComplexNumber addRev(double v) ;

	/**
	 * Addition of a complex number in (re,im) format to a complex number.
	 * @param re : real part
	 * @param im : imaginary part
	 * @return {@link ComplexNumber}
	 */
	ComplexNumber add(double re, double im) ;

	/**
	 * Reverse addition of a complex number in (re,im) format to a complex number.
	 * @param re : real part
	 * @param im : imaginary part
	 * @return {@link ComplexNumber}
	 */
	ComplexNumber addRev(double re, double im) ;

	ComplexNumber add(ComplexNumber v) ;
	ComplexNumber addRev(ComplexNumber v) ;

	/*----- subtraction ------*/

	ComplexNumber subtract(double v) ;
	ComplexNumber subtractRev(double v) ;

	ComplexNumber subtract(double re, double im) ;
	ComplexNumber subtractRev(double re, double im) ;

	ComplexNumber subtract(ComplexNumber v) ;
	ComplexNumber subtractRev(ComplexNumber v) ;

	/*----- multiplication ------*/

	ComplexNumber multiply(double v) ;
	ComplexNumber multiplyRev(double v) ;

	ComplexNumber multiply(double re, double im) ;
	ComplexNumber multiplyRev(double re, double im) ;

	ComplexNumber multiply(ComplexNumber v) ;
	ComplexNumber multiplyRev(ComplexNumber v) ;

	/*----- division ------*/

	ComplexNumber divide(double v) ;
	ComplexNumber divideRev(double v) ;

	ComplexNumber divide(double re, double im) ;
	ComplexNumber divideRev(double re, double im) ;

	ComplexNumber divide(ComplexNumber v) ;
	ComplexNumber divideRev(ComplexNumber v) ;

	/*----- negation ------*/

	ComplexNumber negate() ;

}
