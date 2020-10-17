package org.gsl4j.function;

import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealNumber;

/**
 * Represents a real-valued multi-variable function.
 *
 * @author Meisam
 * @since 1.0
 */
@FunctionalInterface
public interface MultiVariateMathFunction {

	double value(double... x) ;

	default RealNumber boxedValue(double... x) {
		return Real.of(value(x)) ;
	}
}
