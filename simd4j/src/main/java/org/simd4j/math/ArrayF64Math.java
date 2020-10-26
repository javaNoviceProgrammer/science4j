package org.simd4j.math;

import org.simd4j.util.NativeLibraryLoader;

public class ArrayF64Math {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	public static native void add(double[] vec1, double[] vec2, double[] result) ;
	public static native void sub(double[] vec1, double[] vec2, double[] result) ;
	public static native void mul(double[] vec1, double[] vec2, double[] result) ;
	public static native void div(double[] vec1, double[] vec2, double[] result) ;

}
