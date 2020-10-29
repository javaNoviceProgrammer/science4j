package org.gsl4j.domain;

public interface Domain2d extends Domain1d {

	double var1Min() ;
	double var1Max() ;
	double var2Min(double var1) ;
	double var2Max(double var1) ;

}
