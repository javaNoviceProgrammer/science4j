package org.gsl4j.matrix;


import java.util.List;
import java.util.function.Function;
import org.gsl4j.AlgebraicEntity;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;


public interface AlgebraVector extends AlgebraicEntity<AlgebraVector> {

	int size() ;

	ComplexNumber at(int index) ;
	ComplexNumber get(int index) ;

	double[] re() ;
	double[] im() ;

	List<? extends ComplexNumber> toList() ;

	AlgebraVector apply(Function<ComplexNumber,ComplexNumber> func) ;
	AlgebraVector applyReal(MathFunction func) ;
	AlgebraVector applyComplex(ComplexMathFunction func) ;

}
