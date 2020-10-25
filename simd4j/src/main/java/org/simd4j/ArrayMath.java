package org.simd4j;

import org.simd4j.util.NativeLibraryLoader;

public class ArrayMath {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	public static native void add(int[] vec1, int[] vec2, int[] result) ;
	public static native void sub(int[] vec1, int[] vec2, int[] result) ;
	public static native void mul(int[] vec1, int[] vec2, int[] result) ;
	public static native void div(int[] vec1, int[] vec2, int[] result) ;

	public static native void add(float[] vec1, float[] vec2, float[] result) ;
	public static native void sub(float[] vec1, float[] vec2, float[] result) ;
	public static native void mul(float[] vec1, float[] vec2, float[] result) ;
	public static native void div(float[] vec1, float[] vec2, float[] result) ;

	public static native void add(double[] vec1, double[] vec2, double[] result) ;
	public static native void sub(double[] vec1, double[] vec2, double[] result) ;
	public static native void mul(double[] vec1, double[] vec2, double[] result) ;
	public static native void div(double[] vec1, double[] vec2, double[] result) ;


}
