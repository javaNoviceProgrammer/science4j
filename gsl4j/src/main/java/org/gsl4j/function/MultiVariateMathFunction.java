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

	default MultiVariateMathFunction deriv2(int varIndex, double h) {
		return this.deriv(varIndex, h).deriv(varIndex, h) ;
	}

	default MultiVariateMathFunction deriv2(int varIndex) {
		return this.deriv(varIndex).deriv(varIndex) ;
	}

	default MultiVariateMathFunction deriv2(int var1Index, int var2Index, double h) {
		return this.deriv(var1Index, h).deriv(var2Index, h) ;
	}

	default MultiVariateMathFunction deriv2(int var1Index, int var2Index) {
		return this.deriv(var1Index).deriv(var2Index) ;
	}


}
