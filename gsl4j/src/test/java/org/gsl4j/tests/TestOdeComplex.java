package org.gsl4j.tests;

import org.gsl4j.ode.DerivFunctionComplex;
import org.gsl4j.ode.OdeSolverComplex;
import org.gsl4j.util.MathUtils;
import org.gsl4j.complex.Complex;
import static org.gsl4j.complex.Complex.* ;

import java.util.Arrays;

public class TestOdeComplex {

	public static void test1() {
		// y' = -y^2, y(0) = 1+j
		DerivFunctionComplex func = (x,y) -> -y*y ;
		double x0 = 0.0 ;
		Complex y0 = 1.0+j ;
		OdeSolverComplex odeSolver = new OdeSolverComplex(func, x0, y0) ;
		double[] x = MathUtils.linspace(0.0, 20.0, 10) ;
		double[][] z = odeSolver.rkf45(x) ;
		System.out.println(Arrays.toString(z[0]));
		System.out.println(Arrays.toString(z[1]));
	}

	public static void test2() {
		// y' = -y^2, y(0) = 1+j
		DerivFunctionComplex func = (x,y) -> -y*y ;
		DerivFunctionComplex dfdx = (x,y) -> 0.0+0.0*j ;
		DerivFunctionComplex dfdy = (x,y) -> -2.0*y ;
		double x0 = 0.0 ;
		Complex y0 = 1.0+j ;
		OdeSolverComplex odeSolver = new OdeSolverComplex(func, dfdx, dfdy, x0, y0) ;
		double[] x = MathUtils.linspace(0.0, 20.0, 10) ;
		Complex[] z = odeSolver.rk4impComplex(x) ;
		System.out.println(Arrays.toString(z));
	}

	public static void main(String[] args) {
		test1() ;
		test2() ;
	}

}
