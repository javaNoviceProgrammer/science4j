package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.plot.xy.XYPlot;
import org.gsl4j.special.Digamma;
import org.gsl4j.util.MathUtils;

class TestDigamma {

	private static void test1() {
		double x = 1.1 ;
		double y = Digamma.digamma(x) ;
		System.out.println(y);
	}

	private static void test2() {
		double x = 1.1 ;
		double y = Digamma.polygamma(2, x) ;
		System.out.println(y);
	}

	private static void test3() {
		double x = 1.1 ;
		double y = Digamma.trigamma(x) ;
		System.out.println(y);
	}

	private static void test4() {
		double[] x = MathUtils.linspace(1.0, 50.0, 1000) ;
		double[] y = Arrays.stream(x).map(Digamma::trigamma).toArray() ;
		XYPlot plt = new XYPlot("Trigamma function") ;
		plt.plot(x, y).color("b") ;
		plt.grid(true).xlabel("X values").ylabel("Y values") ;
		plt.show("./tests/org/gsl4j/tests/test/fig2.py");
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
	}

}
