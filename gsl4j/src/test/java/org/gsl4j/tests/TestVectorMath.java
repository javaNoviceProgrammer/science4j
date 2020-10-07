package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.matrix.VectorMath;
import org.gsl4j.util.MathUtils;

public class TestVectorMath {

	public static void test1() {
		double[] x = {1, 2, 3, 4} ;
		double elem = VectorMath.get(x, 3) ;
		System.out.println(elem);
	}

	public static void test2() {
		double[] x = {1, 2, 3, 4} ;
		VectorMath.set(x, 3, -3.0) ;
		System.out.println(Arrays.toString(x));
	}

	public static void test3() {
		double[] x = new double[10] ;
		VectorMath.setAll(x, -1.1);
		System.out.println(Arrays.toString(x));
	}

	public static void test4() {
		double[] x = new double[10] ;
		VectorMath.setZero(x);
		System.out.println(Arrays.toString(x));
	}

	public static void test5() {
		double[] x = new double[10] ;
		VectorMath.setBasis(x, 3);
		System.out.println(Arrays.toString(x));
	}

	public static void test6() {
		double[] x = MathUtils.linspace(0.0, 10.0, 0.5) ;
		double[] subX = VectorMath.subVector(x, 4, 6) ;
		System.out.println(Arrays.toString(x));
		System.out.println(Arrays.toString(subX));
	}

	public static void test7() {
		double[] x = MathUtils.linspace(0.0, 10.0, 0.2) ;
		double[] subX = VectorMath.subVectorWithStride(x, 4, 3, 6) ;
		System.out.println(Arrays.toString(x));
		System.out.println(Arrays.toString(subX));
	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
//		test4() ;
//		test5() ;
//		test6() ;
		test7() ;
	}

}
