package org.gsl4j.special;

import org.gsl4j.util.NativeLibraryLoader;

public class Synchrotron {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private Synchrotron() {

	}

	public static native double synchrotron1(double x) ;
	public static native double synchrotron2(double x) ;

}
