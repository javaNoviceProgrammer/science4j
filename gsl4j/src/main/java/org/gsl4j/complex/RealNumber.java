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

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	RealNumber add(RealNumber v) ;
	RealNumber addRev(RealNumber v) ;

	/*----- subtraction ------*/

	RealNumber subtract(RealNumber v) ;
	RealNumber subtractRev(RealNumber v) ;

	/*----- multiplication ------*/

	RealNumber multiply(RealNumber v) ;
	RealNumber multiplyRev(RealNumber v) ;

	/*----- division ------*/

	RealNumber divide(RealNumber v) ;
	RealNumber divideRev(RealNumber v) ;

}
