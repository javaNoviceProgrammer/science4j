package gsl4j;

import java.util.Arrays;

import org.gsl4j.diff.NumericalDiff;
import org.gsl4j.function.MathFunction;
import org.gsl4j.util.MathUtils;

public class TestNumericalDiff {

	public static void test1() {
		double[] x = {0.0, -5.0, 1.2, 3.0*Math.PI} ;
		MathFunction func = t -> Math.sin(t) ;
		for(double val : x) {
			System.out.println(NumericalDiff.central(func, val, 0.1)) ;
			System.out.println(Math.cos(val));
		}
		System.out.println(Arrays.toString(NumericalDiff.central(func, x, 0.1)));
	}

	public static void test2() {
		double[] x = {0.0, -5.0, 1.2, 3.0*Math.PI} ;
		MathFunction func = t -> Math.sin(t) ;
		for(double val : x) {
			System.out.println(NumericalDiff.forward(func, val, 0.1)) ;
			System.out.println(Math.cos(val));
		}
		System.out.println(Arrays.toString(NumericalDiff.forward(func, x, 0.1)));
	}

	public static void test3() {
		double[] x = {0.0, -5.0, 1.2, 3.0*Math.PI} ;
		MathFunction func = t -> Math.sin(t) ;
		for(double val : x) {
			System.out.println(NumericalDiff.backward(func, val, 0.1)) ;
			System.out.println(Math.cos(val));
		}
		System.out.println(Arrays.toString(NumericalDiff.backward(func, x, 0.1)));
	}

	public static void test4() {
		double[] x = MathUtils.linspace(-100.0, 100.0, 1_000_000) ;
		MathFunction func = t -> t*Math.sin(t) ;
		MathFunction func2 = func.deriv() ;
		long start = System.currentTimeMillis() ;
//		double[] deriv = NumericalDiff.central(func, x, 0.1) ;
//		double[] deriv = NumericalDiff.forward(func, x, 0.1) ;

		double[] deriv = Arrays.stream(x)
								.parallel()
								.map(t -> NumericalDiff.central(func, t, 0.1))
								.toArray() ;

		long end = System.currentTimeMillis() ;

		System.out.println("Time (msec) = " + (end-start));
		System.out.println(deriv[0] + " == " + func2.value(x[0]));
		System.out.println(deriv[100] + " == " + func2.value(x[100]));
	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
		test4() ;
	}

}
