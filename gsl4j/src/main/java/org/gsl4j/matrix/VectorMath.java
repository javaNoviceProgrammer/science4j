package org.gsl4j.matrix;

import org.gsl4j.util.NativeLibraryLoader;

/**
 * Vectors are defined by a {@code gsl_vector} structure which describes a slice of a block. Different vectors can be created which point to the same block. A vector slice is a set of equally-spaced elements of an area of memory.
 * <p> The {@code gsl_vector} structure contains five components, the size, the stride, a pointer to the memory where the elements are stored, data, a pointer to the block owned by the vector, block, if any, and an ownership flag, owner. The structure is very simple and looks like this:
 * <p>
 *	typedef struct <br>
 *	{<br>
 *	  size_t size;<br>
 *	  size_t stride;<br>
 *	  double * data;<br>
 *	  gsl_block * block;<br>
 *	  int owner;<br>
 *	} gsl_vector;<br>
 *
 * @author Meisam
 *
 */
public class VectorMath {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	private VectorMath() {

	}

	// Accessing vector elements
	public static native double get(double[] vec, int i) ;
	public static native void set(double[] vec, int i, double x) ;

	// Initializing vector elements
	public static native void setAll(double[] vec, double x) ;
	public static native void setZero(double[] vec) ;
	public static native void setBasis(double[] vec, int i) ;

	// Vector views
	public static native double[] subVector(double[] vec, int offset, int n) ;
	public static native double[] subVectorWithStride(double[] vec, int offset, int stride, int n) ;

	// Copying vectors
	public static native void memcpy(double[] dest, double[] src) ;
	public static native void swap(double[] v, double[] w) ;

	// Exchanging elements
	public static native double[] swapElements(double[] vec, int i, int j) ;
	public static native double[] reverse(double[] vec) ;

	// Vector operations
	public static native double[] add(double[] vec1, double[] vec2) ;
	public static native double[] sub(double[] vec1, double[] vec2) ;
	public static native double[] mul(double[] vec1, double[] vec2) ;
	public static native double[] div(double[] vec1, double[] vec2) ;
	public static native double[] scale(double[] vec1, double alpha) ;
	public static native double[] addConstant(double[] vec1, double x) ;
	public static native double[] axpby(double a, double[] x, double b, double[] y) ;

	// Finding maximum and minimum elements of vectors
	public static native double max(double[] vec) ;
	public static native double min(double[] vec) ;
	public static native double[] minmax(double[] vec) ;
	public static native int maxIndex(double[] vec) ;
	public static native int minIndex(double[] vec) ;
	public static native int[] minmaxIndex(double[] vec) ;

	// Vector properties
	public static native boolean isZero(double[] vec) ;
	public static native boolean isPositive(double[] vec) ;
	public static native boolean isNegative(double[] vec) ;
	public static native boolean isNonNegative(double[] vec) ;
	public static native boolean equal(double[] u, double[] v) ;


}
