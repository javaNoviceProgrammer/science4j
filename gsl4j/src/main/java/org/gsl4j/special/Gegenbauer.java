package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Gegenbauer {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Gegenbauer() {

	}

	public static native double gegenpoly1(double lambda, double x) ;
	public static native double gegenpoly2(double lambda, double x) ;
	public static native double gegenpoly3(double lambda, double x) ;
	public static native double gegenpolyN(int n, double lambda, double x) ;
	public static native double[] gegenpolyArray(int nmax, double lambda, double x) ;

}
