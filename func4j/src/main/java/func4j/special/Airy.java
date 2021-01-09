package func4j.special;

import util4j.natives.NativeLibraryLoader;

public class Airy {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Airy() {

	}

	public static native double ai(double x) ;
	public static native double aiScaled(double x) ;
	public static native double aiDeriv(double x) ;
	public static native double aiDerivScaled(double x) ;
	public static native double aiZero(int n) ;
	public static native double aiDerivZero(int n) ;

	public static native double bi(double x) ;
	public static native double biScaled(double x) ;
	public static native double biDeriv(double x) ;
	public static native double biDerivScaled(double x) ;
	public static native double biZero(int n) ;
	public static native double biDerivZero(int n) ;

}
