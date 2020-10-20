package org.gsl4j.vector;


import java.util.List;
import java.util.function.Function;
import org.gsl4j.AlgebraicEntity;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;


public interface AlgebraVector extends AlgebraicEntity<AlgebraVector> {

	int size() ;

	ComplexNumber at(int index) ;
	ComplexNumber get(int index) ;

	double atReal(int index) ;
	double atImag(int index) ;

	double[] re() ;
	double[] im() ;

	List<? extends ComplexNumber> toList() ;

	VectorBuilder getBuilder() ;
	VectorBuilder cachedBuilder() ;

	AlgebraVector apply(Function<ComplexNumber,ComplexNumber> func) ;
	AlgebraVector applyReal(MathFunction func) ;
	AlgebraVector applyComplex(ComplexMathFunction func) ;

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	AlgebraVector add(double v) ;
	AlgebraVector addRev(double v) ;

	AlgebraVector add(RealNumber v) ;
	AlgebraVector addRev(RealNumber v) ;

	AlgebraVector add(ComplexNumber v) ;
	AlgebraVector addRev(ComplexNumber v) ;

	AlgebraVector add(AlgebraVector v) ;
	AlgebraVector addRev(AlgebraVector v) ;

	/*----- subtraction ------*/

	AlgebraVector subtract(double v) ;
	AlgebraVector subtractRev(double v) ;

	AlgebraVector subtract(RealNumber v) ;
	AlgebraVector subtractRev(RealNumber v) ;

	AlgebraVector subtract(ComplexNumber v) ;
	AlgebraVector subtractRev(ComplexNumber v) ;

	AlgebraVector subtract(AlgebraVector v) ;
	AlgebraVector subtractRev(AlgebraVector v) ;

	/*----- multiplication ------*/

	AlgebraVector multiply(double v) ;
	AlgebraVector multiplyRev(double v) ;

	AlgebraVector multiply(RealNumber v) ;
	AlgebraVector multiplyRev(RealNumber v) ;

	AlgebraVector multiply(ComplexNumber v) ;
	AlgebraVector multiplyRev(ComplexNumber v) ;

	AlgebraVector multiply(AlgebraVector v) ;
	AlgebraVector multiplyRev(AlgebraVector v) ;

	/*----- division ------*/

	AlgebraVector divide(double v) ;
	AlgebraVector divideRev(double v) ;

	AlgebraVector divide(RealNumber v) ;
	AlgebraVector divideRev(RealNumber v) ;

	AlgebraVector divide(ComplexNumber v) ;
	AlgebraVector divideRev(ComplexNumber v) ;

	AlgebraVector divide(AlgebraVector v) ;
	AlgebraVector divideRev(AlgebraVector v) ;

	/*----- negation ------*/

	AlgebraVector negate() ;


}
