package org.gsl4j.domain;

public interface Domain3d extends Domain2d {

	double var1Min() ;
	double var1Max() ;
	double var2Min(double var1) ;
	double var2Max(double var1) ;
	double var3Min(double var1, double var2) ;
	double var3Max(double var1, double var2) ;

}
