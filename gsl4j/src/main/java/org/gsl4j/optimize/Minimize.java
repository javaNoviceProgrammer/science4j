package org.gsl4j.optimize;

import org.gsl4j.function.MathFunction;
import org.gsl4j.util.NativeLibraryLoader;

/**
 * The minimization algorithms described in this class require an initial interval which is guaranteed to contain a minimum—if a and b are the endpoints of the interval and x is an estimate of the minimum then f(a) > f(x) < f(b). This ensures that the function has at least one minimum somewhere in the interval. If a valid initial interval is used then these algorithm cannot fail, provided the function is well-behaved.
 *
 * @author Meisam
 *
 */
public class Minimize {

	static {
		NativeLibraryLoader.loadLibraries();
		initIDs();
	}

	private static native void initIDs() ;

	MathFunction func ;
	double xlower = -100.0 ;
	double xupper = 100.0 ;
	double xguess = 0.0 ;
	double absErr = 1e-10 ;
	double relErr = 1e-10 ;
	int maxNumberOfIterations = 1000 ;

	public Minimize(MathFunction func, double guess, double lower, double upper) {
		this.func = func ;
		this.xguess = guess ;
		this.xlower = lower ;
		this.xupper = upper ;
	}

	public Minimize(MathFunction func) {
		this.func = func ;
	}

	public void setInitialGuess(double guess) {
		this.xguess = guess ;
	}

	public void setBounds(double lower, double upper) {
		this.xlower = lower ;
		this.xupper = upper ;
	}

	/**
	 * The golden section algorithm is the simplest method of bracketing the minimum of a function. It is the slowest algorithm provided by the library, with linear convergence.
	 * <br>On each iteration, the algorithm first compares the subintervals from the endpoints to the current minimum. The larger subinterval is divided in a golden section (using the famous ratio (3-\sqrt 5)/2 \approx 0.3819660 and the value of the function at this new point is calculated. The new value is used with the constraint f(a') > f(x') < f(b') to a select new interval containing the minimum, by discarding the least useful point. This procedure can be continued indefinitely until the interval is sufficiently small. Choosing the golden section as the bisection ratio can be shown to provide the fastest convergence for this type of algorithm.
	 *
	 * @return
	 */
	public native double goldenSection() ;

	/**
	 * The Brent minimization algorithm combines a parabolic interpolation with the golden section algorithm. This produces a fast algorithm which is still robust.
	 * <br>The outline of the algorithm can be summarized as follows: on each iteration Brent’s method approximates the function using an interpolating parabola through three existing points. The minimum of the parabola is taken as a guess for the minimum. If it lies within the bounds of the current interval then the interpolating point is accepted, and used to generate a smaller interval. If the interpolating point is not accepted then the algorithm falls back to an ordinary golden section step. The full details of Brent’s method include some additional checks to improve convergence.
	 *
	 * @return
	 */
	public native double brent() ;

	/**
	 * This is a variant of Brent’s algorithm which uses the safeguarded step-length algorithm of Gill and Murray.
	 *
	 * @return
	 */
	public native double quadGolden() ;



}
