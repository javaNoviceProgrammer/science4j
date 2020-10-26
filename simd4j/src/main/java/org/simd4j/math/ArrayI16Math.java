package org.simd4j.math;

import org.simd4j.util.NativeLibraryLoader;

public class ArrayI16Math {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	public static native void add(short[] vec1, short[] vec2, short[] result) ;
	public static native void sub(short[] vec1, short[] vec2, short[] result) ;
	public static native void mul(short[] vec1, short[] vec2, short[] result) ;
	public static native void div(short[] vec1, short[] vec2, short[] result) ;

}
