package org.gsl4j.complex;

/**
 * An interface for Real numbers. A defualt implementation of {@link #im()} method
 * is provided which returns 0.0 ;
 *
 * @author Meisam
 * @since 1.0
 *
 */
public interface RealNumber extends ComplexNumber {

	default double im() {
		return 0.0 ;
	}

	/**
	 * Requesting an appropriate builder (real) for the number.
	 * @return a builder : {@link RealBuilder}
	 */
	RealNumber getBuilder() ;

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	RealNumber add(double v) ;
	RealNumber addRev(double v) ;
	RealNumber add(RealNumber v) ;
	RealNumber addRev(RealNumber v) ;

	/*----- subtraction ------*/

	RealNumber subtract(double v) ;
	RealNumber subtractRev(double v) ;
	RealNumber subtract(RealNumber v) ;
	RealNumber subtractRev(RealNumber v) ;

	/*----- multiplication ------*/

	RealNumber multiply(double v) ;
	RealNumber multiplyRev(double v) ;
	RealNumber multiply(RealNumber v) ;
	RealNumber multiplyRev(RealNumber v) ;

	/*----- division ------*/

	RealNumber divide(double v) ;
	RealNumber divideRev(double v) ;
	RealNumber divide(RealNumber v) ;
	RealNumber divideRev(RealNumber v) ;

	/*----- negation ------*/

	RealNumber negate() ;

}
