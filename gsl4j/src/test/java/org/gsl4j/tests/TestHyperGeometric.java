package org.gsl4j.tests;

import org.gsl4j.special.HyperGeometric;

class TestHyperGeometric {

	public static void test1() {
		double x = 1.1 ;
		double y = HyperGeometric.hyperg0F1(2.2, x) ;
		System.out.println(y);
	}

	public static void test2() {
		double y = HyperGeometric.hyperg1F1(1.1, 2.2, 3.3) ;
		System.out.println(y);
	}

	public static void test3() {
		double y = HyperGeometric.hyperg2F1(0.1, 0.2, 0.3, 0.4) ;
		System.out.println(y);
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
	}

}
