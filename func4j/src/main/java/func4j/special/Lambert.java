package func4j.special;

import util4j.natives.NativeLibraryLoader;

public class Lambert {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Lambert() {

	}

	public static native double lambertW0(double x) ;
	public static native double lambertWm1(double x) ;

}
