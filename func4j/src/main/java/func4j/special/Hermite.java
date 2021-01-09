package func4j.special;

import util4j.natives.NativeLibraryLoader;

public class Hermite {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Hermite() {

	}

	// Hermite Functions
	public static native double hermiteFunc(int n, double x) ;
	public static native double hermiteFuncFast(int n, double x) ;
	public static native double[] hermiteFuncArray(int nmax, double x) ;
	public static native double hermiteFuncSeries(int n, double x, double[] a) ;

	// Derivatives of Hermite Functions
	public static native double hermiteFuncDeriv(int m, int n, double x) ;

	// Zeros of Hermite Polynomials and Hermite Functions
	public static native double hermiteFuncZero(int n, int s) ;

}
