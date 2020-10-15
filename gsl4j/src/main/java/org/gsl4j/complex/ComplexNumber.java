package org.gsl4j.complex;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.gsl4j.AlgebraicEntity;

/**
 * This is the base interface for defining a complex number.
 * It provides two methods "re()" and "im()" and algebraic operations.
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
	 * A functional interface for applying any of {@link ComplexMath} methods.
	 * @param func
	 * @return
	 */
	ComplexNumber apply(BiFunction<Double, Double, double[]> func) ;

	ComplexNumber apply(Function<ComplexNumber, ComplexNumber> func) ;

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	ComplexNumber add(double v) ;
	ComplexNumber addRev(double v) ;

	ComplexNumber add(double re, double im) ;
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
