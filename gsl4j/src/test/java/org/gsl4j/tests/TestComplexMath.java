package org.gsl4j.tests;

import java.util.Arrays;
import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexMath;


class TestComplexMath {

	static {
		Complex.setDisplayAccuracy(5);
	}


	private static void test1() {
		Complex z1 = Complex.ofRect(1.1, 2.2) ;
		Complex z2 = Complex.ofRect(3.0, -1.0) ;
		double[] sum = ComplexMath.add(z1.re(), z1.im(), z2.re(), z2.im()) ;
		Complex result = Complex.ofArray(sum) ;
		System.out.println("(" + z1 + ")" + "+" + "(" + z2 + ")" + " = " + result);
	}

	private static void test2() {
		Complex z1 = Complex.ofRect(1.1, 2.2) ;
		Complex z2 = Complex.ofRect(3.0, -1.0) ;
		double[] sum = ComplexMath.mul(z1.re(), z1.im(), z2.re(), z2.im()) ;
		Complex result = Complex.ofArray(sum) ;
		System.out.println("(" + z1 + ")" + "*" + "(" + z2 + ")" + " = " + result);
	}

	private static void test3() {
		Complex z1 = Complex.ofRect(1.1, 2.2) ;
		Complex z2 = Complex.ofRect(3.0, -1.0) ;
		double[] sum = ComplexMath.sub(z1.re(), z1.im(), z2.re(), z2.im()) ;
		Complex result = Complex.ofArray(sum) ;
		System.out.println("(" + z1 + ")" + "-" + "(" + z2 + ")" + " = " + result);
	}

	private static void test4() {
		Complex z1 = Complex.ofRect(1.1, 2.2) ;
		Complex z2 = Complex.ofRect(3.0, -1.0) ;
		double[] sum = ComplexMath.div(z1.re(), z1.im(), z2.re(), z2.im()) ;
		Complex result = Complex.ofArray(sum) ;
		System.out.println("(" + z1 + ")" + "/" + "(" + z2 + ")" + " = " + result);
	}

	private static void test5() {
		Complex z1 = Complex.ofRect(1.1, 2.2) ;
		Complex z2 = Complex.ofRect(3.0, -1.0) ;
		Complex z3 = Complex.ofRect(10.0, 10.0) ;
		Complex z4 = Complex.ofRect(0.0, 1.0) ;
		Complex[] values = {z1, z2, z3, z4} ;
		double[] sum = new double[2] ;
		for(Complex z : values) {
			sum = ComplexMath.add(z.re(), z.im(), sum[0], sum[1]) ;
		}
		Complex result = Complex.ofArray(sum) ;
		System.out.println(result);
	}

	private static void test6() {
		Complex imagNumber = Complex.ofPolarDegree(1.0, 30.0) ;
		Complex[] values = new Complex[1_000_000] ;
		Arrays.fill(values, imagNumber);
		double[] sum = {1.0, 0.0} ;
		for(Complex z : values) {
			sum = ComplexMath.mul(z.re(), z.im(), sum[0], sum[1]) ;
		}
		Complex result = Complex.ofArray(sum) ;
		System.out.println(result);
	}

	private static void test7() {

		ComplexBuilder cb = new ComplexBuilder() ;
		cb.add(1.1, 2.2).multiply(Complex.j) ;
		System.out.println("Complex builder = " + cb);
		Complex u = Complex.ofArray(ComplexMath.tanh(cb.re(), cb.im())) ;
		System.out.println("tanh(cb) = " + u);
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
		test5() ;
		test6() ;
		test7() ;
	}

}
