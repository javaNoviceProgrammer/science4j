package gsl4j;

import java.util.Arrays;

import org.gsl4j.polynom.PolynomialMath;

public class TestPolynomialMath {

	public static void test1() {
		// 2.1*x^2-x+3.0 = 0.0
		double[] roots = PolynomialMath.solveQuadraticComplexRoots(2.1, -1.0, 3.0) ;
		System.out.println(Arrays.toString(roots));
	}

	public static void test2() {
		// 2.1*x^2-x+3.0 = 0.0
		double[] roots = PolynomialMath.solvePolynomialComplexRoots(3.0, -1.0, 2.1) ;
		System.out.println(Arrays.toString(roots));
	}

	public static void main(String[] args) {
//		test1() ;
		test2() ;
	}

}
