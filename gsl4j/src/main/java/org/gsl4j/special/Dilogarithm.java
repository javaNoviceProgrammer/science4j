package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Dilogarithm {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Dilogarithm() {

	}

	public static native double dilog(double x) ;
	public static native double[] dilog(double x, double y) ;
	public static native double[] dilogPolar(double r, double theta) ;

}
