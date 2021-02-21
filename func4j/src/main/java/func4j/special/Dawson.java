package func4j.special;

import util4j.natives.NativeLibraryLoader;

//import func4j.natives.NativeLibraryLoader;

public class Dawson {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Dawson() {

	}

	public static native double dawson(double x) ; // in Mathematica: DawsonF(x)


}
