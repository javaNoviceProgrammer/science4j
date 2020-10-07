package org.gsl4j.tests;

import org.gsl4j.special.Gamma;

class TetsGamma {

	private static void test1() {
		double x = 1.1 ;
		double y = Gamma.gamma(x) ;
		System.out.printf("Gamma(%.4f) = %.15f\n", x, y) ;
	}

	private static void test2() {
		int x = 5 ;
		double y = Gamma.factorial(x) ;
		System.out.printf("Factorial(%d) = %.15f\n", x, y) ;
	}

	private static void test3() {
		double a = 1.1 ;
		double b = 2.2 ;
		double y = Gamma.beta(a, b) ;
		System.out.printf("Beta(%.4f, %.4f) = %.15f\n", a, b, y) ;
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
	}

}
