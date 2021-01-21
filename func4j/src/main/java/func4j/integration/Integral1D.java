package func4j.integration;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import func4j.integration.domain.IntegralDomain1D;
import func4j.natives.NativeLibraryLoader;
import func4j.special.SpecialFuncs;


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
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
		initFieldIDs();
	}

	private static native void initFieldIDs() ;

	IntegralFunction1D func ;
	IntegralDomain1D domain ;
	double absErr = 1e-10 ;
	double relErr = 1e-10 ;
	int maxNumberOfIntervals = 1000 ;

//	public native void allocateWorkspace() ;
//	public native void freeWorkspace() ;

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
		this.domain = null ;
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
	
	public Integral1D(IntegralFunction1D func, IntegralDomain1D domain) {
		this.func = func ;
		this.domain = domain ;
	}

	public void setIntegralFunction(IntegralFunction1D func) {
		this.func = func ;
	}
	
	public void setDomain(IntegralDomain1D domain) {
		this.domain = domain ;
	}

	
	public void setMaxIterations(int iterations) {
		this.maxNumberOfIntervals = iterations ;
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
	
	public double qng(IntegralDomain1D domain) {
		return qng(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qng() {
		return qng(domain.var1Min(), domain.var1Max()) ;
	}

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
	
	public double[] qngDetailed(IntegralDomain1D domain) {
		return qngDetailed(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double[] qngDetailed() {
		return qngDetailed(domain.var1Min(), domain.var1Max()) ;
	}

	// QAG adaptive integration over (a,b) interval
	/**
	 * This function applies an integration rule adaptively until an estimate of the integral of f over (a,b) is achieved within the desired absolute and relative error limits, epsabs and epsrel. The function returns the final approximation, result, and an estimate of the absolute error, abserr.
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * @param a : start of interval
	 * @param b : end of interval
	 * @return  numerical integration
	 */
	public native double qagGauss15(double a, double b) ;
	public native double[] qagGauss15withError(double a, double b) ;
	
	public double qagGauss15(IntegralDomain1D domain) {
		return qagGauss15(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double[] qagGauss15withError(IntegralDomain1D domain) {
		return qagGauss15withError(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss15() {
		return qagGauss15(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double[] qagGauss15withError() {
		return qagGauss15withError(domain.var1Min(), domain.var1Max()) ;
	}

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
	
	public double qagGauss21(IntegralDomain1D domain) {
		return qagGauss21(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss21() {
		return qagGauss21(domain.var1Min(), domain.var1Max()) ;
	}

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
	
	public double qagGauss31(IntegralDomain1D domain) {
		return qagGauss31(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss31() {
		return qagGauss31(domain.var1Min(), domain.var1Max()) ;
	}

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
	
	public double qagGauss41(IntegralDomain1D domain) {
		return qagGauss41(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss41() {
		return qagGauss41(domain.var1Min(), domain.var1Max()) ;
	}

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
	
	public double qagGauss51(IntegralDomain1D domain) {
		return qagGauss51(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss51() {
		return qagGauss51(domain.var1Min(), domain.var1Max()) ;
	}

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
	
	public double qagGauss61(IntegralDomain1D domain) {
		return qagGauss61(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double qagGauss61() {
		return qagGauss61(domain.var1Min(), domain.var1Max()) ;
	}

	// QAGS adaptive integration with singularities over (a,b) interval
	/**
	 * Adaptive integration with singularities over (a,b) interval
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * S - singularities can be more readily integrated <br>
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public native double qags(double a, double b) ;
	public native double[] qagsWithError(double a, double b) ;
	
	public double qags(IntegralDomain1D domain) {
		return qags(domain.var1Min(), domain.var1Max()) ;
	}

	// QAGP adaptive integration with known singular points
	/**
	 * Adaptive integration with known singular points
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * P - points of special difficulty can be supplied <br>
	 * 
	 * @param a
	 * @param b
	 * @param breakPoints
	 * @return
	 */
	public native double qagp(double a, double b, double[] breakPoints) ;
	public native double[] qagpWithError(double a, double b, double[] breakPoints) ;
	
	public double qagp(IntegralDomain1D domain, double[] breakPoints) {
		return qagp(domain.var1Min(), domain.var1Max(), breakPoints) ;
	}
	
	public double qagp(double[] breakPoints) {
		return qagp(domain.var1Min(), domain.var1Max(), breakPoints) ;
	}

	// QAGI adaptive integration on infinite intervals
	/**
	 * Adaptive integration on infinite intervals [-inf, inf]
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * I - infinite range of integration <br>
	 * 
	 * @return
	 */
	public native double qagi() ;
	public native double[] qagiWithError() ;
	
	/**
	 * Adaptive integration on semi-infinite intervals [a, inf]
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * I - infinite range of integration <br>
	 * 
	 * @return
	 */
	public native double qagiu(double a) ;
	public native double[] qagiuWithError(double a) ;
	
	public double qagiu(IntegralDomain1D domain) {
		return qagiu(domain.var1Min()) ;  // only use lower bound
	}
	
	/**
	 * Adaptive integration on semi-infinite intervals [-inf, a]
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * G - general integrand (user-defined) <br>
	 * I - infinite range of integration <br>
	 * 
	 * @return
	 */
	public native double qagil(double b) ;
	public native double[] qagilWithError(double b) ;
	
	public double qagil(IntegralDomain1D domain) {
		return qagil(domain.var1Max()) ; // only use upper bound
	}

	// QAWC adaptive integration for Cauchy principal values
	/**
	 * Adaptive integration for Cauchy principal values
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * W - weight function with integrand <br>
	 * C - Cauchy principal value
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public native double qawc(double a, double b, double c) ;
	public native double[] qawcWithError(double a, double b, double c) ;
	
	public double qawc(IntegralDomain1D domain, double c) {
		return qawc(domain.var1Min(), domain.var1Max(), c) ;
	}

	// QAWS adaptive integration for singular functions
	public static final int WEIGHT_FUNC_TYPE_I = 100 ;
	public static final int WEIGHT_FUNC_TYPE_II = 101 ;
	public static final int WEIGHT_FUNC_TYPE_III = 102 ;
	public static final int WEIGHT_FUNC_TYPE_IV = 103 ;
	
	public native double qaws(double a, double b, int weightFuncType, double alpha, double beta) ;
	public native double[] qawsWithError(double a, double b, int weightFuncType, double alpha, double beta) ;

	public double qaws(IntegralDomain1D domain, int weightFuncType, double alpha, double beta) {
		return qaws(domain.var1Min(), domain.var1Max(), weightFuncType, alpha, beta) ;
	}
	
	// QAWO adaptive integration for oscillatory functions
	public static final int GSL_INTEG_COSINE = 0 ;
	public static final int GSL_INTEG_SINE = 1 ;
	/**
	 * Adaptive integration for oscillatory functions
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * W - weight function with integrand <br>
	 * O - oscillatory weight function, cos or sin <br>
	 * 
	 * @param a
	 * @param b
	 * @param choice
	 * @param omega
	 * @return
	 */
	public native double qawo(double a, double b, int choice, double omega) ;
	public native double[] qawoWithError(double a, double b, int choice, double omega) ;
	
	public double qawo(IntegralDomain1D domain, int choice, double omega) {
		return qawo(domain.var1Min(), domain.var1Max(), choice, omega) ;
	}

	// QAWF adaptive integration for Fourier integrals
	/**
	 * Adaptive integration for Fourier integrals
	 * <br>
	 * Q - quadrature routine <br>
	 * A - adaptive integrator <br>
	 * W - weight function with integrand <br>
	 * F - Fourier integral <br>
	 * 
	 * @param a
	 * @param choice
	 * @param omega
	 * @return
	 */
	public native double qawf(double a, int choice, double omega) ;
	public native double[] qawfWithError(double a, int choice, double omega) ;

	// CQUAD doubly-adaptive integration
	/**
	 * Doubly-adaptive integration.
	 * The underlying algorithm uses a doubly-adaptive scheme in which Clenshaw-Curtis quadrature rules of increasing degree are used to compute the integral in each interval.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public native double cquad(double a, double b) ;
	public native double[] cquadWithError(double a, double b) ;
	
	public double cquad(IntegralDomain1D domain) {
		return cquad(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double cquad() {
		return cquad(domain.var1Min(), domain.var1Max()) ;
	}

	// Romberg integration
	/**
	 * The Romberg integration method estimates the definite integral 
	 * by applying Richardson extrapolation on the trapezoidal rule, using equally spaced points with spacing
	 * hk = (b-a)^2 k for k = 1,..., n. For each k, Richardson extrapolation is used k-1 times on previous approximations to improve the order of accuracy as much as possible. Romberg integration typically works well (and converges quickly) for smooth integrands with no singularities in the interval or at the end points.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public native double romberg(double a, double b) ;
	public native double[] rombergDetailed(double a, double b) ;
	
	public double romberg(IntegralDomain1D domain) {
		return romberg(domain.var1Min(), domain.var1Max()) ;
	}
	
	public double romberg() {
		return romberg(domain.var1Min(), domain.var1Max()) ;
	}

	// Gauss-Legendre integration
	/**
	 * The fixed-order Gauss-Legendre integration routines are provided for fast integration of 
	 * smooth functions with known polynomial order. The n-point Gauss-Legendre rule is exact for 
	 * polynomials of order 2n-1 or less. For example, these rules are useful when integrating basis 
	 * functions to form mass matrices for the Galerkin method. Unlike other numerical integration 
	 * routines within the library, these routines do not accept absolute or relative error bounds.
	 * 
	 * @param a
	 * @param b
	 * @param numPoints
	 * @return
	 */
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

	/**
	 * The routines in this section approximate an integral by the sum where f(x) is the function to 
	 * be integrated and w(x) is a weighting function. The n weights wi and nodes xi are carefully 
	 * chosen so that the result is exact when f(x) is a polynomial of degree 2n-1 or less. Once the 
	 * user chooses the order n and weighting function w(x), the weights wi and nodes xi can be 
	 * precomputed and used to efficiently evaluate integrals for any number of functions f(x).
	 * 
	 * @param a
	 * @param b
	 * @param numPoints
	 * @param type
	 * @param alpha
	 * @param beta
	 * @return
	 */
	public native double qfixed(double a, double b, int numPoints, int type, double alpha, double beta) ;
	public native double[] qfixedPoints(double a, double b, int numPoints, int type, double alpha, double beta) ;
	public native double[] qfixedWeights(double a, double b, int numPoints, int type, double alpha, double beta) ;

	// open PDF documentation
	public static void help() {
		try {
	        String inputPdf = "doc/gsl_integration.pdf";
	        Path tempOutput = Files.createTempFile("gsl_integration", ".pdf");
	        tempOutput.toFile().deleteOnExit();
	        try (InputStream is = SpecialFuncs.class.getClassLoader().getResourceAsStream(inputPdf)) {
	            Files.copy(is, tempOutput, StandardCopyOption.REPLACE_EXISTING);
	        }
	        Desktop.getDesktop().open(tempOutput.toFile());
			
		} catch (IOException e) {
			System.err.println("could not open PDF document...");
		}
	}
	
}
