package func4j.special;

import util4j.natives.NativeLibraryLoader;

//import func4j.natives.NativeLibraryLoader;

public class Coulomb {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Coulomb() {

	}

	// Normalized Hydrogenic Bound States
	public static native double hydrogenicR1(double z, double r) ;
	public static native double hydrogenicR(int n, int l, double z, double r) ;

	// Coulomb Wave Functions
	public static native double[] coulombWaveFunctions(double eta, double x, double l, int k) ;

	// Coulomb Wave IntegralFunction1D Normalization Constant
	public static native double coulombCL(double l, double eta) ;

}
