package org.gsl4j.polynom;

import org.gsl4j.util.NativeLibraryLoader;

public class HermitePolynom {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private HermitePolynom() {

	}

	// Hermite Polynomials
	public static native double hermite(int n, double x) ;
	public static native double[] hermiteArray(int nmax, double x) ;
	public static native double hermiteSeries(int n, double x, double[] coeffs) ;
	public static native double hermiteProb(int n, double x) ;
	public static native double[] hermiteProbArray(int nmax, double x) ;
	public static native double hermiteProbSeries(int n, double x, double[] coeffs) ;

	// Derivatives of Hermite Polynomials
	public static native double hermiteDeriv(int m, int n, double x) ;
	public static native double[] hermiteArrayDeriv(int m, int nmax, double x) ;
	public static native double[] hermiteDerivArray(int mmax, int n, double x) ;
	public static native double hermiteProbDeriv(int m, int n, double x) ;
	public static native double[] hermiteProbArrayDeriv(int m, int nmax, double x) ;
	public static native double[] hermiteProbDerivArray(int mmax, int n, double x) ;

	// Zeros of Hermite Polynomials
	public static native double hermiteZero(int n, int s) ;
	public static native double hermiteProbZero(int n, int s) ;

}
