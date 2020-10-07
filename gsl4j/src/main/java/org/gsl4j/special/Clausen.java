package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Clausen {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Clausen() {

	}

	public static native double clausen(double x) ;

}
