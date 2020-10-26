package org.simd4j.math;

import org.simd4j.util.NativeLibraryLoader;

public class ArrayF32Math {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	public static native void add(float[] vec1, float[] vec2, float[] result) ;
	public static native void sub(float[] vec1, float[] vec2, float[] result) ;
	public static native void mul(float[] vec1, float[] vec2, float[] result) ;
	public static native void div(float[] vec1, float[] vec2, float[] result) ;

}
