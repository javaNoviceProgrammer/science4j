package org.gsl4j.function;

import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.diff.NumericalDiff;

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


	default MultiVariateMathFunction deriv(int varIndex, double h) {
		return x -> NumericalDiff.central(t -> {
			double[] y = x.clone();
			y[varIndex] = t ;
			return value(y) ;
		}, x[varIndex], h) ;
	}

	default MultiVariateMathFunction deriv(int varIndex) {
		return x -> NumericalDiff.central(t -> {
			double[] y = x.clone();
			y[varIndex] = t ;
			return value(y) ;
		}, x[varIndex], 0.1) ;
	}


}
