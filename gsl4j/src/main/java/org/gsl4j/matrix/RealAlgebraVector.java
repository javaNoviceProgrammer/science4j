package org.gsl4j.matrix;

import java.util.List;

import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.MathFunction;

public interface RealAlgebraVector extends AlgebraVector {

	RealNumber at(int index) ;
	RealNumber get(int index) ;

	RealNumber[] toArray() ;
	List<RealNumber> toList() ;

	void set(int index, RealNumber z) ;

	void setAll(RealNumber z) ;

	RealAlgebraVector conjugate() ;
	RealAlgebraVector getBuilder() ;

	RealAlgebraVector applyReal(MathFunction func) ;

}
