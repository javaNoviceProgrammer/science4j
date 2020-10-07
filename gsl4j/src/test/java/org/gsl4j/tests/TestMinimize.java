package org.gsl4j.tests;

import org.gsl4j.function.MathFunction;
import org.gsl4j.optimize.Minimize;

public class TestMinimize {

	public static void test1() {
		MathFunction func = x -> x*x+1 ;
		Minimize minimizer = new Minimize(func, 0.0, -100.0, 100.0) ;
		double xmin = minimizer.goldenSection() ;
		System.out.println("Method: golden section");
		System.out.println("xmin = " + xmin);
		System.out.println("f(xmin) = " + func.value(xmin));
	}

	public static void test2() {
		MathFunction func = x -> x*x+1 ;
		Minimize minimizer = new Minimize(func, 0.0, -100.0, 100.0) ;
		double xmin = minimizer.brent() ;
		System.out.println("Method: brent");
		System.out.println("xmin = " + xmin);
		System.out.println("f(xmin) = " + func.value(xmin));
	}

	public static void test3() {
		MathFunction func = x -> x*x+1 ;
		Minimize minimizer = new Minimize(func, 0.0, -100.0, 100.0) ;
		double xmin = minimizer.quadGolden() ;
		System.out.println("Method: quad golden");
		System.out.println("xmin = " + xmin);
		System.out.println("f(xmin) = " + func.value(xmin));
	}

	public static void test4() {
		MathFunction func = x -> x*x*Math.sin(x)+1.0 ;
		Minimize minimizer = new Minimize(func, 0.0, -1.5*Math.PI, 0.5*Math.PI) ;
		double xmin = minimizer.goldenSection() ;
		System.out.println("Method: golden section");
		System.out.println("xmin = " + xmin);
		System.out.println("f(xmin) = " + func.value(xmin));
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
	}

}
