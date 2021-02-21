package func4j.special;

import util4j.natives.NativeLibraryLoader;

//import func4j.natives.NativeLibraryLoader;

public class Synchrotron {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Synchrotron() {

	}

	public static native double synchrotron1(double x) ;
	public static native double synchrotron2(double x) ;

}
