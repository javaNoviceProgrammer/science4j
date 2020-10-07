package org.gsl4j.function;

/**
 * Represents a real-valued multi-variable function.
 *
 * @author Meisam
 *
 */
@FunctionalInterface
public interface MultiVariateMathFunction {
	double value(double... x) ;
}
