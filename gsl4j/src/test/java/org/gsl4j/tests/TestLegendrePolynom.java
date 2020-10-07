package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.polynom.LegendrePolynom;

class TestLegendrePolynom {

	private static void test1() {
		double x = 0.9 ;
		double y = LegendrePolynom.legendrePl(5, x) ;
		System.out.printf("Pl(%d, %.4f) = %.15f\n", 5, x, y);
	}

	private static void test2() {
		double x = 0.9 ;
		double y = LegendrePolynom.legendreQl(5, x) ;
		System.out.printf("Ql(%d, %.4f) = %.15f\n", 5, x, y);
	}

	private static void test3() {
		double x = 0.9 ;
		double[] y = LegendrePolynom.legendreArray(LegendrePolynom.GSL_SF_LEGENDRE_NONE, 2, x) ;
		System.out.println(Arrays.toString(y));
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
	}

}
