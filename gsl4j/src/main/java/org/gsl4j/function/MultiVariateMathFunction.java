package org.gsl4j.function;

/**
 * Represents a real-valued multi-variable function.
 *
 * @author Meisam
 * @since 1.0
 */
@FunctionalInterface
public interface MultiVariateMathFunction {
	double value(double... x) ;
}
