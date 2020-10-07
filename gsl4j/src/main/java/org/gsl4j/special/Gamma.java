package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Gamma {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Gamma() {

	}

	// Gamma Functions
	public static native double gamma(double x) ;
	public static native double lngamma(double x) ;
	public static native double[] lngammaSign(double x) ;
	public static native double gammaStar(double x) ;
	public static native double gammaInv(double x) ;
	public static native double[] lngammaComplex(double re, double im) ;

	// Factorials
	public static native double factorial(int n) ;
	public static native double doubleFactorial(int n) ;
	public static native double lnfactorial(int n) ;
	public static native double lnDoubleFactorial(int n) ;
	public static native double choose(int n, int m) ;
	public static native double lnchoose(int n, int m) ;
	public static native double taylorCoeff(int n, double x) ;

	// Pochhammer Symbol
	public static native double poch(double a, double x) ;
	public static native double lnpoch(double a, double x) ;
	public static native double[] lnpochSign(double a, double x) ;
	public static native double pochrel(double a, double x) ;

	// Incomplete Gamma Functions
	public static native double gammaIncomplete(double a, double x) ;
	public static native double gammaIncompleteQ(double a, double x) ;
	public static native double gammaIncompleteP(double a, double x) ;

	// Beta Functions
	public static native double beta(double a, double b) ;
	public static native double lnbeta(double a, double b) ;


	// Incomplete Beta IntegralFunction1D
	public static native double betaIncomplete(double a, double b, double x) ;

}
