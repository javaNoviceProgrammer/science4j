package org.simd4j.math;

import org.simd4j.util.NativeLibraryLoader;

public class ArrayI32Math {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	public static native void add(int[] vec1, int[] vec2, int[] result) ;
	public static native void sub(int[] vec1, int[] vec2, int[] result) ;
	public static native void mul(int[] vec1, int[] vec2, int[] result) ;
	public static native void div(int[] vec1, int[] vec2, int[] result) ;

}
