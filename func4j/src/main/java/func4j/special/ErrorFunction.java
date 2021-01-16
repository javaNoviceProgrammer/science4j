package func4j.special;

import func4j.natives.NativeLibraryLoader;

public class ErrorFunction {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
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
