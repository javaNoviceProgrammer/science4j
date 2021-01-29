package func4j.roots;

import func4j.natives.NativeLibraryLoader;

public class MultiRoot {
	
	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
		initFieldIDs() ;
	}

	private static native void initFieldIDs() ;
	
	double absErr = 1e-7 ;
	double relErr = 1e-10 ;
	double absErrUniqueness = 1e-5 ;
	int maxNumberOfIterations = 100 ;
	boolean printInfo = false ;
	
	int dim ; // must be NxN system of equation
	double[] initialGuess ;
	MultiRootFunction func ;
	MultiRootDerivFunction jacob ;
	
	public MultiRoot(int dim, MultiRootFunction func, MultiRootDerivFunction jacob, double... initialGuess) {
		this.dim = dim ;
		this.func = func ;
		this.jacob = jacob ;
		this.initialGuess = initialGuess ;
	}
	
	public MultiRoot(int dim, MultiRootFunction func, MultiRootDerivFunction jacob) {
		this.dim = dim ;
		this.func = func ;
		this.jacob = jacob ;
		initialGuess = new double[dim] ;
	}
	
	public MultiRoot(int dim, MultiRootFunction func) {
		this.dim = dim ;
		this.func = func ;
		this.jacob = null ; // numerically initialize jacobian??
		initialGuess = new double[dim] ;
	}
	
	public void setInitialGuess(double... guess) {
		initialGuess = guess ;
	}
	
	public void setMaxNumberOfIterations(int iterations) {
		maxNumberOfIterations = iterations ;
	}
	
	public void printInfo(boolean flag) {
		printInfo = flag ;
	}
	
	public void setAbsoluteError(double absErr) {
		this.absErr = absErr ;
	}
	
//	public void setRelativeError(double relErr) {
//		this.relErr = relErr ;
//	}
//	
//	public void setAbsErrForUniqueness(double absErr) {
//		this.absErrUniqueness = absErr ;
//	}
	
	
	// ******** Algorithms using Derivatives *********
	
	
	//	1. gsl_multiroot_fdfsolver_hybridsj
	
	public native double[] hybridsj() ;
	
	// 	2. gsl_multiroot_fdfsolver_hybridj
	
	public native double[] hybridj() ;
	
	// 	3. gsl_multiroot_fdfsolver_newton
	
	public native double[] newton() ;
	
	// 	4. gsl_multiroot_fdfsolver_gnewton
	
	public native double[] gnewton() ;
	
	
	// ******** Algorithms without Derivatives ********
	
	
	// 	1. gsl_multiroot_fsolver_hybrids
	
	/**
	 * This is a version of the Hybrid algorithm which replaces calls to the Jacobian function by its finite difference approximation. The finite difference approximation is computed using gsl_multiroots_fdjac() with a relative step size of GSL_SQRT_DBL_EPSILON. Note that this step size will not be suitable for all problems.
	 * @return
	 */
	public native double[] hybrids() ;
	
	// 	2. gsl_multiroot_fsolver_hybrid
	
	/**
	 * This is a finite difference version of the Hybrid algorithm without internal scaling.
	 * @return
	 */
	public native double[] hybrid() ;
	
	// 	3. gsl_multiroot_fsolver_dnewton
	
	/**
	 * The discrete Newton algorithm is the simplest method of solving a multidimensional system. It uses the Newton iteration
	 * where the Jacobian matrix J is approximated by taking finite differences of the function f. 
	 * <p>
	 * The order of convergence of Newton’s algorithm is quadratic, but the finite differences require n2 function evaluations on each iteration. The algorithm may become unstable if the finite differences are not a good approximation to the true derivatives.
	 * 
	 * @return
	 */
	public native double[] dnewton() ;
	
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
	public native double[] broyden() ;
	

}
