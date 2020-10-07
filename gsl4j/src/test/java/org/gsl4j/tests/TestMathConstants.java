package org.gsl4j.tests;

import org.gsl4j.MathConstants;

class TestMathConstants {

	private static void test1() {
		System.out.println("M_E = " + MathConstants.M_E);
		System.out.println("M_LOG2E = " + MathConstants.M_LOG2E);
		System.out.println("M_LOG10E = " + MathConstants.M_LOG10E);
		System.out.println("M_SQRT2 = " + MathConstants.M_SQRT2);
		System.out.println("M_SQRT1_2 = " + MathConstants.M_SQRT1_2);
		System.out.println("M_SQRT3 = " + MathConstants.M_SQRT3);
		System.out.println("M_PI = " + MathConstants.M_PI);
		System.out.println("M_PI_2 = " + MathConstants.M_PI_2);
		System.out.println("M_PI_4 = " + MathConstants.M_PI_4);
		System.out.println("M_SQRTPI = " + MathConstants.M_SQRTPI);
		System.out.println("M_2_SQRTPI = " + MathConstants.M_2_SQRTPI);
		System.out.println("M_1_PI = " + MathConstants.M_1_PI);
		System.out.println("M_2_PI = " + MathConstants.M_2_PI);
		System.out.println("M_LN10 = " + MathConstants.M_LN10);
		System.out.println("M_LN2 = " + MathConstants.M_LN2);
		System.out.println("M_LNPI = " + MathConstants.M_LNPI);
		System.out.println("M_EULER = " + MathConstants.M_EULER);
		System.out.println("GSL_POSINF = " + MathConstants.GSL_POSINF);
		System.out.println("GSL_NEGINF = " + MathConstants.GSL_NEGINF);
		System.out.println("GSL_NAN = " + MathConstants.GSL_NAN);
		System.out.println("Double.isNaN(GSL_NAN) = " + Double.isNaN(MathConstants.GSL_NAN));
		System.out.println("MathConstants.isInf(GSL_POSINF) = " + MathConstants.isInf(MathConstants.GSL_POSINF));
		System.out.println("MathConstants.isPositiveInf(GSL_POSINF) = " + MathConstants.isPositiveInf(MathConstants.GSL_POSINF));
		System.out.println("MathConstants.isNegativeInf(GSL_POSINF) = " + MathConstants.isNegativeInf(MathConstants.GSL_POSINF));
		System.out.println("MathConstants.isNegativeInf(GSL_NEGINF) = " + MathConstants.isNegativeInf(MathConstants.GSL_NEGINF));
		System.out.println("Double.isInfinite(GSL_POSINF) = " + Double.isInfinite(MathConstants.GSL_POSINF));
		System.out.println("Double.isInfinite(GSL_NEGINF) = " + Double.isInfinite(MathConstants.GSL_NEGINF));
	}

	public static void main(String[] args) {
		test1() ;
	}

}
