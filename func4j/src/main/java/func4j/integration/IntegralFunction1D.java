package func4j.integration;

import func4j.function.MathFunction;

/**
 * This interface represents a real-valued double-precision function as the integrand.
 * This interface extends {@link MathFunction}
 *
 * @author Meisam
 * @since 1.0
 * @see MathFunction
 *
 */
@FunctionalInterface
public interface IntegralFunction1D extends MathFunction {

	/**
	 * Returns the real-value of the function.
	 *
	 * @see {@link MathFunction#value(double)}
	 */
	double value(double x) ;

}
