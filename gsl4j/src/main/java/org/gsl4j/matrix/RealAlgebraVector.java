package org.gsl4j.matrix;

import java.util.List;

import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.MathFunction;

/**
 *
 * @author Meisam
 * @since 1.0
 *
 */
public interface RealAlgebraVector extends AlgebraVector {

	default double[] im() {
		return new double[size()] ;
	}

	RealNumber at(int index) ;
	RealNumber get(int index) ;

	double atIndex(int index) ;

	RealNumber[] toArray() ;
	double[] toDoubleArray() ;
	List<RealNumber> toList() ;

	RealAlgebraVector conjugate() ;

	RealVectorBuilder getBuilder() ;
	RealVectorBuilder cachedBuilder() ;

	RealAlgebraVector applyReal(MathFunction func) ;

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	RealAlgebraVector add(double v) ;
	RealAlgebraVector addRev(double v) ;

	RealAlgebraVector add(RealNumber v) ;
	RealAlgebraVector addRev(RealNumber v) ;

	RealAlgebraVector add(RealAlgebraVector v) ;
	RealAlgebraVector addRev(RealAlgebraVector v) ;

	/*----- subtraction ------*/

	RealAlgebraVector subtract(double v) ;
	RealAlgebraVector subtractRev(double v) ;

	RealAlgebraVector subtract(RealNumber v) ;
	RealAlgebraVector subtractRev(RealNumber v) ;

	RealAlgebraVector subtract(RealAlgebraVector v) ;
	RealAlgebraVector subtractRev(RealAlgebraVector v) ;

	/*----- multiplication ------*/

	RealAlgebraVector multiply(double v) ;
	RealAlgebraVector multiplyRev(double v) ;

	RealAlgebraVector multiply(RealNumber v) ;
	RealAlgebraVector multiplyRev(RealNumber v) ;

	RealAlgebraVector multiply(RealAlgebraVector v) ;
	RealAlgebraVector multiplyRev(RealAlgebraVector v) ;

	/*----- division ------*/

	RealAlgebraVector divide(double v) ;
	RealAlgebraVector divideRev(double v) ;

	RealAlgebraVector divide(RealNumber v) ;
	RealAlgebraVector divideRev(RealNumber v) ;

	RealAlgebraVector divide(RealAlgebraVector v) ;
	RealAlgebraVector divideRev(RealAlgebraVector v) ;

	/*----- negation ------*/

	RealAlgebraVector negate() ;

}
