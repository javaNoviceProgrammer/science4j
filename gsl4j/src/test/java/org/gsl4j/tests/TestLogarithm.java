package org.gsl4j.tests;

import org.gsl4j.special.Logarithm;

class TestLogarithm {

	private static void test1() {
		double x = 1.1 ;
		double y = Logarithm.log(x) ;
		System.out.printf("Log(%.4f) = %.16f \n", x, y);
	}

	private static void test2() {
		double x = 1.1 ;
		double y = Logarithm.log1plusx(x) ;
		System.out.printf("Log(1+%.4f) = %.16f \n", x, y);
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
	}

}
