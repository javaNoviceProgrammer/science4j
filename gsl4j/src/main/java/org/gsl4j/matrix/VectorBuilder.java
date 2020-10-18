package org.gsl4j.matrix;

public interface VectorBuilder extends AlgebraVector {

	void reset() ;

	void set(int index, double z) ;
	void setAll(double z) ;



}
