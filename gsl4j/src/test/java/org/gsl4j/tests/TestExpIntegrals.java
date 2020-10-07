package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.special.ExpIntegrals;

class TestExpIntegrals {

	private static void test1() {
		double[] x = {1.1, 2.2, -1.2} ;
		double[] y = Arrays.stream(x).map(ExpIntegrals::si).toArray() ;
		System.out.printf("SinIntegral(%.3f) = %.15f\n", x[0], y[0]);
		System.out.printf("SinIntegral(%.3f) = %.15f\n", x[1], y[1]);
		System.out.printf("SinIntegral(%.3f) = %.15f\n", x[2], y[2]);
	}

	public static void main(String[] args) {
		test1() ;
	}

}
