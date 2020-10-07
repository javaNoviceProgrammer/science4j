package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Debye {

	static {
		NativeLibraryLoader.loadLibraries();
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
