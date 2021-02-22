package func4j.special;

import func4j.natives.NativeLibraryLoader;

public class Debye {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
	}

	private Debye() {

	}

	public static native double debye1(double x) ;
	public static native double debye2(double x) ;
	public static native double debye3(double x) ;
	public static native double debye4(double x) ;
	public static native double debye5(double x) ;
	public static native double debye6(double x) ;

}
