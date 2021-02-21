package func4j.special;

import util4j.natives.NativeLibraryLoader;

//import func4j.natives.NativeLibraryLoader;

public class Wright {
	
	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Wright() {

	}
	
	public static native double wrightOmega(double x) ;
	public static native double[] wrightOmega(double re, double im) ;
	
}
