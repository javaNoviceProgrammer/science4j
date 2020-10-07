package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Mathieu {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Mathieu() {

	}

	// Mathieu IntegralFunction1D Characteristic Values
	public static native int mathieuA(int n, double q) ;
	public static native int mathieuB(int n, double q) ;
	public static native double[] mathieuAarray(int minOrder, int maxOrder, double q) ;
	public static native double[] mathieuBarray(int minorder, int maxorder, double q) ;

	// Angular Mathieu Functions
	public static native int mathieuCe(int n, double q, double x) ;
	public static native int mathieuSe(int n, double q, double x) ;
	public static native double[] mathieuCeArray(int nmin, int nmax, double q, double x) ;
	public static native double[] mathieuSeArray(int nmin, int nmax, double q, double x) ;

	// Radial Mathieu Functions
	public static native int mathieuMc(int j, int n, double q, double x) ;
	public static native int mathieuMs(int j, int n, double q, double x) ;
	public static native double[] mathieuMcArray(int j, int nmin, int nmax, double q, double x) ;
	public static native double[] mathieuMsArray(int j, int nmin, int nmax, double q, double x) ;

}
