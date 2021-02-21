package func4j.special;

import util4j.natives.NativeLibraryLoader;

//import func4j.natives.NativeLibraryLoader;

public class Dilogarithm {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Dilogarithm() {

	}

	public static native double dilog(double x) ;
	public static native double[] dilog(double x, double y) ;
	public static native double[] dilogPolar(double r, double theta) ;

}
