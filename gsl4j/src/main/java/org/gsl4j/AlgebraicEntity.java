package org.gsl4j;

/**
 * Interface for defining a math entity with algebraic operations
 *
 * @author Meisam
 * @since 1.0
 * @param <T> Class subject to algebraic operations
 */
public interface AlgebraicEntity<T> {

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
