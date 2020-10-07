package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Transport {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Transport() {

	}

	public static native double transport2(double x) ;
	public static native double transport3(double x) ;
	public static native double transport4(double x) ;
	public static native double transport5(double x) ;

}
