package org.simd4j.math;

import org.simd4j.util.NativeLibraryLoader;

public class ArrayI64Math {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	public static native void add(long[] vec1, long[] vec2, long[] result) ;
	public static native void sub(long[] vec1, long[] vec2, long[] result) ;
	public static native void mul(long[] vec1, long[] vec2, long[] result) ;
	public static native void div(long[] vec1, long[] vec2, long[] result) ;

}
