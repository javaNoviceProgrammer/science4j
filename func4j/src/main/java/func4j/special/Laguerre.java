package func4j.special;

import util4j.natives.NativeLibraryLoader;

public class Laguerre {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Laguerre() {

	}

	// Laguerre Functions
	public static native double laguerre1(double a, double x) ;
	public static native double laguerre2(double a, double x) ;
	public static native double laguerre3(double a, double x) ;
	public static native double laguerreN(int n, double a, double x) ;

}
