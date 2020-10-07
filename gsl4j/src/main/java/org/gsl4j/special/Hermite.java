package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Hermite {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Hermite() {

	}

	// Hermite Functions
	public static native double hermiteFunc(int n, double x) ;
	public static native double hermiteFuncFast(int n, double x) ;
	public static native double[] hermiteFuncArray(int nmax, double x) ;
	public static native double hermiteFuncSeries(int n, double x, double[] a) ;

	// Derivatives of Hermite Functions
	public static native double hermiteFuncDeriv(int m, int n, double x) ;

	// Zeros of Hermite Polynomials and Hermite Functions
	public static native double hermiteFuncZero(int n, int s) ;

}
