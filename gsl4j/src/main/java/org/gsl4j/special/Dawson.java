package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Dawson {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Dawson() {

	}

	public static native double dawson(double x) ;


}
