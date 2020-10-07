package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.complex.Complex;
import org.gsl4j.special.Debye;
import org.gsl4j.special.Dilogarithm;
import org.gsl4j.util.MathUtils;

class TestDebye {

	private static void test1() {
		double[] x = MathUtils.linspace(0.0, 10.0, 5) ;
		double[] y = Arrays.stream(x).map(t -> Debye.debye1(t)).toArray() ;
		System.out.println(Arrays.toString(y));
	}

	private static void test2() {
		Complex.setDisplayAccuracy(10);
		Complex z = Complex.ofArray(1.0, 2.0) ;
		double[] y = Dilogarithm.dilog(z.re(), z.im()) ;
		Complex w = Complex.ofArray(y) ;
		System.out.println(w);
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
	}

}
