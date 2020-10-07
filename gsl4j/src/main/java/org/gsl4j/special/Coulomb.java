package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Coulomb {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Coulomb() {

	}

	// Normalized Hydrogenic Bound States
	public static native double hydrogenicR1(double z, double r) ;
	public static native double hydrogenicR(int n, int l, double z, double r) ;

	// Coulomb Wave Functions
	public static native double[] coulombWaveFunctions(double eta, double x, double l, int k) ;

	// Coulomb Wave IntegralFunction1D Normalization Constant
	public static native double coulombCL(double l, double eta) ;

}
