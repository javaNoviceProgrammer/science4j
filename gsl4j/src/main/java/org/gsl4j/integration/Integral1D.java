package org.gsl4j.integration;

import org.gsl4j.util.NativeLibraryLoader;

/**
 * This chapter describes routines for performing numerical integration (quadrature) of a function in one dimension. There are routines for adaptive and non-adaptive integration of general functions, with specialised routines for specific cases. These include integration over infinite and semi-infinite ranges, singular integrals, including logarithmic singularities, computation of Cauchy principal values and oscillatory integrals. The library reimplements the algorithms used in QUADPACK, a numerical integration package written by Piessens, de Doncker-Kapenga, Ueberhuber and Kahaner. Fortran code for QUADPACK is available on Netlib. Also included are non-adaptive, fixed-order Gauss-Legendre integration routines with high precision coefficients, as well as fixed-order quadrature rules for a variety of weighting functions from IQPACK.
 * <br>
 * The functions described in this chapter are declared in the header file {@code gsl_integration.h}.
 * <br>
 * Signature of methods: <br>
 * Q - quadrature routine <br>
 * N - non-adaptive integrator <br>
 * A - adaptive integrator <br>
 * G - general integrand (user-defined) <br>
 * W - weight function with integrand <br>
 * S - singularities can be more readily integrated <br>
 * P - points of special difficulty can be supplied <br>
 * I - infinite range of integration <br>
 * O - oscillatory weight function, cos or sin <br>
 * F - Fourier integral <br>
 * C - Cauchy principal value
 *
 * @author Meisam
 *
 */
public class Integral1D {

	static {
		NativeLibraryLoader.loadLibraries();
		initFieldIDs();
	}

	private static native void initFieldIDs() ;

	IntegralFunction1D func ;
	double absErr = 1e-10 ;
	double relErr = 1e-10 ;
	int maxNumberOfIntervals = 100 ;

	/**
	 * Signature of methods: <br>
	 * Q - quadrature routine <br>
	 * N - non-adaptive integrator <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * W - weight function with integrand <br>
	 * S - singularities can be more readily integrated <br>
	 * P - points of special difficulty can be supplied <br>
	 * I - infinite range of integration <br>
	 * O - oscillatory weight function, cos or sin <br>
	 * F - Fourier integral <br>
	 * C - Cauchy principal value
	 *
	 */
	public Integral1D() {
		this.func = null ;
	}

	/**
	 * Signature of methods: <br>
	 * Q - quadrature routine <br>
	 * N - non-adaptive integrator <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * W - weight function with integrand <br>
	 * S - singularities can be more readily integrated <br>
	 * P - points of special difficulty can be supplied <br>
	 * I - infinite range of integration <br>
	 * O - oscillatory weight function, cos or sin <br>
	 * F - Fourier integral <br>
	 * C - Cauchy principal value
	 *
	 * @param func : integral function (integrand)
	 */
	public Integral1D(IntegralFunction1D func) {
		this.func = func ;
	}

	public void setIntegralFunction(IntegralFunction1D func) {
		this.func = func ;
	}

	// QNG non-adaptive Gauss-Kronrod integration over (a,b) interval
	/**
	 * This function applies the Gauss-Kronrod 10-point, 21-point, 43-point and 87-point integration rules in succession until an estimate of the integral of f over (a,b) is achieved within the desired absolute and relative error limits, epsabs and epsrel. The function returns the final approximation, result, an estimate of the absolute error, abserr and the number of function evaluations used, neval. The Gauss-Kronrod rules are designed in such a way that each rule uses all the results of its predecessors, in order to minimize the total number of function evaluations.
	 * <br>
	 * Q - quadrature routine <br>
	 * N - non-adaptive integrator <br>
	 * G - general integrand (user-defined)
	 *
	 * @param a : start of interval
	 * @param b : end of interval
	 * @return numerical integration
	 */
	public native double qng(double a, double b) ;

	/**
	 * Same as {@link #qng(double, double)}, but also returns the integration error and the number of evaluations.
 	 * <br>
 	 * Q - quadrature routine <br>
	 * N - non-adaptive integrator <br>
	 * G - general integrand (user-defined)
	 * @param a : start of interval
	 * @param b : end of interval
	 * @return [numerical integration, abs error, nevals]
	 */
	public native double[] qngDetailed(double a, double b) ;

	// QAG adaptive integration over (a,b) interval
	/**
	 * This function applies an integration rule adaptively until an estimate of the integral of f over (a,b) is achieved within the desired absolute and relative error limits, epsabs and epsrel. The function returns the final approximation, result, and an estimate of the absolute error, abserr.
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * @param a
	 * @param b
	 * @return
	 */
	public native double qagGauss15(double a, double b) ;
	public native double[] qagGauss15withError(double a, double b) ;

	/**
	 * This function applies an integration rule adaptively until an estimate of the integral of f over (a,b) is achieved within the desired absolute and relative error limits, epsabs and epsrel. The function returns the final approximation, result, and an estimate of the absolute error, abserr.
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * @param a
	 * @param b
	 * @return
	 */
	public native double qagGauss21(double a, double b) ;
	public native double[] qagGauss21withError(double a, double b) ;

	/**
	 * This function applies an integration rule adaptively until an estimate of the integral of f over (a,b) is achieved within the desired absolute and relative error limits, epsabs and epsrel. The function returns the final approximation, result, and an estimate of the absolute error, abserr.
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * @param a
	 * @param b
	 * @return
	 */
	public native double qagGauss31(double a, double b) ;
	public native double[] qagGauss31withError(double a, double b) ;

	/**
	 * This function applies an integration rule adaptively until an estimate of the integral of f over (a,b) is achieved within the desired absolute and relative error limits, epsabs and epsrel. The function returns the final approximation, result, and an estimate of the absolute error, abserr.
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * @param a
	 * @param b
	 * @return
	 */
	public native double qagGauss41(double a, double b) ;
	public native double[] qagGauss41withError(double a, double b) ;

	/**
	 * This function applies an integration rule adaptively until an estimate of the integral of f over (a,b) is achieved within the desired absolute and relative error limits, epsabs and epsrel. The function returns the final approximation, result, and an estimate of the absolute error, abserr.
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * @param a
	 * @param b
	 * @return
	 */
	public native double qagGauss51(double a, double b) ;
	public native double[] qagGauss51withError(double a, double b) ;

	/**
	 * This function applies an integration rule adaptively until an estimate of the integral of f over (a,b) is achieved within the desired absolute and relative error limits, epsabs and epsrel. The function returns the final approximation, result, and an estimate of the absolute error, abserr.
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * @param a
	 * @param b
	 * @return
	 */
	public native double qagGauss61(double a, double b) ;
	public native double[] qagGauss61withError(double a, double b) ;

	// QAGS adaptive integration with singularities over (a,b) interval
	public native double qags(double a, double b) ;
	public native double[] qagsWithError(double a, double b) ;

	// QAGP adaptive integration with known singular points
	public native double qagp(double a, double b, double[] breakPoints) ;
	public native double[] qagpWithError(double a, double b, double[] breakPoints) ;

	// QAGI adaptive integration on infinite intervals
	public native double qagi() ;
	public native double[] qagiWithError() ;
	public native double qagiu(double a) ;
	public native double[] qagiuWithError(double a) ;
	public native double qagil(double b) ;
	public native double[] qagilWithError(double b) ;

	// QAWC adaptive integration for Cauchy principal values
	public native double qawc(double a, double b, double c) ;
	public native double[] qawcWithError(double a, double b, double c) ;

	// QAWS adaptive integration for singular functions
	public static final int WEIGHT_FUNC_TYPE_I = 100 ;
	public static final int WEIGHT_FUNC_TYPE_II = 101 ;
	public static final int WEIGHT_FUNC_TYPE_III = 102 ;
	public static final int WEIGHT_FUNC_TYPE_IV = 103 ;
	public native double qaws(double a, double b, int weightFuncType, double alpha, double beta) ;
	public native double[] qawsWithError(double a, double b, int weightFuncType, double alpha, double beta) ;

	// QAWO adaptive integration for oscillatory functions
	public static final int GSL_INTEG_COSINE = 0 ;
	public static final int GSL_INTEG_SINE = 1 ;
	public native double qawo(double a, double b, int choice, double omega) ;
	public native double[] qawoWithError(double a, double b, int choice, double omega) ;

	// QAWF adaptive integration for Fourier integrals
	public native double qawf(double a, int choice, double omega) ;
	public native double[] qawfWithError(double a, int choice, double omega) ;

	// CQUAD doubly-adaptive integration
	public native double cquad(double a, double b) ;
	public native double[] cquadWithError(double a, double b) ;

	// Romberg integration
	public native double romberg(double a, double b) ;
	public native double[] rombergDetailed(double a, double b) ;

	// Gauss-Legendre integration
	public native double glfixed(double a, double b, int numPoints) ;
	public native double[] glfixedPointAndWeight(double a, double b, int numPoints, int index) ;

	// Fixed point quadratures
	public static final int FIXED_LEGENDRE = 2 ;
	public static final int FIXED_CHEBYSHEV = 3 ;
	public static final int FIXED_GEGENBAUER = 4 ;
	public static final int FIXED_JACOBI = 5 ;
	public static final int FIXED_LAGUERRE = 6 ;
	public static final int FIXED_HERMITE = 7 ;
	public static final int FIXED_EXPONENTIAL = 8 ;
	public static final int FIXED_RATIONAL = 9 ;
	public static final int FIXED_CHEBYSHEV2 = 10 ;

	public native double qfixed(double a, double b, int numPoints, int type, double alpha, double beta) ;
	public native double[] qfixedPoints(double a, double b, int numPoints, int type, double alpha, double beta) ;
	public native double[] qfixedWeights(double a, double b, int numPoints, int type, double alpha, double beta) ;

}
