package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.special.Gegenbauer;

class TestGegenbauer {

	private static void test1() {
		double x = 1.1 ;
		double lambda = 2.3 ;
		double y = Gegenbauer.gegenpoly1(lambda, x) ;
		System.out.printf("Gegenpoly1(%.4f, %.4f) = %.15f\n", lambda, x, y);
	}

	private static void test2() {
		double x = 1.1 ;
		double lambda = 2.3 ;
		double y = Gegenbauer.gegenpolyN(5, lambda, x) ;
		System.out.printf("Gegenpoly5(%.4f, %.4f) = %.15f\n", lambda, x, y);
	}

	private static void test3() {
		double x = 1.1 ;
		double lambda = 2.3 ;
		double[] y = Gegenbauer.gegenpolyArray(5, lambda, x) ;
		System.out.printf("Gegenpoly1-5(%.4f, %.4f) = %s\n", lambda, x, Arrays.toString(y));
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
	}

}
