package org.gsl4j.tests;

import org.gsl4j.polynom.HermitePolynom;

class TestHermitePolynom {

	private static void test1() {
		System.out.println(HermitePolynom.hermite(10, 1.2));
	}

	private static void test2() {
		System.out.println(HermitePolynom.hermiteZero(2, 1));
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
	}

}
