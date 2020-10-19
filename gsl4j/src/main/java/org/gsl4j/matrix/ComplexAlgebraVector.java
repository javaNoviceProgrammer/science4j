package org.gsl4j.matrix;

import java.util.List;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;


public interface ComplexAlgebraVector extends AlgebraVector {

	ComplexNumber at(int index) ;
	ComplexNumber get(int index) ;

	ComplexNumber[] toArray() ;
	List<ComplexNumber> toList() ;

	ComplexAlgebraVector conjugate() ;

	VectorBuilder getBuilder() ;
	VectorBuilder cachedBuilder() ;

	ComplexAlgebraVector apply(ComplexMathFunction func) ;

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	ComplexAlgebraVector add(double v) ;
	ComplexAlgebraVector addRev(double v) ;

	ComplexAlgebraVector add(RealNumber v) ;
	ComplexAlgebraVector addRev(RealNumber v) ;

	ComplexAlgebraVector add(ComplexNumber v) ;
	ComplexAlgebraVector addRev(ComplexNumber v) ;

	ComplexAlgebraVector add(ComplexAlgebraVector v) ;
	ComplexAlgebraVector addRev(ComplexAlgebraVector v) ;

	/*----- subtraction ------*/

	ComplexAlgebraVector subtract(double v) ;
	ComplexAlgebraVector subtractRev(double v) ;

	ComplexAlgebraVector subtract(RealNumber v) ;
	ComplexAlgebraVector subtractRev(RealNumber v) ;

	ComplexAlgebraVector subtract(ComplexNumber v) ;
	ComplexAlgebraVector subtractRev(ComplexNumber v) ;

	ComplexAlgebraVector subtract(ComplexAlgebraVector v) ;
	ComplexAlgebraVector subtractRev(ComplexAlgebraVector v) ;

	/*----- multiplication ------*/

	ComplexAlgebraVector multiply(double v) ;
	ComplexAlgebraVector multiplyRev(double v) ;

	ComplexAlgebraVector multiply(RealNumber v) ;
	ComplexAlgebraVector multiplyRev(RealNumber v) ;

	ComplexAlgebraVector multiply(ComplexNumber v) ;
	ComplexAlgebraVector multiplyRev(ComplexNumber v) ;

	ComplexAlgebraVector multiply(ComplexAlgebraVector v) ;
	ComplexAlgebraVector multiplyRev(ComplexAlgebraVector v) ;

	/*----- division ------*/

	ComplexAlgebraVector divide(double v) ;
	ComplexAlgebraVector divideRev(double v) ;

	ComplexAlgebraVector divide(RealNumber v) ;
	ComplexAlgebraVector divideRev(RealNumber v) ;

	ComplexAlgebraVector divide(ComplexNumber v) ;
	ComplexAlgebraVector divideRev(ComplexNumber v) ;

	ComplexAlgebraVector divide(ComplexAlgebraVector v) ;
	ComplexAlgebraVector divideRev(ComplexAlgebraVector v) ;

	/*----- negation ------*/

	ComplexAlgebraVector negate() ;

}
