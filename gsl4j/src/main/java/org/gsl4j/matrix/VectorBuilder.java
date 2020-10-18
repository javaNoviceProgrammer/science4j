package org.gsl4j.matrix;

import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.RealNumber;


public interface VectorBuilder extends AlgebraVector {

	void reset() ;

	void set(int index, double z) ;
	void setAll(double z) ;

	void set(int index, RealNumber z) ;
	void setAll(RealNumber z) ;

	void set(int index, ComplexNumber z) ;
	void setAll(ComplexNumber z) ;

	void set(AlgebraVector v) ;




}
