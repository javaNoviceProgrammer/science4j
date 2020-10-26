package org.simd4j.math;

import org.simd4j.util.NativeLibraryLoader;

public class ArrayI8Math {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	public static native void add(byte[] vec1, byte[] vec2, byte[] result) ;
	public static native void sub(byte[] vec1, byte[] vec2, byte[] result) ;
	public static native void mul(byte[] vec1, byte[] vec2, byte[] result) ;
	public static native void div(byte[] vec1, byte[] vec2, byte[] result) ;

}
