package func4j.roots;

import func4j.function.ComplexMathFunction;
import util4j.complex.Complex;
import util4j.complex.ComplexNumber;

public class ComplexRoot {
	
	ComplexNumber initialGuess ;
	ComplexMathFunction func ;
	MultiRoot rootFinder ;
	
	
	public ComplexRoot(ComplexMathFunction func, ComplexNumber initialGuess) {
		this.func = func ;
		this.initialGuess = initialGuess ;
		MultiRootFunction multiFunc = t -> {
			ComplexNumber z = func.value(t[0], t[1]) ;
			return new double[] {z.re(), z.im()} ;
		} ;
		this.rootFinder = new MultiRoot(2, multiFunc) ;
		this.rootFinder.setInitialGuess(initialGuess.re(), initialGuess.im());
	}
	
	public ComplexRoot(ComplexMathFunction func) {
		this.func = func ;
		this.initialGuess = Complex.ZERO ;
		MultiRootFunction multiFunc = t -> {
			ComplexNumber z = func.value(t[0], t[1]) ;
			return new double[] {z.re(), z.im()} ;
		} ;
		this.rootFinder = new MultiRoot(2, multiFunc) ;
		this.rootFinder.setInitialGuess(initialGuess.re(), initialGuess.im());
	}
	
	public ComplexRoot(ComplexMathFunction func, double guessRe, double guessIm) {
		this.func = func ;
		this.initialGuess = Complex.ofRect(guessRe, guessIm) ;
		MultiRootFunction multiFunc = t -> {
			ComplexNumber z = func.value(t[0], t[1]) ;
			return new double[] {z.re(), z.im()} ;
		} ;
		this.rootFinder = new MultiRoot(2, multiFunc) ;
		this.rootFinder.setInitialGuess(guessRe, guessIm);
	}
	
	public void setInitialGuess(ComplexNumber guess) {
		initialGuess = guess ;
		rootFinder.setInitialGuess(guess.re(), guess.im());
	}
	
	public void setInitialGuess(double re, double im) {
		initialGuess = Complex.ofRect(re, im) ;
		rootFinder.setInitialGuess(re, im) ;
	}
	
	public void setMaxNumberOfIterations(int iterations) {
		rootFinder.setMaxNumberOfIterations(iterations) ;
	}
	
	public void printInfo(boolean flag) {
		rootFinder.printInfo(flag);
	}
	
	public void setAbsoluteError(double absErr) {
		rootFinder.setAbsoluteError(absErr);
	}
	
	public void setRelativeError(double relErr) {
		rootFinder.setRelativeError(relErr);
	}
	
	public void setAbsErrForUniqueness(double absErr) {
		rootFinder.setAbsErrForUniqueness(absErr);
	}
	
	// ******** Algorithms using Derivatives *********
	
	
	//	1. gsl_multiroot_fdfsolver_hybridsj
	
	/**
	 * This is a modified version of Powell’s Hybrid method as implemented in the HYBRJ algorithm in MINPACK. Minpack was written by Jorge J. Moré, Burton S. Garbow and Kenneth E. Hillstrom. The Hybrid algorithm retains the fast convergence of Newton’s method but will also reduce the residual when Newton’s method is unreliable.
	 * @return
	 */	
	public ComplexNumber hybridsj() {
		return Complex.ofArray(rootFinder.hybridsj()) ;
	}
	
	// 	2. gsl_multiroot_fdfsolver_hybridj
	
	/**
	 * This algorithm is an unscaled version of {@link #hybridsj()}. The steps are controlled by a spherical trust region |x'-x| < delta , instead of a generalized region. This can be useful if the generalized region estimated by HYBRIDSJ is inappropriate.
	 * @return
	 */
	public ComplexNumber hybridj() {
		return Complex.ofArray(rootFinder.hybridj()) ;
	}
	
	// 	3. gsl_multiroot_fdfsolver_newton
	
	/**
	 * Newton’s Method is the standard root-polishing algorithm. The algorithm begins with an initial guess for the location of the solution. On each iteration a linear approximation to the function F is used to estimate the step which will zero all the components of the residual.
	 * @return
	 */
	public ComplexNumber newton() {
		return Complex.ofArray(rootFinder.newton()) ;
	}
	
	// 	4. gsl_multiroot_fdfsolver_gnewton
	
	/**
	 * This is a modified version of Newton’s method which attempts to improve global convergence by requiring every step to reduce the Euclidean norm of the residual, |f(x)|.
	 * @return
	 */
	public ComplexNumber gnewton() {
		return Complex.ofArray(rootFinder.gnewton()) ;
	}
	
	
	// ******** Algorithms without Derivatives ********
	
	
	// 	1. gsl_multiroot_fsolver_hybrids
	
	/**
	 * This is a version of the Hybrid algorithm which replaces calls to the Jacobian function by its finite difference approximation. The finite difference approximation is computed using gsl_multiroots_fdjac() with a relative step size of GSL_SQRT_DBL_EPSILON. Note that this step size will not be suitable for all problems.
	 * @return
	 */
	public ComplexNumber hybrids() {
		return Complex.ofArray(rootFinder.hybrids()) ;
	}
	
	// 	2. gsl_multiroot_fsolver_hybrid
	
	/**
	 * This is a finite difference version of the Hybrid algorithm without internal scaling.
	 * @return
	 */
	public ComplexNumber hybrid() {
		return Complex.ofArray(rootFinder.hybrid()) ;
	}
	
	// 	3. gsl_multiroot_fsolver_dnewton
	
	/**
	 * The discrete Newton algorithm is the simplest method of solving a multidimensional system. It uses the Newton iteration
	 * where the Jacobian matrix J is approximated by taking finite differences of the function f. 
	 * <p>
	 * The order of convergence of Newton’s algorithm is quadratic, but the finite differences require n2 function evaluations on each iteration. The algorithm may become unstable if the finite differences are not a good approximation to the true derivatives.
	 * 
	 * @return
	 */
	public ComplexNumber dnewton() {
		return Complex.ofArray(rootFinder.dnewton()) ;
	}
	
	//	4. gsl_multiroot_fsolver_broyden
	
	/**
	 * The Broyden algorithm is a version of the discrete Newton algorithm which attempts to avoid the expen- sive update of the Jacobian matrix on each iteration. The changes to the Jacobian are also approximated, using a rank-1 update,
	 * On the first iteration the inverse Jacobian is estimated using finite differences, as in the discrete Newton algorithm.
	 * <p>
	 * This approximation gives a fast update but is unreliable if the changes are not small, and the estimate of the inverse Jacobian becomes worse as time passes. The algorithm has a tendency to become unstable unless it starts close to the root. The Jacobian is refreshed if this instability is detected (consult the source for details).
	 * <p>
	 * This algorithm is included only for demonstration purposes, and is not recommended for serious use.
	 * 
	 * @return
	 */
	public ComplexNumber broyden() {
		return Complex.ofArray(rootFinder.broyden()) ;
	}
	
	
	
	
	
	
	
	

}
