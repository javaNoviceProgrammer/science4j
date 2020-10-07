package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class EllipticIntegrals {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private EllipticIntegrals() {

	}

	// Legendre Form of Complete Elliptic Integrals
	public static native double ellintKcomp(double k) ;
	public static native double ellintEcomp(double k) ;
	public static native double ellintPcomp(double k, double n) ;

	// Legendre Form of Incomplete Elliptic Integrals
	public static native double ellintF(double phi, double k) ;
	public static native double ellintE(double phi, double k) ;
	public static native double ellintP(double phi, double k, double n) ;
	public static native double ellintD(double phi, double k) ;

	// Carlson Forms
	public static native double ellintRC(double x, double y) ;
	public static native double ellintRD(double x, double y, double z) ;
	public static native double ellintRF(double x, double y, double z) ;
	public static native double ellintRJ(double x, double y, double z, double p) ;

	// Elliptic Functions (Jacobi)
	public static native double elljacSn(double u, double m) ;
	public static native double elljacCn(double u, double m) ;
	public static native double elljacDn(double u, double m) ;
	public static native double[] elljac(double u, double m) ;

}
