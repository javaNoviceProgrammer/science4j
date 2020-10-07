package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Lambert {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Lambert() {

	}

	public static native double lambertW0(double x) ;
	public static native double lambertWm1(double x) ;

}
