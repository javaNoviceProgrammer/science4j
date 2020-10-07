package org.gsl4j.roots;

import java.util.ArrayList;
import java.util.List;

import org.gsl4j.diff.NumericalDiff;
import org.gsl4j.function.MathFunction;
import org.gsl4j.util.NativeLibraryLoader;

/**
 * This class describes routines for finding roots of arbitrary one-dimensional functions. The library provides low level components for a variety of iterative solvers and convergence tests. These can be combined by the user to achieve the desired solution, with full access to the intermediate steps of the iteration. Each class of methods uses the same framework, so that you can switch between solvers at runtime without needing to recompile your program. Each instance of a solver keeps track of its own state, allowing the solvers to be used in multi-threaded programs.
 * <br>
 * The header file {@code gsl_roots.h} contains prototypes for the root finding functions and related declarations.
 *
 * @author Meisam
 *
 */
public class RealRoot {

	static {
		NativeLibraryLoader.loadLibraries();
		initFieldIDs() ;
	}

	private static native void initFieldIDs() ;

	double absErr = 1e-10 ;
	double relErr = 1e-10 ;
	int maxNumberOfIterations = 100 ;
	RealRootFunction func ;
	RealRootDerivFunction derivFunc ;

	/**
	 * One-dimensional root finding algorithms can be divided into two classes, root bracketing and root polishing. Algorithms which proceed by bracketing a root are guaranteed to converge. Bracketing algorithms begin with a bounded region known to contain a root. The size of this bounded region is reduced, iteratively, until it encloses the root to a desired tolerance. This provides a rigorous error estimate for the location of the root.
	 * <br><br>
	 * The technique of root polishing attempts to improve an initial guess to the root. These algorithms converge only if started “close enough” to a root, and sacrifice a rigorous error bound for speed. By approximating the behavior of a function in the vicinity of a root they attempt to find a higher order improvement of an initial guess. When the behavior of the function is compatible with the algorithm and a good initial guess is available a polishing algorithm can provide rapid convergence.
	 */
	public RealRoot() {

	}

	/**
	 *
	 * One-dimensional root finding algorithms can be divided into two classes, root bracketing and root polishing. Algorithms which proceed by bracketing a root are guaranteed to converge. Bracketing algorithms begin with a bounded region known to contain a root. The size of this bounded region is reduced, iteratively, until it encloses the root to a desired tolerance. This provides a rigorous error estimate for the location of the root.
	 * <br><br>
	 * The technique of root polishing attempts to improve an initial guess to the root. These algorithms converge only if started “close enough” to a root, and sacrifice a rigorous error bound for speed. By approximating the behavior of a function in the vicinity of a root they attempt to find a higher order improvement of an initial guess. When the behavior of the function is compatible with the algorithm and a good initial guess is available a polishing algorithm can provide rapid convergence.
	 *
	 * @param func : RealRootFunction to be used
	 */
	public RealRoot(RealRootFunction func) {
		this.func = func ;
		this.derivFunc = t -> new double[] {func.value(t), NumericalDiff.central(func, t, 0.1)} ;
	}

	/**
	 *
	 * One-dimensional root finding algorithms can be divided into two classes, root bracketing and root polishing. Algorithms which proceed by bracketing a root are guaranteed to converge. Bracketing algorithms begin with a bounded region known to contain a root. The size of this bounded region is reduced, iteratively, until it encloses the root to a desired tolerance. This provides a rigorous error estimate for the location of the root.
	 * <br><br>
	 * The technique of root polishing attempts to improve an initial guess to the root. These algorithms converge only if started “close enough” to a root, and sacrifice a rigorous error bound for speed. By approximating the behavior of a function in the vicinity of a root they attempt to find a higher order improvement of an initial guess. When the behavior of the function is compatible with the algorithm and a good initial guess is available a polishing algorithm can provide rapid convergence.
	 *
	 * @param func : MathFunction to be used
	 */
	public RealRoot(MathFunction func) {
		this.func = t -> func.value(t) ;
		this.derivFunc = t -> new double[] {func.value(t), NumericalDiff.central(func, t, 0.1)} ; ;
	}

	/**
	 *
	 * One-dimensional root finding algorithms can be divided into two classes, root bracketing and root polishing. Algorithms which proceed by bracketing a root are guaranteed to converge. Bracketing algorithms begin with a bounded region known to contain a root. The size of this bounded region is reduced, iteratively, until it encloses the root to a desired tolerance. This provides a rigorous error estimate for the location of the root.
	 * <br><br>
	 * The technique of root polishing attempts to improve an initial guess to the root. These algorithms converge only if started “close enough” to a root, and sacrifice a rigorous error bound for speed. By approximating the behavior of a function in the vicinity of a root they attempt to find a higher order improvement of an initial guess. When the behavior of the function is compatible with the algorithm and a good initial guess is available a polishing algorithm can provide rapid convergence.
	 *
	 * @param func : RealRootDerivFunction to be used
	 */
	public RealRoot(RealRootDerivFunction func) {
		this.func = t -> func.value(t)[0] ;
		this.derivFunc = func ;
	}

	public void setFunction(RealRootFunction func) {
		this.func = func ;
		this.derivFunc = t -> new double[] {func.value(t), NumericalDiff.central(func, t, 0.1)} ; ;
	}

	public void setDerivFunction(RealRootDerivFunction derivfunc) {
		this.func = t -> derivfunc.value(t)[0] ;
		this.derivFunc = derivfunc ;
	}

	// Root Bracketing Algorithms (use func)

	/**
	 * The bisection algorithm is the simplest method of bracketing the roots of a function. It is the slowest algorithm provided by the library, with linear convergence.
	 * <br>On each iteration, the interval is bisected and the value of the function at the midpoint is calculated. The sign of this value is used to determine which half of the interval does not contain a root. That half is discarded to give a new, smaller interval containing the root. This procedure can be continued indefinitely until the interval is sufficiently small.
	 * <br>At any time the current estimate of the root is taken as the midpoint of the interval.
	 *
	 * @param start : start of the bracket interval
	 * @param end : end of the bracket interval
	 * @return The real root
	 */
	public native double bisection(double start, double end) ;

	/**
	 * The false position algorithm is a method of finding roots based on linear interpolation. Its convergence is linear, but it is usually faster than bisection.
	 * <br>On each iteration a line is drawn between the endpoints (a,f(a)) and (b,f(b)) and the point where this line crosses the x-axis taken as a “midpoint”. The value of the function at this point is calculated and its sign is used to determine which side of the interval does not contain a root. That side is discarded to give a new, smaller interval containing the root. This procedure can be continued indefinitely until the interval is sufficiently small.
	 * <br>The best estimate of the root is taken from the linear interpolation of the interval on the current iteration.
	 *
	 * @param start : start of the bracket interval
	 * @param end : end of the bracket interval
	 * @return The real root
	 */
	public native double falsepos(double start, double end) ;

	/**
	 * The Brent-Dekker method (referred to here as Brent’s method) combines an interpolation strategy with the bisection algorithm. This produces a fast algorithm which is still robust.
	 * <br>On each iteration Brent’s method approximates the function using an interpolating curve. On the first iteration this is a linear interpolation of the two endpoints. For subsequent iterations the algorithm uses an inverse quadratic fit to the last three points, for higher accuracy. The intercept of the interpolating curve with the x-axis is taken as a guess for the root. If it lies within the bounds of the current interval then the interpolating point is accepted, and used to generate a smaller interval. If the interpolating point is not accepted then the algorithm falls back to an ordinary bisection step.
	 * <br>The best estimate of the root is taken from the most recent interpolation or bisection.
	 *
	 * @param start : start of the bracket interval
	 * @param end : end of the bracket interval
	 * @return The real root
	 */
	public native double brent(double start, double end) ;

	/**
	 * Same as {@link #bisection(double, double)} but returns {@code Double.NaN} if no root was found.
	 *
	 * @param start : start of the bracket interval
	 * @param end : end of the bracket interval
	 * @return The real root or {@code Double.NaN}
	 */
	public double bisectionSafe(double start, double end) {
		if(Math.abs(func.value(start))<absErr)
			return start ;
		else if(Math.abs(func.value(end))<absErr)
			return end ;
		else if(func.value(start)>0.0 && func.value(end)>0.0)
			return Double.NaN ;
		else if(func.value(start)<0.0 && func.value(end)<0.0)
			return Double.NaN ;
		else
			return bisection(start, end) ;
	}

	/**
	 * Same as {@link #falsepos(double, double)} but returns {@code Double.NaN} if no root was found.
	 *
	 * @param start : start of the bracket interval
	 * @param end : end of the bracket interval
	 * @return The real root or {@code Double.NaN}
	 */
	public double falseposSafe(double start, double end) {
		if(Math.abs(func.value(start))<absErr)
			return start ;
		else if(Math.abs(func.value(end))<absErr)
			return end ;
		else if(func.value(start)>0.0 && func.value(end)>0.0)
			return Double.NaN ;
		else if(func.value(start)<0.0 && func.value(end)<0.0)
			return Double.NaN ;
		else
			return falsepos(start, end) ;
	}

	/**
	 * Same as {@link #brent(double, double)} but returns {@code Double.NaN} if no root was found.
	 *
	 * @param start : start of the bracket interval
	 * @param end : end of the bracket interval
	 * @return The real root or {@code Double.NaN}
	 */
	public double brentSafe(double start, double end) {
		if(Math.abs(func.value(start))<absErr)
			return start ;
		else if(Math.abs(func.value(end))<absErr)
			return end ;
		else if(func.value(start)>0.0 && func.value(end)>0.0)
			return Double.NaN ;
		else if(func.value(start)<0.0 && func.value(end)<0.0)
			return Double.NaN ;
		else
			return brent(start, end) ;
	}

	/**
	 * Uses {@link #bisectionSafe(double, double)} to find all of the real roots in a given interval by using {@code N} subintervals.
	 *
	 * @param start : start of the bracket interval
	 * @param end : end of the bracket interval
	 * @param subIntervals : number of subintervals to search the roots in them
	 * @return A list of all real roots or an empty list.
	 */
	public List<Double> bisection(double start, double end, int subIntervals) {
		List<Double> roots = new ArrayList<>() ;
		double[] points = new double[subIntervals+1] ;
		double dx = (end-start)/subIntervals ;
		for(int i=0; i<points.length; i++) {
			points[i] = start + i*dx ;
		}
		for(int i=0; i<subIntervals; i++) {
			double potentialRoot = bisectionSafe(points[i], points[i+1]) ;
			if(!Double.isNaN(potentialRoot)){
				boolean repeated = false ;
				for(int j=0;j<roots.size(); j++){
					if(Math.abs(potentialRoot-roots.get(j))<absErr)
						repeated = true ;
				}
				if(!repeated)
					roots.add(potentialRoot) ;
			}
		}
		return roots ;
	}

	/**
	 * Uses {@link #falseposSafe(double, double)} to find all of the real roots in a given interval by using {@code N} subintervals.
	 *
	 * @param start : start of the bracket interval
	 * @param end : end of the bracket interval
	 * @param subIntervals : number of subintervals to search the roots in them
	 * @return A list of all real roots or an empty list.
	 */
	public List<Double> falsepos(double start, double end, int subIntervals) {
		List<Double> roots = new ArrayList<>() ;
		double[] points = new double[subIntervals+1] ;
		double dx = (end-start)/subIntervals ;
		for(int i=0; i<points.length; i++) {
			points[i] = start + i*dx ;
		}
		for(int i=0; i<subIntervals; i++) {
			double potentialRoot = falseposSafe(points[i], points[i+1]) ;
			if(!Double.isNaN(potentialRoot)){
				boolean repeated = false ;
				for(int j=0;j<roots.size(); j++){
					if(Math.abs(potentialRoot-roots.get(j))<absErr)
						repeated = true ;
				}
				if(!repeated)
					roots.add(potentialRoot) ;
			}
		}
		return roots ;
	}

	/**
	 * Uses {@link #brentSafe(double, double)} to find all of the real roots in a given interval by using {@code N} subintervals.
	 *
	 * @param start : start of the bracket interval
	 * @param end : end of the bracket interval
	 * @param subIntervals : number of subintervals to search the roots in them
	 * @return A list of all real roots or an empty list.
	 */
	public List<Double> brent(double start, double end, int subIntervals) {
		List<Double> roots = new ArrayList<>() ;
		double[] points = new double[subIntervals+1] ;
		double dx = (end-start)/subIntervals ;
		for(int i=0; i<points.length; i++) {
			points[i] = start + i*dx ;
		}
		for(int i=0; i<subIntervals; i++) {
			double potentialRoot = brentSafe(points[i], points[i+1]) ;
			if(!Double.isNaN(potentialRoot)){
				boolean repeated = false ;
				for(int j=0;j<roots.size(); j++){
					if(Math.abs(potentialRoot-roots.get(j))<absErr)
						repeated = true ;
				}
				if(!repeated)
					roots.add(potentialRoot) ;
			}
		}
		return roots ;
	}

	// Root Finding Algorithms using Derivatives (use derivFunc)

	/**
	 * Newton’s Method is the standard root-polishing algorithm. The algorithm begins with an initial guess for the location of the root. On each iteration, a line tangent to the function f is drawn at that position. The point where this line crosses the x-axis becomes the new guess. The iteration is defined by the following sequence,
	 * <br>x_{i+1} = x_i - {f(x_i) \over f'(x_i)}
	 * <br>Newton’s method converges quadratically for single roots, and linearly for multiple roots.
	 *
	 * @param estimate : initial estimation of the root
	 * @return The real root
	 */
	public native double newton(double estimate) ;

	/**
	 *The secant method is a simplified version of Newton’s method which does not require the computation of the derivative on every step.
	 *<br> On its first iteration the algorithm begins with Newton’s method, using the derivative to compute a first step,
	 *<br> x_1 = x_0 - {f(x_0) \over f'(x_0)}
	 *<br> Subsequent iterations avoid the evaluation of the derivative by replacing it with a numerical estimate, the slope of the line through the previous two points,
	 *<br> x_{i+1} = x_i - {f(x_i) \over f'_{est}}~\hbox{where}~ f'_{est} =  {f(x_{i}) - f(x_{i-1}) \over x_i - x_{i-1}}
	 *<br> When the derivative does not change significantly in the vicinity of the root the secant method gives a useful saving. Asymptotically the secant method is faster than Newton’s method whenever the cost of evaluating the derivative is more than 0.44 times the cost of evaluating the function itself. As with all methods of computing a numerical derivative the estimate can suffer from cancellation errors if the separation of the points becomes too small.
	 *<br> On single roots, the method has a convergence of order (1 + \sqrt5)/2 (approximately 1.62). It converges linearly for multiple roots.
	 *
	 * @param estimate : initial estimation of the root
	 * @return The real root
	 */
	public native double secant(double estimate) ;

	/**
	 * The Steffenson Method 1 provides the fastest convergence of all the routines. It combines the basic Newton algorithm with an Aitken “delta-squared” acceleration. If the Newton iterates are x_i then the acceleration procedure generates a new sequence R_i,
	 * <br> R_i = x_i - {(x_{i+1} - x_i)^2 \over (x_{i+2} - 2 x_{i+1} + x_i)}
	 * <br> which converges faster than the original sequence under reasonable conditions. The new sequence requires three terms before it can produce its first value so the method returns accelerated values on the second and subsequent iterations. On the first iteration it returns the ordinary Newton estimate. The Newton iterate is also returned if the denominator of the acceleration term ever becomes zero.
	 * <br> As with all acceleration procedures this method can become unstable if the function is not well-behaved.
	 *
	 * @param estimate : initial estimation of the root
	 * @return The real root
	 */
	public native double steffenson(double estimate) ;


	/**
	 * Same as {@link #newton(double)} but returns {@code Double.NaN} if no root was found.
	 *
	 * @param estimate : initial estimation of the root
	 * @return The real root or {@code Double.NaN}
	 */
	public double newtonSafe(double estimate) {
		if(Math.abs(func.value(estimate))<absErr)
			return estimate ;
		else {
			double root = newton(estimate) ;
			if(Math.abs(func.value(root))<absErr)
				return root ;
			else
				return Double.NaN ;
		}
	}

	public double secantSafe(double estimate) {
		if(Math.abs(func.value(estimate))<absErr)
			return estimate ;
		else {
			double root = secant(estimate) ;
			if(Math.abs(func.value(root))<absErr)
				return root ;
			else
				return Double.NaN ;
		}
	}

	public double steffensonSafe(double estimate) {
		if(Math.abs(func.value(estimate))<absErr)
			return estimate ;
		else {
			double root = steffenson(estimate) ;
			if(Math.abs(func.value(root))<absErr)
				return root ;
			else
				return Double.NaN ;
		}
	}

	public List<Double> newton(double start, double end, int subIntervals) {
		List<Double> roots = new ArrayList<>() ;
		double[] points = new double[subIntervals+1] ;
		double dx = (end-start)/subIntervals ;
		for(int i=0; i<points.length; i++) {
			points[i] = start + i*dx ;
		}
		for(int i=0; i<subIntervals; i++) {
			double potentialRoot = newtonSafe(0.5*(points[i]+points[i+1])) ;
			if(!Double.isNaN(potentialRoot)){
				boolean repeated = false ;
				for(int j=0;j<roots.size(); j++){
					if(Math.abs(potentialRoot-roots.get(j))<absErr)
						repeated = true ;
				}
				if(!repeated)
					roots.add(potentialRoot) ;
			}
		}
		return roots ;
	}

	public List<Double> secant(double start, double end, int subIntervals) {
		List<Double> roots = new ArrayList<>() ;
		double[] points = new double[subIntervals+1] ;
		double dx = (end-start)/subIntervals ;
		for(int i=0; i<points.length; i++) {
			points[i] = start + i*dx ;
		}
		for(int i=0; i<subIntervals; i++) {
			double potentialRoot = secantSafe(0.5*(points[i]+points[i+1])) ;
			if(!Double.isNaN(potentialRoot)){
				boolean repeated = false ;
				for(int j=0;j<roots.size(); j++){
					if(Math.abs(potentialRoot-roots.get(j))<absErr)
						repeated = true ;
				}
				if(!repeated)
					roots.add(potentialRoot) ;
			}
		}
		return roots ;
	}

	public List<Double> steffenson(double start, double end, int subIntervals) {
		List<Double> roots = new ArrayList<>() ;
		double[] points = new double[subIntervals+1] ;
		double dx = (end-start)/subIntervals ;
		for(int i=0; i<points.length; i++) {
			points[i] = start + i*dx ;
		}
		for(int i=0; i<subIntervals; i++) {
			double potentialRoot = steffensonSafe(0.5*(points[i]+points[i+1])) ;
			if(!Double.isNaN(potentialRoot)){
				boolean repeated = false ;
				for(int j=0;j<roots.size(); j++){
					if(Math.abs(potentialRoot-roots.get(j))<absErr)
						repeated = true ;
				}
				if(!repeated)
					roots.add(potentialRoot) ;
			}
		}
		return roots ;
	}


}
