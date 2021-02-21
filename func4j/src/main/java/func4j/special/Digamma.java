package func4j.special;

import util4j.natives.NativeLibraryLoader;

//import func4j.natives.NativeLibraryLoader;

public class Digamma {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Digamma() {

	}

	// Digamma 
	public static native double psiInt(int n) ;
	public static native double digamma(int n) ;
	public static native double psi(double x) ;
	public static native double digamma(double x) ; // in mathematica: PolyGamma
	public static native double psi1piy(double y) ;

	// Trigamma 
	public static native double psi1int(int n) ;
	public static native double psi1(double x) ;
	public static native double trigamma(double x) ;

	// Polygamma 
	public static native double psiN(int n, double x) ;
	public static native double polygamma(int n, double x) ;

}
