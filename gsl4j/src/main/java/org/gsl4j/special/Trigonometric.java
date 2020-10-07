package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Trigonometric {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	// Trigonometric Functions

	private Trigonometric() {

	}

	// Circular Trigonometric Functions

	public static native double sin(double x) ;
	public static native double cos(double x) ;
	public static native double hypot(double x, double y) ;
	public static native double sinc(double x) ;
	public static native double sinc2(double x) ;

	// Trigonometric Functions for Complex Arguments

	public static native double[] sin(double re, double im) ;
	public static native double[] cos(double re, double im) ;
	public static native double[] logsin(double re, double im) ;

	// Hyperbolic Trigonometric Functions

	public static native double lnsinh(double x) ;
	public static native double lncosh(double x) ;

	// Conversion Functions

	public static native double[] polarToRect(double r, double phiRad) ;
	public static native double[] rectToPolar(double x, double y) ;

	// Restriction Functions

	public static native double angleRectrictSymm(double theta) ;
	public static native double angleRestrictPos(double theta) ;

	// Trigonometric Functions With Error Estimates

	public static native double[] sinErr(double x, double dx) ;
	public static native double[] cosErr(double x, double dx) ;


}
