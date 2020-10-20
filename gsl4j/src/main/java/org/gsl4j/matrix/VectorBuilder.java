package org.gsl4j.matrix;

import java.util.function.Function;

import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;


public interface VectorBuilder extends AlgebraVector {

	void reset() ;

	VectorBuilder set(int index, double z) ;
	VectorBuilder setAll(double z) ;

	VectorBuilder set(int index, RealNumber z) ;
	VectorBuilder setAll(RealNumber z) ;

	VectorBuilder set(int index, double re, double im) ;
	VectorBuilder setAll(double re, double im) ;

	VectorBuilder set(int index, ComplexNumber z) ;
	VectorBuilder setAll(ComplexNumber z) ;

	VectorBuilder apply(Function<ComplexNumber,ComplexNumber> func) ;
	VectorBuilder applyReal(MathFunction func) ;
	VectorBuilder applyComplex(ComplexMathFunction func) ;

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	VectorBuilder add(double v) ;
	VectorBuilder addRev(double v) ;

	VectorBuilder add(RealNumber v) ;
	VectorBuilder addRev(RealNumber v) ;

	VectorBuilder add(ComplexNumber v) ;
	VectorBuilder addRev(ComplexNumber v) ;

	VectorBuilder add(VectorBuilder v) ;
	VectorBuilder addRev(VectorBuilder v) ;

	/*----- subtraction ------*/

	VectorBuilder subtract(double v) ;
	VectorBuilder subtractRev(double v) ;

	VectorBuilder subtract(RealNumber v) ;
	VectorBuilder subtractRev(RealNumber v) ;

	VectorBuilder subtract(ComplexNumber v) ;
	VectorBuilder subtractRev(ComplexNumber v) ;

	VectorBuilder subtract(VectorBuilder v) ;
	VectorBuilder subtractRev(VectorBuilder v) ;

	/*----- multiplication ------*/

	VectorBuilder multiply(double v) ;
	VectorBuilder multiplyRev(double v) ;

	VectorBuilder multiply(RealNumber v) ;
	VectorBuilder multiplyRev(RealNumber v) ;

	VectorBuilder multiply(ComplexNumber v) ;
	VectorBuilder multiplyRev(ComplexNumber v) ;

	VectorBuilder multiply(VectorBuilder v) ;
	VectorBuilder multiplyRev(VectorBuilder v) ;

	/*----- division ------*/

	VectorBuilder divide(double v) ;
	VectorBuilder divideRev(double v) ;

	VectorBuilder divide(RealNumber v) ;
	VectorBuilder divideRev(RealNumber v) ;

	VectorBuilder divide(ComplexNumber v) ;
	VectorBuilder divideRev(ComplexNumber v) ;

	VectorBuilder divide(VectorBuilder v) ;
	VectorBuilder divideRev(VectorBuilder v) ;

	/*----- negation ------*/

	VectorBuilder negate() ;


}
