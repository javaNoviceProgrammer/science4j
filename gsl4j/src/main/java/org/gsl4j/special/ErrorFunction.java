package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class ErrorFunction {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private ErrorFunction() {

	}

	public static native double erf(double x) ;
	public static native double erfc(double x) ;
	public static native double logErfc(double x) ;
	public static native double erfZ(double x) ;
	public static native double erfQ(double x) ;
	public static native double hazard(double x) ;

}
