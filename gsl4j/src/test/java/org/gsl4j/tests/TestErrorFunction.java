package org.gsl4j.tests;

import org.gsl4j.special.ErrorFunction;

class TestErrorFunction {

	private static void test1() {
		double x = 1.1 ;
		double y = ErrorFunction.erf(x) ;
		double z = ErrorFunction.erfc(x) ;
		System.out.printf("Erf(%.4f) = %.10f\n", x, y);
		System.out.printf("Erfc(%.4f) = %.10f\n", x, z);
	}

	public static void main(String[] args) {
		test1() ;
	}

}
