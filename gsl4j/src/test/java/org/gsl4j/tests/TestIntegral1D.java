package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.function.MathFunction;
import org.gsl4j.integration.Integral1D;
import org.gsl4j.integration.IntegralFunction1D;


class TestIntegral1D {

	public static void test1() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qng(1.1, 2.2) ;
		System.out.println(result);
	}

	public static void test2() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qngDetailed(1.1, 2.2) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test3() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagGauss15(1.1, 2.2) ;
		System.out.println(result);
	}

	public static void test4() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagGauss15withError(1.1, 2.2) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test5() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagGauss21(1.1, 2.2) ;
		System.out.println(result);
	}

	public static void test5_1() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagGauss21withError(1.1, 2.2) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test6() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagGauss31(1.1, 2.2) ;
		System.out.println(result);
	}

	public static void test6_1() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagGauss31withError(1.1, 2.2) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test7() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagGauss41(1.1, 2.2) ;
		System.out.println(result);
	}

	public static void test7_1() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagGauss41withError(1.1, 2.2) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test8() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagGauss51(1.1, 2.2) ;
		System.out.println(result);
	}

	public static void test8_1() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagGauss51withError(1.1, 2.2) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test9() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagGauss61(1.1, 2.2) ;
		System.out.println(result);
	}

	public static void test9_1() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagGauss61withError(1.1, 2.2) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test10() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qags(1.1, 2.2) ;
		System.out.println(result);
	}

	public static void test10_1() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagsWithError(1.1, 2.2) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test11() {
		IntegralFunction1D func = t -> 1.0/(t*t)+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qags(1.1, 2.2) ;
		System.out.println(result);
	}

	public static void test11_1() {
		IntegralFunction1D func = t -> 1.0/(t*t)+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagsWithError(1.1, 2.2) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test12() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagp(1.1, 2.2, new double[] {2.0}) ;
		System.out.println(result);
	}

	public static void test12_1() {
		IntegralFunction1D func = t -> t*t+1.0 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagpWithError(1.1, 2.2, new double[] {2.0}) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test13() {
		IntegralFunction1D func = t -> 1.0/(1.0+t*t*t*t) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagi() ;
		System.out.println(result);
	}

	public static void test13_1() {
		IntegralFunction1D func = t -> 1.0/(1.0+t*t*t*t) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagiWithError() ;
		System.out.println(Arrays.toString(result));
	}

	public static void test14() {
		IntegralFunction1D func = t -> 1.0/(1.0+t*t*t*t) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagiu(1.1) ;
		System.out.println(result);
	}

	public static void test14_1() {
		IntegralFunction1D func = t -> 1.0/(1.0+t*t*t*t) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagiuWithError(1.1) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test15() {
		IntegralFunction1D func = t -> 1.0/(1.0+t*t*t*t) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qagil(1.1) ;
		System.out.println(result);
	}

	public static void test15_1() {
		IntegralFunction1D func = t -> 1.0/(1.0+t*t*t*t) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qagilWithError(1.1) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test16() {
		MathFunction f = t -> t*t ;
		IntegralFunction1D func = t -> f.value(t)/(t*t+1.1) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qng(0.0, 2.0) ;
		System.out.println(result);
	}

	public static void test16_1() {
		MathFunction f = t -> t*t ;
		IntegralFunction1D func = t -> f.value(t)/(t*t+1.1) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qawcWithError(0.0, 2.0, 1.1) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test17() {
		IntegralFunction1D func = t -> t*t+1.1 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qaws(1.1, 2.2, Integral1D.WEIGHT_FUNC_TYPE_I, 1.0, 1.0) ;
		System.out.println(result);
	}

	public static void test17_1() {
		IntegralFunction1D func = t -> t*t+1.1 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qawsWithError(1.1, 2.2, Integral1D.WEIGHT_FUNC_TYPE_I, 1.0, 1.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test18() {
		double omega = 5.0 ;
		IntegralFunction1D func = t -> 1 ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qawo(0.0, 10000.0, Integral1D.GSL_INTEG_SINE, omega) ;
		System.out.println(result);
	}

	public static void test18_1() {
		double omega = 5.0 ;
		IntegralFunction1D func = t -> 1 ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qawoWithError(0.0, 10000.0, Integral1D.GSL_INTEG_SINE, omega) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test19() {
		double omega = 5.0 ;
		IntegralFunction1D func = t -> Math.exp(-t) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qawf(0.0, Integral1D.GSL_INTEG_SINE, omega) ;
		System.out.println(result);
	}

	public static void test19_1() {
		double omega = 5.0 ;
		IntegralFunction1D func = t -> Math.exp(-t) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qawfWithError(0.0, Integral1D.GSL_INTEG_SINE, omega) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test20() {
		IntegralFunction1D func = t -> Math.exp(-t) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.cquad(0.0, 10.0) ;
		System.out.println(result);
	}

	public static void test20_1() {
		IntegralFunction1D func = t -> Math.exp(-t) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.cquadWithError(0.0, 10.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test21() {
		IntegralFunction1D func = t -> Math.exp(-t) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.cquad(0.0, 1e10) ;
		System.out.println(result);
	}

	public static void test22() {
		IntegralFunction1D func = t -> Math.sin(t) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.romberg(0.0, 10.0) ;
		System.out.println(result);
	}

	public static void test22_1() {
		IntegralFunction1D func = t -> Math.sin(t) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.rombergDetailed(0.0, 10.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test23() {
		IntegralFunction1D func = t -> Math.sin(t) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.glfixed(1.1, 2.2, 20) ;
		System.out.println(result);
	}

	public static void test23_1() {
		IntegralFunction1D func = t -> Math.sin(t) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.glfixedPointAndWeight(-1.0, 1.0, 3, 0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test24() {
		IntegralFunction1D func = t -> Math.sin(t) ;
		Integral1D integral = new Integral1D(func) ;
		double result = integral.qfixed(1.1, 2.2, 10, Integral1D.FIXED_LEGENDRE, 0, 0) ;
		System.out.println(result);
	}

	public static void test24_1() {
		IntegralFunction1D func = t -> Math.sin(t) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qfixedPoints(1.1, 2.2, 5, Integral1D.FIXED_LEGENDRE, 0, 0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test24_2() {
		IntegralFunction1D func = t -> Math.sin(t) ;
		Integral1D integral = new Integral1D(func) ;
		double[] result = integral.qfixedWeights(1.1, 2.2, 5, Integral1D.FIXED_LEGENDRE, 0, 0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
//		test4() ;
//		test5() ;
//		test5_1() ;
//		test6() ;
//		test6_1() ;
//		test7() ;
//		test7_1() ;
//		test8() ;
//		test8_1() ;
//		test9() ;
//		test9_1() ;
//		test10() ;
//		test10_1() ;
//		test11() ;
//		test11_1() ;
//		test12() ;
//		test12_1() ;
//		test13() ;
//		test13_1() ;
//		test14() ;
//		test14_1() ;
//		test15() ;
//		test15_1() ;
//		test16() ;
//		test16_1() ;
//		test17() ;
//		test17_1() ;
//		test18() ;
//		test18_1() ;
//		test19() ;
//		test19_1() ;
//		test20() ;
//		test20_1() ;
//		test21() ;
//		test22() ;
//		test22_1() ;
//		test23() ;
//		test23_1() ;
		test24() ;
		test24_1() ;
		test24_2() ;
	}

}
