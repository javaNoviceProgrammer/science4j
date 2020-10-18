package org.gsl4j.matrix;

import java.util.List;
import org.gsl4j.complex.ComplexNumber;
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

}
