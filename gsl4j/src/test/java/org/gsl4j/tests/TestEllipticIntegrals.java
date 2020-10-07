package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.special.EllipticIntegrals;

class TestEllipticIntegrals {

	private static void test1() {
		double x = 0.5 ;
		double y = EllipticIntegrals.ellintKcomp(x) ;
		System.out.printf("K(%.4f) = %.20f\n", x, y);
	}

	private static void test2() {
		double x = -0.25 ;
		double y = EllipticIntegrals.ellintKcomp(x) ;
		System.out.printf("K(%.4f) = %.20f\n", x, y);
	}

	private static void test3() {
		double u = 1.1, m = 0.3 ;
		double[] y = EllipticIntegrals.elljac(u, m) ;
		System.out.println(Arrays.toString(y));
	}

	private static void test4() {
		double u = 1.1, m = 0.3 ;
		double y = EllipticIntegrals.elljacSn(u, m) ;
		System.out.println("Sn(1.1, 0.3) = " + y);
	}

	private static void test5() {
		double u = 1.1, m = 0.3 ;
		double y = EllipticIntegrals.elljacCn(u, m) ;
		System.out.println("Cn(1.1, 0.3) = " + y);
	}

	private static void test6() {
		double u = 1.1, m = 0.3 ;
		double y = EllipticIntegrals.elljacDn(u, m) ;
		System.out.println("Dn(1.1, 0.3) = " + y);
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
		test5() ;
		test6() ;
	}

}
