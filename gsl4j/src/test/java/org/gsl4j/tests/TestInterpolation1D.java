package org.gsl4j.tests;

import org.gsl4j.interpolate.Interpolation1D;
import org.gsl4j.interpolate.oned.AkimaSplineInterpolation;
import org.gsl4j.interpolate.oned.CubicSplineInterpolation;
import org.gsl4j.interpolate.oned.LinearInterpolation;
import org.gsl4j.interpolate.oned.PeriodicAkimaSplineInterpolation;
import org.gsl4j.util.ArrayUtils;
import org.gsl4j.util.MathUtils;
import org.gsl4j.util.Timer;


class TestInterpolation1D {

	public static void test1() {
		double[] x = MathUtils.linspace(0.0, 1.0, 10) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D linInterp = new LinearInterpolation(x, y) ;
		System.out.println(linInterp.name());
	}

	public static void test2() {
		double[] x = MathUtils.linspace(0.0, 1.0, 1000) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D linInterp = new LinearInterpolation(x, y) ;
		double z = linInterp.eval(0.222222) ;
		System.out.println(z);
		System.out.println(Math.sin(0.222222));
	}

	public static void test3() {
		double[] x = MathUtils.linspace(0.0, 1.0, 10000) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D linInterp = new CubicSplineInterpolation(x, y) ;
		System.out.println(linInterp.name());
	}

	public static void test4() {
		double[] x = MathUtils.linspace(0.0, 1.0, 1000) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D cubicSpline = new CubicSplineInterpolation(x, y) ;
		double z = cubicSpline.eval(0.222222) ;
		System.out.println(z);
		System.out.println(Math.sin(0.222222));
	}

	public static void test5() {
		double[] x = MathUtils.linspace(0.0, 1.0, 10) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D linInterp = new AkimaSplineInterpolation(x, y) ;
		System.out.println(linInterp.name());
	}

	public static void test6() {
		double[] x = MathUtils.linspace(0.0, 1.0, 1000) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D cubicSpline = new AkimaSplineInterpolation(x, y) ;
		double z = cubicSpline.eval(0.222222) ;
		System.out.println(z);
		System.out.println(Math.sin(0.222222));
	}

	public static void test7() {
		double[] x = MathUtils.linspace(0.0, 1.0, 10) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D linInterp = new PeriodicAkimaSplineInterpolation(x, y) ;
		System.out.println(linInterp.name());
	}

	public static void test8() {
		double[] x = MathUtils.linspace(0.0, 1.0, 1000) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D cubicSpline = new PeriodicAkimaSplineInterpolation(x, y) ;
		double z = cubicSpline.eval(0.222222) ;
		System.out.println(z);
		System.out.println(Math.sin(0.222222));
	}

	public static void test9() {
		double[] x = MathUtils.linspace(0.0, 1.0, 1000) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D cubicSpline = new PeriodicAkimaSplineInterpolation(x, y) ;
		double[] y2 = ArrayUtils.eval(t -> Math.cos(t), x) ;
		Interpolation1D linearInterp = new LinearInterpolation(x, y2) ;
		System.out.println(cubicSpline.name());
		double z = cubicSpline.eval(0.222222) ;
		System.out.println(z);
		System.out.println(Math.sin(0.222222));
		System.out.println(linearInterp.name());
		double z2 = linearInterp.eval(0.222222) ;
		System.out.println(z2);
		System.out.println(Math.cos(0.222222));
	}

	public static void test10() {
		double[] x = MathUtils.linspace(0.0, 1.0, 100) ;
		double[] y = ArrayUtils.eval(t -> Math.sin(t), x) ;
		Interpolation1D cubicSpline = new CubicSplineInterpolation(x, y) ;
		double[] xvals = MathUtils.linspace(0.0, 1.0, 1_000_000) ;
		Timer timer = new Timer() ;
		timer.start();
		cubicSpline.eval(xvals) ;
		timer.stop();
		System.out.println("1,000,000 interpolations => " + timer);
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
		test3() ;
		test4() ;
		test5() ;
		test6() ;
		test7() ;
		test8() ;
		test9() ;
		test10() ;
	}

}
