package func4j.special;

import util4j.natives.NativeLibraryLoader;

//import func4j.natives.NativeLibraryLoader;

public class ErrorFunction {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private ErrorFunction() {

	}

	// --> from GSL
	public static native double erf(double x) ;
	public static native double erfc(double x) ;
	public static native double logErfc(double x) ;
	public static native double erfZ(double x) ;
	public static native double erfQ(double x) ;
	public static native double hazard(double x) ;
	
	// --> from scipy.special
	public static native double[] erf(double re, double im) ;
	public static native double[] erfDeriv(double re, double im) ;
	public static native double[] erfAndDeriv(double re, double im) ;

}
