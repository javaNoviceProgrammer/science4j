package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class ExpFunction {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private ExpFunction() {

	}

	// Exponential IntegralFunction1D
	public static native double exp(double x) ;
	public static native double exp10(double x) ;
	public static native double expMult(double x, double y) ;
	public static native double multE10(double x, double y) ;

	// Relative Exponential Functions
	public static native double expm1(double x) ;
	public static native double exprel(double x) ;
	public static native double exprel2(double x) ;
	public static native double exprelN(int n, double x) ;

	// Exponentiation With Error Estimate
	public static native double[] expErr(double x, double dx) ;
	public static native double[] expErrE10(double x, double dx) ;
	public static native double[] expMultErr(double x, double dx, double y, double dy) ;
	public static native double[] expMultErrE10(double x, double dx, double y, double dy) ;

}
