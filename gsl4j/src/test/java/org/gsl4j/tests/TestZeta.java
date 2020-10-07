package org.gsl4j.tests;

import org.gsl4j.special.Zeta;

class TestZeta {

	private static void test1() {
		double x = 1.1 ;
		double y = Zeta.zeta(x) ;
		System.out.printf("Zeta(%.4f) = %.15f", x, y) ;
	}

	private static void test2() {
		double x = 1.1 ;
		double y = Zeta.eta(x) ;
		System.out.printf("Eta(%.4f) = %.15f", x, y) ;
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
	}

}
