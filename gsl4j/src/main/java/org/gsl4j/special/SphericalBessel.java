package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class SphericalBessel {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private SphericalBessel() {

	}

	// Regular Spherical Bessel Functions
	public static native double j0(double x) ;
	public static native double j1(double x) ;
	public static native double j2(double x) ;
	public static native double jl(int l, double x) ;
	public static native double[] jlArray(int lmax, double x) ;
	public static native double[] jlSteedArray(int lmax, double x) ;

	// Irregular Spherical Bessel Functions
	public static native double y0(double x) ;
	public static native double y1(double x) ;
	public static native double y2(double x) ;
	public static native double yl(double x) ;
	public static native double[] ylArray(int lmax, double x) ;

	// Regular Modified Spherical Bessel Functions
	public static native double i0Scaled(double x) ;
	public static native double i1Scaled(double x) ;
	public static native double i2Scaled(double x) ;
	public static native double ilScaled(int l, double x) ;
	public static native double[] ilScaledArray(int lmax, double x) ;

	// Irregular Modified Spherical Bessel Functions
	public static native double k0Scaled(double x) ;
	public static native double k1Scaled(double x) ;
	public static native double k2Scaled(double x) ;
	public static native double klScaled(int l, double x) ;
	public static native double[] klScaledArray(int lmax, double x) ;

}
