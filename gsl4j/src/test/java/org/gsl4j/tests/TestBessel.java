package org.gsl4j.tests;

import java.util.ArrayList;
import java.util.Arrays;
import org.gsl4j.special.Bessel;
import org.gsl4j.util.MathUtils;

class TestBessel {

	private static void test1() {
		double[] x = {-20.0, -15, -2.5, 0.0, 3.2} ;
		ArrayList<Double> result = new ArrayList<>() ;
		for(double d : x)
			result.add(Bessel.J0(d)) ;
		System.out.println(result);
	}

	private static void test2() {
		double[] x = {-20.0, -15, -2.5, 0.0, 3.2} ;
		ArrayList<Double> result = new ArrayList<>() ;
		for(double d : x)
			result.add(Bessel.J1(d)) ;
		System.out.println(result);
	}


	private static void test3() {
		double[] x = {-20.0, -15, -2.5, 0.0, 3.2} ;
		double[] y = Arrays.stream(x).map(t -> Bessel.Jn(3, t)).toArray() ;
		System.out.println(Arrays.toString(y));
	}

	private static void test4() {
		double x = 0.0 ;
		double[] y = Bessel.JnArray(0, 10, x) ;
		System.out.println(Arrays.toString(y));
	}

	private static void test5() {
		double x = 2.0 ;
		double[] y = Bessel.YnArray(0, 10, x) ;
		System.out.println(Arrays.toString(y));
	}

	private static void test6() {
		double[] x = MathUtils.linspace(0.001, 10.0, 20) ;
		double[] y = Bessel.Jv(1.0, x) ;
		System.out.println(Arrays.toString(y));
	}

	private static void test7() {
		int[] n = {1, 2, 3, 4, 5, 6, 7, 8 , 9, 10} ;
		double[] zeros = Arrays.stream(n).mapToDouble(m -> Bessel.zeroJ0(m)).toArray() ;
		System.out.println("J0(x)=0 --> x = " + Arrays.toString(zeros));
	}

	private static void test8() {
		double order = 3.1 ;
		int[] n = {1, 2, 3, 4, 5, 6, 7, 8 , 9, 10} ;
		double[] zeros = Arrays.stream(n).mapToDouble(m -> Bessel.zeroJv(order, m)).toArray() ;
		System.out.printf("J%.2f(x)=0 --> x = %s\n", order, Arrays.toString(zeros));
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
		test5() ;
		test6() ;
		test7() ;
		test8() ;
	}

}
