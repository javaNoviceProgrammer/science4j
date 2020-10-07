package org.gsl4j.eigen;

import org.gsl4j.util.NativeLibraryLoader;

public class EigenSystem {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	public static final int EIGEN_SORT_VAL_ASC = 0 ;
	public static final int EIGEN_SORT_VAL_DESC = 1 ;
	public static final int EIGEN_SORT_ABS_ASC = 2 ;
	public static final int EIGEN_SORT_ABS_DESC = 3 ;

	private EigenSystem() {

	}

	// Real Symmetric Matrices
	public static native double[] eigenSymm(double[][] matrix) ;
	public static native double[] eigenSymmv(double[][] matrix, double[][] eigenVectors) ;

	// Complex Hermitian Matrices
	public static native double[] eigenHerm(double[][] matrixReal, double[][] matrixImag) ;
	public static native double[] eigenHermv(double[][] matrixReal, double[][] matrixImag, double[][] eigenVecReal, double[][] eigenVecImag) ;

	// Real Nonsymmetric Matrices
	public static native double[] eigenNonsymm(int computeT, int balance, double[][] matrix) ;
	public static native double[] eigenNonsymmv(int balance, double[][] matrix, double[][] eigenVecReal, double[][] eigenVecImag) ;

	// Real Generalized Symmetric-Definite Eigensystems
	public static native double[] eigenGenSymm(double[][] matrixA, double[][] matrixB) ;
	public static native double[] eigenGenSymmv(double[][] matrixA, double[][] matrixB, double[][] eigenVectors) ;

	// Complex Generalized Hermitian-Definite Eigensystems
	public static native double[] eigenGenHerm(double[][] matrixAreal, double[][] matrixAimag, double[][] matrixBreal, double[][] matrixBimag) ;
	public static native double[] eigenGenHermv(double[][] matrixAreal, double[][] matrixAimag, double[][] matrixBreal, double[][] matrixBimag, double[][] eigenVecReal, double[][] eigenVecImag) ;

	// Real Generalized Nonsymmetric Eigensystems
	public static native void eigenGen(int computeS, int computeT, int balance, double[][] matrixA, double[][] matrixB, double[] vecAlphaReal, double[] vecAlphaImag, double[] beta) ;
	public static native void eigenGenv(double[][] matrixA, double[][] matrixB, double[] vecAlphaReal, double[] vecAlphaImag, double[] beta, double[][] eigenVecReal, double[][] eigenVecImag) ;

	// Sorting Eigenvalues and Eigenvectors


}
