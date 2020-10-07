package org.gsl4j.tests;

import org.gsl4j.ElementaryFunctions;
import org.gsl4j.MathConstants;

class TestElementaryFunctions {

	private static void test1() {
		System.out.println("acosh(1.2) = " + ElementaryFunctions.acosh(1.2));
		System.out.println("asinh(1.2) = " + ElementaryFunctions.asinh(1.2));
		System.out.println("max(1.2, -2.3) = " + ElementaryFunctions.max(1.2, -2.3));
		System.out.println("max(1.2, GSL_POSINF) = " + ElementaryFunctions.max(1.2, MathConstants.GSL_POSINF));
		System.out.println("pow5(2.2) = " + ElementaryFunctions.pow5(2.2));
		System.out.println("isOdd(3) = " + ElementaryFunctions.isOdd(3));
		System.out.println("isEven(48) = " + ElementaryFunctions.isEven(48));
		System.out.println("isOdd(48) = " + ElementaryFunctions.isOdd(48));
	}

	public static void main(String[] args) {
		test1() ;
	}

}
