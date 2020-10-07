package org.gsl4j.polynom;

import org.gsl4j.util.NativeLibraryLoader;

public class LegendrePolynom {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private LegendrePolynom() {

	}

	public static final int GSL_SF_LEGENDRE_NONE = 0 ;
	public static final int GSL_SF_LEGENDRE_SCHMIDT = 1 ;
	public static final int GSL_SF_LEGENDRE_SPHARM = 2 ;
	public static final int GSL_SF_LEGENDRE_FULL = 3 ;

	// Legendre Polynomials
	public static native double legendreP1(double x) ;
	public static native double legendreP2(double x) ;
	public static native double legendreP3(double x) ;
	public static native double legendrePl(int l, double x) ;
	public static native double[] legendrePlArray(int lmax, double x) ;
	public static native double[] legendrePlDerivArray(int lmax, double x) ;

	public static native double legendreQ0(double x) ;
	public static native double legendreQ1(double x) ;
	public static native double legendreQl(int l, double x) ;

	// Associated Legendre Polynomials and Spherical Harmonics
	public static native double[] legendreArray(int norm, int lmax, double x) ;
	public static native double[] legendreDerivArray(int norm, int lmax, double x) ;
	public static native double[] legendreDerivAltArray(int norm, int lmax, double x) ;
	public static native double[] legendreDeriv2Array(int norm, int lmax, double x, double csphase) ;
	public static native double[] legendreDeriv2AltArray(int norm, int lmax, double x) ;
	public static native int legendreArrayN(int lmax) ;
	public static native int legendreArrayIndex(int l, int m) ;
	public static native double legendrePlm(int l, int m, double x) ;
	public static native double legendreSphPlm(int l, int m, double x) ;
	public static native double[] legendrePlmArray(int lmax, int m, double x) ;
	public static native double[] legendreSphPlmArray(int lmax, int m, double x) ;

	// Conical Functions
	public static native double conicalPhalf(double lambda, double x) ;
	public static native double conicalPmhalf(double lambda, double x) ;
	public static native double conicalP0(double lambda, double x) ;
	public static native double conicalP1(double lambda, double x) ;
	public static native double conicalPsphReg(int l, double lambda, double x) ;
	public static native double conicalPcylReg(int m, double lambda, double x) ;

	// Radial Functions for Hyperbolic Space
	public static native double legendreH3d0(double lambda, double eta) ;
	public static native double legendreH3d1(double lambda, double eta) ;
	public static native double legendreH3d(int l, double lambda, double eta) ;
	public static native double[] legendreH3dArray(int lmax, double lambda, double eta) ;

}
