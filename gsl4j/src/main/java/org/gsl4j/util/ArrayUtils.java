package org.gsl4j.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.gsl4j.complex.Complex;
import org.gsl4j.function.MathFunction;


public class ArrayUtils {

	private ArrayUtils() {

	}

	public static Complex[] toComplexArray(double[] array) {
		int len = array.length ;
		if(len % 2 == 1) {
			throw new IllegalArgumentException("Array length must be an even integer") ;
		}
		int clen = len/2 ;
		Complex[] complexArray = new Complex[clen] ;
		for(int i=0; i<clen; i++) {
			complexArray[i] = Complex.ofRect(array[2*i], array[2*i+1]) ;
		}
		return complexArray ;
	}

	public static double[] eval(MathFunction func, double[] x) {
		double[] result = new double[x.length] ;
		if(x.length<10000) {
			// single-threaded (sequential)
			result = Arrays.stream(x).map(t -> func.value(t)).toArray() ;
		}
		else {
			// multi-threaded (parallel)
			result = Arrays.stream(x).parallel().map(t -> func.value(t)).toArray() ;
		}
		return result ;
	}

	public static Complex[][] toComplexArray(double[][] data){
		int M = data.length ;
		int N = data[0].length ;
		Complex[][] dataComplex = new Complex[M][N] ;
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; i++) {
				dataComplex[i][j] = Complex.ofRect(data[i][j], 0.0) ;
			}
		}
		return dataComplex ;
	}

	public static double[][] reshapeRow(double[] arg, int row, int column) {
		int nx = row ;
		int ny = column ;
		if(nx*ny != arg.length)
			throw new IllegalArgumentException("Dimensions don't agree!") ;
		double[][] var0 = new double[nx][ny] ;
		for(int i=0; i<nx; i++) {
			for(int j=0; j<ny; j++) {
				var0[i][j] = arg[i*ny+j] ;
			}
		}
		return var0 ;
	}

	public static int[][] reshapeRow(int[] arg, int row, int column) {
		int nx = row ;
		int ny = column ;
		if(nx*ny != arg.length)
			throw new IllegalArgumentException("Dimensions don't agree!") ;
		int[][] var0 = new int[nx][ny] ;
		for(int i=0; i<nx; i++) {
			for(int j=0; j<ny; j++) {
				var0[i][j] = arg[i*ny+j] ;
			}
		}
		return var0 ;
	}

	public static int[][] reshapeColumn(int[] arg, int row, int column) {
		int nx = row ;
		int ny = column ;
		if(nx*ny != arg.length)
			throw new IllegalArgumentException("Dimensions don't agree!") ;
		int[][] var0 = new int[nx][ny] ;
		for(int j=0; j<ny; j++) {
			for(int i=0; i<nx; i++) {
				var0[i][j] = arg[i+j*nx] ;
			}
		}
		return var0 ;
	}

	public static double[][] reshapeColumn(double[] arg, int row, int column) {
		int nx = row ;
		int ny = column ;
		if(nx*ny != arg.length)
			throw new IllegalArgumentException("Dimensions don't agree!") ;
		double[][] var0 = new double[nx][ny] ;
		for(int j=0; j<ny; j++) {
			for(int i=0; i<nx; i++) {
				var0[i][j] = arg[i+j*nx] ;
			}
		}
		return var0 ;
	}

	public static ArrayList<Complex> toArrayList(Complex[] st) {
		ArrayList<Complex> list = new ArrayList<>();
		for (Complex s : st) {
			list.add(s);
		}
		return list;
	}

	public static ArrayList<Double> toArrayList(double[] st) {
		ArrayList<Double> list = new ArrayList<>();
		for (double s : st) {
			list.add(s);
		}
		return list;
	}


	// creating an array of zero elements
	public static double[] setZero(int length) {
		double[] x = new double[length];
		for (int i = 0; i < length; i++) {
			x[i] = 0;
		}
		return x;
	}

	// creating an array of specific value
	public static double[] setValue(double value, int length) {
		int n = length;
		double[] x = new double[n];
		for (int i = 0; i < n; i++) {
			x[i] = value;
		}
		return x;
	}

	public static double[] replaceValues(double[] x, int indexArray[], double[] values) {
		int n = indexArray.length;
		for (int i = 0; i < n; i++) {
			x[indexArray[i]] = values[i];
		}
		return x;
	}

	// summing two arrays (z = y + x)
	public static double[] plus(double[] y, double[] x) {
		int n = x.length;
		double[] z = new double[n];
		for (int i = 0; i < n; i++) {
			z[i] = x[i] + y[i];
		}
		return z;
	}

	public static int[] plus(int[] y, int[] x) {
		int n = x.length;
		int[] z = new int[n];
		for (int i = 0; i < n; i++) {
			z[i] = x[i] + y[i];
		}
		return z;
	}

	public static String[] plus(String[] y, String[] x) {
		int n = x.length;
		String[] z = new String[n];
		for (int i = 0; i < n; i++) {
			z[i] = Double.toString(Double.parseDouble(x[i]) + Double.parseDouble(y[i]));
		}
		return z;
	}

	// adding a constant number to all the elements of an array (y = x +
	// alpha)
	public static double[] plus(double[] x, double alpha) {
		int n = x.length;
		double[] y = new double[n];
		for (int i = 0; i < n; i++) {
			y[i] = x[i] + alpha;
		}
		return y;
	}

	public static double[] plus(double alpha, double[] x) {
		int n = x.length;
		double[] y = new double[n];
		for (int i = 0; i < n; i++) {
			y[i] = x[i] + alpha;
		}
		return y;
	}

	// subtracting two arrays (z = y - x)
	public static double[] minus(double[] y, double[] x) {
		int n = y.length;
		double[] z = new double[n];
		for (int i = 0; i < n; i++) {
			z[i] = y[i] - x[i];
		}
		return z;
	}

	// multiplying an array by a number
	public static double[] times(double[] x, double alpha) {
		int n = x.length;
		double[] y = new double[n];
		for (int i = 0; i < n; i++) {
			y[i] = x[i] * alpha;
		}
		return y;
	}

	public static double[] times(double alpha, double[] x) {
		int n = x.length;
		double[] y = new double[n];
		for (int i = 0; i < n; i++) {
			y[i] = x[i] * alpha;
		}
		return y;
	}

	// multiplying element-wise of two arrays (z = x.*y)
	public static double[] times(double[] x, double[] y) {
		int n = x.length;
		double[] z = new double[n];
		for (int i = 0; i < n; i++) {
			z[i] = x[i] * y[i];
		}
		return z;
	}

	// element-wise division of two arrays (z = y./x)
	public static double[] divides(double[] y, double[] x) {
		int n = y.length;
		double[] z = new double[n];
		for (int i = 0; i < n; i++) {
			z[i] = y[i] / x[i];
		}
		return z;
	}

	public static int[] concat(int[] A, int[] B) {
		int nA = A.length;
		int nB = B.length;
		int[] AB = new int[nA + nB];
		for (int i = 0; i < nA; i++) {
			AB[i] = A[i];
		}
		for (int i = 0; i < nB; i++) {
			AB[i + nA] = B[i];
		}
		return AB;
	}

	public static double[] concat(double[] A, double[] B) {
		int nA = A.length;
		int nB = B.length;
		double[] AB = new double[nA + nB];
		for (int i = 0; i < nA; i++) {
			AB[i] = A[i];
		}
		for (int i = 0; i < nB; i++) {
			AB[i + nA] = B[i];
		}
		return AB;
	}

	public static String[] concat(String[] A, String[] B) {
		int nA = A.length;
		int nB = B.length;
		String[] AB = new String[nA + nB];
		for (int i = 0; i < nA; i++) {
			AB[i] = A[i];
		}
		for (int i = 0; i < nB; i++) {
			AB[i + nA] = B[i];
		}
		return AB;
	}

	public static int getIntervalIndex(double[] var0, double var1) {
		int k = 0 ;
		if(var1 < var0[0] || var1 > var0[var0.length-1])
			return -1 ;
		if(var1 == var0[0])
			return 0 ;
		if(var1 == var0[var0.length-1])
			return var0.length-1 ;
		for(int i=0; i<var0.length; i++)
			if(var1 == var0[i])
				return i ;
		while(var1 > var0[k])
			k++ ;
		return k-1 ;
	}

	// appending a new element to the end of array
	public static double[] append(double[] x, double x0) {
		return concat(x, new double[] { x0 });
	}

	public static int[] append(int[] x, int x0) {
		return concat(x, new int[] { x0 });
	}

	public static String[] append(String[] x, String x0) {
		return concat(x, new String[] { x0 });
	}

	public static double[] getReal(Complex[] u) {
		double[] x = new double[u.length];
		for (int i = 0; i < u.length; i++) {
			x[i] = u[i].re();
		}
		return x;
	}

	public static double[] getImag(Complex[] u) {
		double[] x = new double[u.length];
		for (int i = 0; i < u.length; i++) {
			x[i] = u[i].im();
		}
		return x;
	}

	// converting to string
	public static String toString(double[] x) {
		String s = "[ ";
		int n = x.length;
		for (int i = 0; i < n - 1; i++) {
			s += x[i] + ", ";
		}
		s = s + x[n - 1] + " ]";
		return s;
	}

	public static String toString(int[] x) {
		String s = "[ ";
		int n = x.length;
		for (int i = 0; i < n - 1; i++) {
			s += x[i] + ", ";
		}
		s = s + x[n - 1] + " ]";
		return s;
	}

	public static String toString(Complex[] x) {
		String s = "[ ";
		int n = x.length;
		for (int i = 0; i < n - 1; i++) {
			s += x[i] + ", ";
		}
		s = s + x[n - 1] + " ]";
		return s;
	}

	// printing the array on the screen
	public static void show(double[] x) {
		System.out.println(toString(x));
	}

	public static void show(int[] x) {
		System.out.println(toString(x));
	}

	public static void show(Complex[] x) {
		System.out.println(toString(x));
	}

	// common elements of two array lists
	public static <E> List<E> getCommonElements(List<E> a, List<E> b) {
		ArrayList<E> list1 = new ArrayList<>(a) ;
		ArrayList<E> list2 = new ArrayList<>(b) ;
		list1.retainAll(b) ;
		list2.retainAll(a) ;
		ArrayList<E> list3 = new ArrayList<>(list1) ;
		ArrayList<E> commons = new ArrayList<>() ;
		for(E e : list3)
			if(list1.remove(e) && list2.remove(e))
				commons.add(e) ;

		return commons ;
	}

}
