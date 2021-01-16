package func4j.special;

import func4j.natives.NativeLibraryLoader;

public class Clausen {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Clausen() {

	}

	public static native double clausen(double x) ;

}
