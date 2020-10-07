package org.gsl4j.tests;

import java.util.ArrayList;
import java.util.Arrays;

import org.gsl4j.special.Airy;

class TestAiry {

	private static void test1() {
		double[] x = {-20.0, -15, -2.5, 0.0, 3.2} ;
		ArrayList<Double> result = new ArrayList<>() ;
		for(double d : x)
			result.add(Airy.ai(d)) ;
		System.out.println(result);
	}

	private static void test2() {
		double[] x = {-20.0, -15, -2.5, 0.0, 3.2} ;
		ArrayList<Double> result = new ArrayList<>() ;
		for(double d : x)
			result.add(Airy.aiScaled(d)) ;
		System.out.println(result);
	}

	private static void test3() {
		double zero = Airy.aiZero(1) ;
		System.out.println(zero);
		System.out.println(Airy.ai(zero));
	}

	private static void test4() {
		double[] x = {-20.0, -15, -2.5, 0.0, 3.2} ;
		double[] y = Arrays.stream(x).map(Airy::bi).toArray() ;
		System.out.println(Arrays.toString(y));
	}


	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
	}

}
