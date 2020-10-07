package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.MathConstants;
import org.gsl4j.sequence.LevinTransform;
import org.gsl4j.sequence.Sequence;
import org.gsl4j.util.MathUtils;

class TestLevinTransform {

	private static void test1() {
		// sequence a_n = 1/n^2
		int n = 20 ;
		double[] nVals = MathUtils.linspace(1, n, 1.0) ;
		double[] arrayVals = Arrays.stream(nVals).map(t -> 1.0/(t*t)).toArray() ;
		double[] result = LevinTransform.sumLevinUaccel(arrayVals) ;
		double sum = result[0] ;
		double err = result[1] ;
		System.out.printf("sum = %.16f\nerror = %.16f\n", sum, err);
	}

	private static void test2() {
		// sequence a_n = 1/n^2
		int n = 20 ;
		double[] nVals = MathUtils.linspace(1, n, 1.0) ;
		double[] arrayVals = Arrays.stream(nVals).map(t -> 1.0/(t*t)).toArray() ;
		double[] result = LevinTransform.sumLevinUtruncAccel(arrayVals) ;
		double sum = result[0] ;
		double err = result[1] ;
		System.out.printf("sum = %.16f\nerror = %.16f\n", sum, err);
	}

	private static void test3() {
		// sequence a_n = 1/n^2
		Sequence seq1 = n -> 1.0/n * 1.0/n ;
		Sequence seq2 = seq1.LevinU(1) ;
		System.out.printf("sum = %.16f\n", seq2.evaluate(20));
	}

	private static void test4() {
		// sequence a_n = 1/n^2
		Sequence seq1 = n -> 4.0*(n%2==0 ? 1.0 : -1.0)/(2.0*n+1) ;
		Sequence seq2 = seq1.LevinU(0) ;
		System.out.printf("sum = %.16f\n", seq2.evaluate(20));
		System.out.println("Exact = " + MathConstants.M_PI);
	}


	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
	}

}
