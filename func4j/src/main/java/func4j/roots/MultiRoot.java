package func4j.roots;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import func4j.natives.NativeLibraryLoader;
import func4j.special.SpecialFuncs;

/**
 * This chapter describes functions for multidimensional root-finding (solving nonlinear systems with {@code n} equations in {@code n} unknowns). The library provides low level components for a variety of iterative solvers and convergence tests. These can be combined by the user to achieve the desired solution, with full access to the intermediate steps of the iteration. Each class of methods uses the same framework, so that you can switch between solvers at runtime without needing to recompile your program. Each instance of a solver keeps track of its own state, allowing the solvers to be used in multi-threaded programs. The solvers are based on the original Fortran library MINPACK.
 * <p>
 * The header file {@code gsl_multiroots.h} contains prototypes for the multidimensional root finding functions and related declarations.
 * 
 * @author Meisam
 *
 */
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
	
	public enum StopTest {
		Residual,
		Delta ;
	}
	
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
		initialGuess = new double[dim] ;
		// numerically initialize the jacobian
		this.jacob = t -> {
			double[][] jac = new double[dim][dim] ;
			for(int i=0; i<dim; i++) 
				for(int j=0; j<dim; j++) {
					jac[i][j] = func.get(i).deriv(j).value(t) ;
				}
			return jac ;
		} ;
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
	
	public void setRelativeError(double relErr) {
		this.relErr = relErr ;
	}
	
	public void setAbsErrForUniqueness(double absErr) {
		this.absErrUniqueness = absErr ;
	}
	
	// ******** Algorithms using Derivatives *********
	
	
	//	1. gsl_multiroot_fdfsolver_hybridsj
	
	/**
	 * This is a modified version of Powell’s Hybrid method as implemented in the HYBRJ algorithm in MINPACK. Minpack was written by Jorge J. Moré, Burton S. Garbow and Kenneth E. Hillstrom. The Hybrid algorithm retains the fast convergence of Newton’s method but will also reduce the residual when Newton’s method is unreliable.
	 * @return
	 */
	public native double[] hybridsj() ;
	
	// 	2. gsl_multiroot_fdfsolver_hybridj
	
	/**
	 * This algorithm is an unscaled version of {@link #hybridsj()}. The steps are controlled by a spherical trust region |x'-x| < delta , instead of a generalized region. This can be useful if the generalized region estimated by HYBRIDSJ is inappropriate.
	 * @return
	 */
	public native double[] hybridj() ;
	
	// 	3. gsl_multiroot_fdfsolver_newton
	
	/**
	 * Newton’s Method is the standard root-polishing algorithm. The algorithm begins with an initial guess for the location of the solution. On each iteration a linear approximation to the function F is used to estimate the step which will zero all the components of the residual.
	 * @return
	 */
	public native double[] newton() ;
	
	// 	4. gsl_multiroot_fdfsolver_gnewton
	
	/**
	 * This is a modified version of Newton’s method which attempts to improve global convergence by requiring every step to reduce the Euclidean norm of the residual, |f(x)|.
	 * @return
	 */
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
	
	
	// open PDF documentation
	public static void help() {
		try {
	        String inputPdf = "doc/gsl_multi_root.pdf";
	        Path tempOutput = Files.createTempFile("gsl_multi_root", ".pdf");
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
