package func4j.special;

import func4j.natives.NativeLibraryLoader;

public class Gegenbauer {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Gegenbauer() {

	}

	public static native double gegenpoly1(double lambda, double x) ;
	public static native double gegenpoly2(double lambda, double x) ;
	public static native double gegenpoly3(double lambda, double x) ;
	public static native double gegenpolyN(int n, double lambda, double x) ;
	public static native double[] gegenpolyArray(int nmax, double lambda, double x) ;

}
