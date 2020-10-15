package org.gsl4j.function;

/**
 * Represents a real-valued single-variable function f(x).
 *
 * @author Meisam
 * @since 1.0
 */
@FunctionalInterface
public interface MathFunction {

	/**
	 * This method returns the real value of the function.
	 *
	 * @param x : point at which the function is evaluated
	 * @return double : the value of function f(x)
	 */
	double value(double x) ;

}
