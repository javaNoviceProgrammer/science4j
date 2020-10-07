package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Laguerre {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Laguerre() {

	}

	// Laguerre Functions
	public static native double laguerre1(double a, double x) ;
	public static native double laguerre2(double a, double x) ;
	public static native double laguerre3(double a, double x) ;
	public static native double laguerreN(int n, double a, double x) ;

}
