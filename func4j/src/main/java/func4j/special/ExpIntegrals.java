package func4j.special;

import util4j.natives.NativeLibraryLoader;

public class ExpIntegrals {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private ExpIntegrals() {

	}

	//Exponential Integral
	public static native double expIntE1(double x) ;
	public static native double expIntE2(double x) ;
	public static native double expIntEn(int n, double x) ;

	// Ei(x)
	public static native double expIntEi(double x) ;

	// Hyperbolic Integrals
	public static native double shi(double x) ;
	public static native double chi(double x) ;

	// Ei_3(x)
	public static native double expInt3(double x) ;

	// Trigonometric Integrals
	public static native double si(double x) ;
	public static native double ci(double x) ;

	// Arctangent Integral
	public static native double atanInt(double x) ;

}
