package org.gsl4j.tests;

import java.util.Arrays;
import java.util.List;

import org.gsl4j.ode.DerivFunction;
import org.gsl4j.ode.OdeSolver;
import org.gsl4j.roots.RealRoot;
import org.gsl4j.roots.RealRootFunction;
import org.gsl4j.util.MathUtils;

public class TestOde {

	public static void test1() {
		DerivFunction func = (x,y) -> x*x ;
		DerivFunction dfdx = (x,y) -> 2.0*x ;
		DerivFunction dfdy = (x,y) -> 0.0 ;
		OdeSolver odeSolver = new OdeSolver(func, dfdx, dfdy, 1.1, 2.2) ;
		double result = odeSolver.rk2(5.0) ;
		System.out.println(result);
	}

	public static void test1_1() {
		DerivFunction func = (x,y) -> x*x ;
		OdeSolver odeSolver = new OdeSolver(func, 1.1, 2.2) ;
		double result = odeSolver.rk2(5.0) ;
		System.out.println(result);
	}

	public static void test2() {
		DerivFunction func = (x,y) -> x*x ;
		DerivFunction dfdx = (x,y) -> 2.0*x ;
		DerivFunction dfdy = (x,y) -> 0.0 ;
		OdeSolver odeSolver = new OdeSolver(func, dfdx, dfdy, 1.1, 2.2) ;
		double result = odeSolver.rk4(5.0) ;
		System.out.println(result);
	}

	public static void test3() {
		DerivFunction func = (x,y) -> x*x ;
		DerivFunction dfdx = (x,y) -> 2.0*x ;
		DerivFunction dfdy = (x,y) -> 0.0 ;
		OdeSolver odeSolver = new OdeSolver(func, dfdx, dfdy, 1.1, 2.2) ;
		double result = odeSolver.rkf45(5.0) ;
		System.out.println(result);
	}

	public static void test4() {
		DerivFunction func = (x,y) -> x*x ;
		DerivFunction dfdx = (x,y) -> 2.0*x ;
		DerivFunction dfdy = (x,y) -> 0.0 ;
		OdeSolver odeSolver = new OdeSolver(func, dfdx, dfdy, 1.1, 2.2) ;
		double result = odeSolver.rkck(5.0) ;
		System.out.println(result);
	}

	public static void test5() {
		DerivFunction func = (x,y) -> x*x ;
		DerivFunction dfdx = (x,y) -> 2.0*x ;
		DerivFunction dfdy = (x,y) -> 0.0 ;
		OdeSolver odeSolver = new OdeSolver(func, dfdx, dfdy, 1.1, 2.2) ;
		double[] x = MathUtils.linspace(1.1, 100.0, 1000) ;
		double[] result = odeSolver.rk4imp(x) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test6() {
		DerivFunction func = (x,y) -> x*x ;
		DerivFunction dfdx = (x,y) -> 2.0*x ;
		DerivFunction dfdy = (x,y) -> 0.0 ;
		OdeSolver odeSolver = new OdeSolver(func, dfdx, dfdy, 1.1, 2.2) ;
		double[] x = MathUtils.linspace(1.1, 100.0, 1000) ;
		double[] result = odeSolver.rk4(x) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test7() {
		DerivFunction func = (x,y) -> x*x ;
		DerivFunction dfdx = (x,y) -> 2.0*x ;
		DerivFunction dfdy = (x,y) -> 0.0 ;
		OdeSolver odeSolver = new OdeSolver(func, dfdx, dfdy, 1.1, 2.2) ;
		double[] x = MathUtils.linspace(1.1, 100.0, 1000) ;
		double[] result = odeSolver.rkck(x) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test8() {
		DerivFunction func = (t,y) -> -y ;
		DerivFunction dfdt = (t,y) -> 0.0 ;
		DerivFunction dfdy = (t,y) -> -1.0 ;
		double t0 = 0.0, y0 = 1.0 ;
		OdeSolver odeSolver = new OdeSolver(func, dfdt, dfdy, t0, y0) ;
		RealRootFunction rootFunc = t -> odeSolver.rkf45(t)-0.8 ;
		RealRoot rootFinder = new RealRoot(rootFunc) ;
		double tval = rootFinder.brentSafe(t0, t0+10000.0) ;
		System.out.println(tval);
		System.out.println(odeSolver.rkf45(tval));
	}

	public static void test9() {
		DerivFunction func = (t,y) -> -y ;
		DerivFunction dfdt = (t,y) -> 0.0 ;
		DerivFunction dfdy = (t,y) -> -1.0 ;
		double t0 = 0.0, y0 = 1.0 ;
		OdeSolver odeSolver = new OdeSolver(func, dfdt, dfdy, t0, y0) ;
		RealRootFunction rootFunc = t -> odeSolver.rkf45(t)-0.8 ;
		RealRoot rootFinder = new RealRoot(rootFunc) ;
		List<Double> tval = rootFinder.brent(t0, t0+1000.0, 100) ;
		System.out.println(tval);
	}

	public static void main(String[] args) {
//		test1() ;
//		test1_1() ;
//		test2() ;
//		test3() ;
//		test4() ;
//		test5() ;
//		test6() ;
		test7() ;
//		test8() ;
//		test9() ;
	}

}
