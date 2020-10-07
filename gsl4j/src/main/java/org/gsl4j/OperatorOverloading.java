package org.gsl4j;

/**
 * Interface for enabling operator overloading of classes for JDK8
 *
 * @author Meisam
 *
 * @param <T> Class subject to operator overloading
 */
public interface OperatorOverloading<T> {

	// addition
	T add(double v) ; // this + v
	T addRev(double v) ; // v + this
	T add(T v) ; // this + v
	T addRev(T v) ; // v + this

	// subtraction
	T subtract(double v) ; // this - v
	T subtractRev(double v) ; // v - this
	T subtract(T v) ;
	T subtractRev(T v) ;

	// multiplication
	T multiply(double v) ;
	T multiplyRev(double v) ;
	T multiply(T v) ;
	T multiplyRev(T v) ;

	// division
	T divide(double v) ;
	T divideRev(double v) ;
	T divide(T v) ;
	T divideRev(T v) ;

	// negate
	T negate() ;

}
