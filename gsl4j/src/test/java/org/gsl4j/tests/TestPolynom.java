package org.gsl4j.tests;

import java.util.ArrayList;
import java.util.Arrays;

import org.gsl4j.complex.Complex;
import org.gsl4j.polynom.Polynomial;
import org.gsl4j.polynom.PolynomialMath;
import static org.gsl4j.complex.Complex.j ;

class TestPolynom {

	public static void test1() {
		double[] coeffs = {1.0, -1.0} ;
		double x = -10.0 ;
		double val = PolynomialMath.eval(coeffs, x) ;
		System.out.printf("P(%.4f) = %.15f \n", x, val);
	}

	public static void test2() {
		double[] coeffs = {1.0, -1.0} ;
		double re = 1.1 ;
		double im = 2.2 ;
		double[] val = PolynomialMath.eval(coeffs, re, im) ;
		System.out.printf("P(%.4f+j%.4f) = %.15f+j%.15f \n", re, im, val[0], val[1]);
	}

	public static void test3() {
		double[] re = {1.1, 2.2} ;
		double[] im = {3.3, 4.4} ;
		double zRe = 1.0 ;
		double zIm = -2.0 ;
		double[] val = PolynomialMath.eval(re, im, zRe, zIm) ;
		System.out.printf("P(%.4f+j%.4f) = %.15f+j%.15f \n", zRe, zIm, val[0], val[1]);
	}

	public static void test4() {
		double[] coeffs = {1.0, -1.0, 2.0, 3.1} ;
		double x = -10.0 ;
		double[] val = PolynomialMath.evalDerivs(coeffs, 10, x) ;
		System.out.println(Arrays.toString(val));
	}

	public static void test5() {
		// x^2 - 2*x + 1
		double[] roots = PolynomialMath.solveQuadraticRealRoots(1.0, -2.0, 1.0) ;
		System.out.println(Arrays.toString(roots));
	}

	public static void test6() {
		// x^2 - 2*x - 3
		double[] roots = PolynomialMath.solveQuadraticRealRoots(1.0, -2.0, -3.0) ;
		System.out.println(Arrays.toString(roots));
	}

	public static void test7() {
		// x^2 - 2*x + 3
		double[] roots = PolynomialMath.solveQuadraticComplexRoots(1.0, -2.0, 3.0);
		System.out.println(Arrays.toString(roots));
	}

	public static void test8() {
		// x^3 - 2*x^2 - 2*x + 3
		double[] roots = PolynomialMath.solveCubicRealRoots(-2.0, -2.0, 3.0) ;
		System.out.println(Arrays.toString(roots));
	}

	public static void test9() {
		// x^3 + 2*x^2 - 2*x + 4
		double[] roots = PolynomialMath.solveCubicRealRoots(2.0, -2.0, 4.0) ;
		System.out.println(Arrays.toString(roots));
	}

	public static void test10() {
		// x^3 + 2*x^2 - 2*x + 4
		double[] roots = PolynomialMath.solveCubicComplexRoots(2.0, -2.0, 4.0) ;
		System.out.println(Arrays.toString(roots));
	}

	public static void test11() {
		// x^4 + x^3 + 2*x^2 - 2*x + 4
		double[] roots = PolynomialMath.solvePolynomialComplexRoots(new double[]{4.0, -2.0, 2.0, 1.0, 1.0}) ;
		System.out.println(Arrays.toString(roots));
	}

	public static void test12() {
		// -x^5 + x^4 + x^3 + 2*x^2 - 2*x + 4
		double[] roots = PolynomialMath.solvePolynomialComplexRoots(4.0, -2.0, 2.0, 1.0, 1.0, -1.0) ;
		System.out.println(Arrays.toString(roots));
	}

	public static void test13() {
		// p1(x) = x^3 - x
		double[] p1 = {0.0, -1.0, 0.0, 1.0, 0.0} ;
		// p2(x) = -x^5 + 0.5x
		double[] p2 = {0.0, 0.5, 0.0, 0.0, 0.0, -5.0} ;
		double[] sum = PolynomialMath.plus(p1, p2) ;
		Polynomial psum = Polynomial.ofCoeffs(sum) ;
		System.out.println(Polynomial.ofCoeffs(p1));
		System.out.println(Polynomial.ofCoeffs(p2));
		System.out.println(psum);
		System.out.println(Arrays.toString(sum));
	}

	public static void test14() {
		// p1(x) = x^3 - x
		double[] p1 = {0.0, -1.0, 0.0, 1.0, 0.0} ;
		// p2(x) = -x^5 + 0.5x
		double[] p2 = {0.0, 0.5, 0.0, 0.0, 0.0, -5.0} ;
		double[] sub = PolynomialMath.minus(p1, p2) ;
		Polynomial psub = Polynomial.ofCoeffs(sub) ;
		System.out.println(Polynomial.ofCoeffs(p1));
		System.out.println(Polynomial.ofCoeffs(p2));
		System.out.println(psub);
		System.out.println(Arrays.toString(sub));
	}

	public static void test15() {
		// p1(x) = x^3 - x
		double[] p1 = {0.0, -1.0, 0.0, 1.0, 0.0} ;
		// p2(x) = -x^5 + 0.5x
		double[] p2 = {0.0, 0.5, 0.0, 0.0, 0.0, -5.0} ;
		double[] mul = PolynomialMath.times(p1, p2) ;
		Polynomial pmul = Polynomial.ofCoeffs(mul) ;
		System.out.println(Polynomial.ofCoeffs(p1));
		System.out.println(Polynomial.ofCoeffs(p2));
		System.out.println(pmul);
		System.out.println(Arrays.toString(mul));
	}

	public static void test16() {
		// p1(x) = x^3 - x
		double[] p1 = {0.0, -1.0, 0.0, 1.0, 0.0} ;
		double[] plus = PolynomialMath.divides(p1, -1.0) ;
		Polynomial psum = Polynomial.ofCoeffs(plus) ;
		System.out.println(Polynomial.ofCoeffs(p1));
		System.out.println(psum);
		System.out.println(Arrays.toString(plus));
		System.out.println(psum.degree());
	}

	public static void test17() {
		// p1(x) = x^3 - x
		double[] p1 = {0.0, -1.0, 0.0, 1.0, 0.0, 10.0} ;
		double[] diff = PolynomialMath.diff(p1) ;
		Polynomial pdiff = Polynomial.ofCoeffs(diff) ;
		System.out.println(Polynomial.ofCoeffs(p1));
		System.out.println(pdiff);
		System.out.println(Arrays.toString(diff));
		System.out.println(pdiff.degree());
	}

	public static void test18() {
		// p1(x) = x^3 - x
		double[] p1 = {0.0, -1.0, 0.0, 1.0, 0.0, 0.0} ;
		double[] diff = PolynomialMath.diff(p1, 1) ;
		double[] diff2 = PolynomialMath.diff(p1, 2) ;
		double[] diff3 = PolynomialMath.diff(p1, 3) ;
		double[] diff4 = PolynomialMath.diff(p1, 4) ;
		double[] diff5 = PolynomialMath.diff(p1, 5) ;
		Polynomial p = Polynomial.ofCoeffs(p1) ;
		Polynomial pdiff = Polynomial.ofCoeffs(diff) ;
		Polynomial pdiff2 = Polynomial.ofCoeffs(diff2) ;
		Polynomial pdiff3 = Polynomial.ofCoeffs(diff3) ;
		Polynomial pdiff4 = Polynomial.ofCoeffs(diff4) ;
		Polynomial pdiff5 = Polynomial.ofCoeffs(diff5) ;
		System.out.println(p);
		System.out.println(pdiff);
		System.out.println(pdiff2);
		System.out.println(pdiff3);
		System.out.println(pdiff4);
		System.out.println(pdiff5);
	}

	public static void test19() {
		// p1(x) = x^3 - x
		double[] p1 = {0.0, -1.0, 0.0, 1.0, 0.0, 0.0} ;
		Polynomial p = Polynomial.ofCoeffs(p1) ;
		Polynomial pdiff = p.diff() ;
		Polynomial pdiff2 = p.diff(2) ;
		Polynomial pdiff3 = p.diff(3) ;
		Polynomial pdiff4 = p.diff(4) ;
		Polynomial pdiff5 = p.diff(5) ;
		System.out.println(p);
		System.out.println(pdiff);
		System.out.println(pdiff2);
		System.out.println(pdiff3);
		System.out.println(pdiff4);
		System.out.println(pdiff5);
	}

	public static void test20() {
		// p1(x) = x^3 - x
		double[] p1 = {0.0, -1.0, 0.0, 1.0, 0.0, 0.1} ;
		double[] p2 = PolynomialMath.integrate(p1) ;
		Polynomial p = Polynomial.ofCoeffs(p1) ;
		Polynomial pint = Polynomial.ofCoeffs(p2) ;
		System.out.println(p);
		System.out.println(pint);
	}

	public static void test21() {
		// p1(x) = x^3 - x
		double[] p1 = {1.0, 0.0, 0.0, 0.0} ;
		double[] p2 = PolynomialMath.integrate(p1) ;
		double p3 = PolynomialMath.integrate(p1, 0.0, 5.0) ;
		Polynomial p = Polynomial.ofCoeffs(p1) ;
		Polynomial pint = Polynomial.ofCoeffs(p2) ;
		System.out.println(p);
		System.out.println(pint);
		System.out.println(p3);
		System.out.println(pint.evaluate(5.0)-pint.evaluate(0.0));
	}

	public static void test22() {
		// p1(x) = x^3 - x
		double[] p1 = {1.0, 2.0, 1.0, 1.0, 1.0} ;
		Polynomial p = Polynomial.ofCoeffs(p1) ;
		System.out.println(p);
		Complex[] roots = p.getRoots() ;
		for(int i=0; i<roots.length; i++)
			System.out.println(roots[i]);
		System.out.println(p.getRootsAsList());
	}

	public static void test23() {
		// p1(x) = x^3 - x
		double[] p1 = {1.0, 2.0, 1.0, 1.0, 1.0} ;
		Polynomial p = Polynomial.ofCoeffs(p1) ;
		ArrayList<Polynomial> factors = p.getFactors() ;
		for(Polynomial poly : factors)
			System.out.println(poly);

		Polynomial p2 = Polynomial.ofFactors(factors) ;
		System.out.println(p2);
	}

	public static void test24() {
		// p1(x) = x^3 - x
		Polynomial p = Polynomial.ofRoots(1.0-0.0*j, 2.0-0.0*j) ;
		System.out.println(p);
	}

	public static void test25() {
		// p1(x) = x^3 - x
		Polynomial p1 = Polynomial.ofCoeffs(0.0, -1.0, 0.0, 1.0, 0.0) ;
		System.out.println(Arrays.toString(p1.coeffs()));
		System.out.println(p1);
		// p2(x) = -x^5 + 0.5x
		Polynomial p2 = Polynomial.ofCoeffs(0.0, 0.5, 0.0, 0.0, 0.0, -5.0, 0.0) ;
		System.out.println(p2);
		ArrayList<Polynomial> commonFactors = Polynomial.getCommonFactors(p1, p2) ;
		System.out.println(commonFactors);
	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
//		test4() ;
//		test5() ;
//		test6() ;
//		test7() ;
//		test8() ;
//		test9() ;
//		test10() ;
//		test11() ;
//		test12() ;
//		test13() ;
//		test14() ;
//		test15() ;
//		test16() ;
//		test17() ;
//		test18() ;
//		test19() ;
//		test20() ;
//		test21() ;
//		test22() ;
//		test23() ;
//		test24() ;
		test25() ;
	}

}
