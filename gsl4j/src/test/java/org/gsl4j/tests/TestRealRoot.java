package org.gsl4j.tests;

import org.gsl4j.function.MathFunction;
import org.gsl4j.roots.RealRoot;
import org.gsl4j.roots.RealRootDerivFunction;
import org.gsl4j.roots.RealRootFunction;
import org.gsl4j.util.Timer;

public class TestRealRoot {

	public static void test1() {
		RealRootFunction func = t -> t*t*t+1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("bisection = " + rootFinder.bisection(-10.0, 2.0));
	}

	public static void test1_1() {
		RealRootFunction func = t -> t*t*t+1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("bisection = " + rootFinder.bisectionSafe(-10.0, 2.0));
	}

	public static void test2() {
		RealRootFunction func = t -> t*t*t+1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("False position = " + rootFinder.falsepos(-10.0, 2.0));
	}

	public static void test3() {
		RealRootFunction func = t -> t*t*t+1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("brent = " + rootFinder.brent(-10.0, 2.0));
	}

	public static void test4() {
		RealRootFunction func = t -> t*t*t*t+1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("newton = " + rootFinder.newton(1.0)) ;
	}

	public static void test4_1() {
		RealRootFunction func = t -> t*t*t*t+1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("newton = " + rootFinder.newtonSafe(10.0)) ;
	}

	public static void test5() {
		RealRootFunction func = t -> t*t*t+1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("secant = " + rootFinder.secant(1.0)) ;
	}

	public static void test6() {
		RealRootFunction func = t -> t*t*t+1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("steffenson = " + rootFinder.steffenson(1.0)) ;
	}

	public static void test7() {
		RealRootDerivFunction func = t -> new double[] {t*t*t+1.1, 3.0*t*t} ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("newton = " + rootFinder.newton(1.0)) ;
	}

	public static void test8() {
		RealRootDerivFunction func = t -> new double[] {t*t*t+1.1, 3.0*t*t} ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("secant = " + rootFinder.secant(1.0)) ;
	}

	public static void test9() {
		RealRootDerivFunction func = t -> new double[] {t*t*t+1.1, 3.0*t*t} ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("steffenson = " + rootFinder.steffenson(1.0)) ;
	}

	public static void test10() {
		MathFunction func = t -> t*t*Math.sin(t) ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("steffenson = " + rootFinder.steffenson(1.0)) ;
	}

	public static void test11() {
		MathFunction func = t -> t*t-1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("steffenson = " + rootFinder.steffensonSafe(1.0)) ;
	}

	public static void test12() {
		MathFunction func = t -> t*t*t*t+2.0*t-1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("bisection = " + rootFinder.bisection(-10.0, 10.0, 100)) ;
	}

	public static void test13() {
		MathFunction func = t -> t*t*t*t+2.0*t-1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("falsepos = " + rootFinder.falsepos(-10.0, 10.0, 100)) ;
	}

	public static void test14() {
		MathFunction func = t -> t*t*t*t+2.0*t-1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("brent = " + rootFinder.brent(-10.0, 10.0, 100)) ;
	}

	public static void test15() {
		MathFunction func = t -> t*t*t*t+2.0*t-1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("newton = " + rootFinder.newton(-10.0, 10.0, 100)) ;
	}

	public static void test16() {
		MathFunction func = t -> t*t*t*t+2.0*t-1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("secant = " + rootFinder.secant(-10.0, 10.0, 100)) ;
	}

	public static void test17() {
		MathFunction func = t -> t*t*t*t+2.0*t-1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		System.out.println("steffenson = " + rootFinder.steffenson(-10.0, 10.0, 100)) ;
	}

	public static void test18() {
		MathFunction func = t -> t*t*t*t+2.0*t-1.1 ;
		RealRoot rootFinder = new RealRoot(func) ;
		Timer timer = new Timer() ;
		timer.start();
		for(int i=0; i<1000; i++)
			rootFinder.steffensonSafe(10.1) ;
		timer.stop();
		timer.show();
	}


	public static void main(String[] args) {
		test1() ;
		test1_1() ;
		test2() ;
		test3() ;
		test4() ;
		test4_1() ;
		test5() ;
		test6() ;
		test7() ;
		test8() ;
		test9() ;
		test10() ;
		test11() ;
		test12() ;
		test13() ;
		test14() ;
		test15() ;
		test16() ;
		test17() ;
//		test18() ;
	}

}
