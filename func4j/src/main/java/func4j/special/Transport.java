package func4j.special;

import func4j.natives.NativeLibraryLoader;

public class Transport {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Transport() {

	}

	public static native double transport2(double x) ;
	public static native double transport3(double x) ;
	public static native double transport4(double x) ;
	public static native double transport5(double x) ;

}
