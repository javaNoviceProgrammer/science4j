package org.gsl4j.tests;

import org.gsl4j.special.Laguerre;

class TestLaguerre {

	private static void test1() {
		double a = 1.1 ;
		double x = 2.2 ;
		System.out.println(Laguerre.laguerre1(a, x));
	}

	private static void test2() {
		double a = 1.1 ;
		double x = 2.2 ;
		int n = 5 ;
		System.out.println(Laguerre.laguerreN(n, a, x));
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
	}

}
