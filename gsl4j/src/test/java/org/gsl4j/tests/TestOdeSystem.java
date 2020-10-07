package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.ode.DerivnFunction;
import org.gsl4j.ode.DerivnJacobian;
import org.gsl4j.ode.OdeSystemSolver;
import org.gsl4j.util.MathUtils;
import org.gsl4j.util.Timer;

public class TestOdeSystem {

	public static void test1() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.rk2(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test2() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.rk4(5.0) ;
		System.out.println(Arrays.toString(result));
	}


	public static void test3() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.rkf45(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test4() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.rkck(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test5() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.rk8pd(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test6() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.rk1imp(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test7() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.rk2imp(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test8() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.rk4imp(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test9() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.bsimp(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test10() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.msadams(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test11() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		double[] result = solver.msbdf(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test12() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, 1.1, y0) ;
		solver.setAbsErr(1e-10);
		double[] result = solver.msbdf(5.0) ;
		System.out.println(Arrays.toString(result));
	}

	public static void test13() {
		DerivnFunction func = (x, y) -> new double[] { x*x, 0.0};
		DerivnFunction dfdx = (x, y) -> new double[] { 2.0*x, 0.0 };
		DerivnJacobian dfdy = (x, y) -> new double[][] { { 0.0, 0.0}, { 0.0, 0.0 } };

		double[] y0 = {2.2, 3.3} ;
		double x0 = 1.1 ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, x0, y0) ;
		double[] result = solver.rk4(3.0) ;
		System.out.println(Arrays.toString(result));
		result = solver.rk4(5.0) ;
		System.out.println(Arrays.toString(result));
		double[][] result2 = solver.rk4(new double[]{3.0, 5.0}) ;
		System.out.println(Arrays.deepToString(result2));
		System.out.println(Arrays.toString(result2[0]));
		System.out.println(Arrays.toString(result2[1]));
	}

	public static void test14() {
		double x0 = 0.0 ;
		double[] y0 = {1.0, 1.0} ;
		DerivnFunction func = (x,y) -> new double[] {-x, 0.0} ;
		DerivnFunction dfdx = (x,y) -> new double[] {-1.0, 0.0} ;
		DerivnJacobian dfdy = (x,y) -> new double[][] {{0.0, 0.0}, {0.0, 0.0}} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, x0, y0) ;
		double[] x = {0.1, 3.0, 5.0} ;
		double[][] z = solver.rk2(x) ;
		double[] z1 = Arrays.stream(x).map(t -> solver.rk2(t)[0]).toArray() ;

		System.out.println(Arrays.toString(z[0]));
		System.out.println(Arrays.toString(z1));
	}

	public static void test15() {
		double x0 = 0.0 ;
		double[] y0 = {0.0, 1.0} ;
		DerivnFunction func = (x,y) -> new double[] {y[1], -y[0]} ;
		DerivnFunction dfdx = (x,y) -> new double[] {0.0, 0.0} ;
		DerivnJacobian dfdy = (x,y) -> new double[][] {{0.0, 1.0}, {-1.0, 0.0}} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, x0, y0) ;
		double[] x = MathUtils.linspace(0.0, 200.0, 10_000) ;
		Timer timer = new Timer() ;
		timer.start();
		solver.rkf45(x) ;
		timer.stop();
		timer.show();
	}

	public static void test16() {
		double x0 = 0.0 ;
		double[] y0 = {0.0, 1.0} ;
		DerivnFunction func = (x,y) -> new double[] {y[1], -y[0]} ;
		DerivnFunction dfdx = (x,y) -> new double[] {0.0, 0.0} ;
		DerivnJacobian dfdy = (x,y) -> new double[][] {{0.0, 1.0}, {-1.0, 0.0}} ;
		OdeSystemSolver solver = new OdeSystemSolver(2, func, dfdx, dfdy, x0, y0) ;
		double[] x = MathUtils.linspace(0.0, 2.0, 10) ;
		double[][] z = solver.rkf45(x) ;
		System.out.println(Arrays.toString(z[1]));

		OdeSystemSolver solver2 = new OdeSystemSolver(2, func, dfdy, x0, y0) ;
		double[][] w = solver2.rkf45(x) ;
		System.out.println(Arrays.toString(w[1]));

		OdeSystemSolver solver3 = new OdeSystemSolver(2, func, x0, y0) ;
		double[][] s = solver3.rkf45(x) ;
		System.out.println(Arrays.toString(s[1]));
	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
//		test4() ;
//		test5() ;
//		test6() ;
//		test7() ;
//		test8() ;
//		test9() ;
//		test10() ;
//		test11() ;
//		test12() ;
//		test13() ;
//		test14() ;
//		test15() ;
		test16() ;
	}

}
