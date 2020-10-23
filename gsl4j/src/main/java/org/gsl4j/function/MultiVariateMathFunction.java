package org.gsl4j.function;

import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.diff.NumericalDiff;

/**
 * Represents a real-valued multi-variable function: f(x1,x2,x3,...)
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

	/*-------- first-order derivatives ----------*/

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

	/*-------- second-order derivatives ----------*/

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

	/*-------- addition ----------*/

	default MultiVariateMathFunction add(double v) { // this + v
		return x -> value(x) + v ;
	}
	default MultiVariateMathFunction addRev(double v) { // v + this
		return x -> v + value(x) ;
	}
	default MultiVariateMathFunction add(MultiVariateMathFunction v) { // this + v
		return x -> value(x) + v.value(x) ;
	}
	default MultiVariateMathFunction addRev(MultiVariateMathFunction v) { // v + this
		return x -> v.value(x) + value(x) ;
	}

	/*-------- subtraction ----------*/

	default MultiVariateMathFunction subtract(double v) { // this - v
		return x -> value(x) - v ;
	}
	default MultiVariateMathFunction subtractRev(double v) { // v - this
		return x -> v - value(x) ;
	}
	default MultiVariateMathFunction subtract(MultiVariateMathFunction v) { // this - v
		return x -> value(x) - v.value(x) ;
	}
	default MultiVariateMathFunction subtractRev(MultiVariateMathFunction v) { // v - this
		return x -> v.value(x) - value(x) ;
	}

	/*-------- Multiplication ----------*/

	default MultiVariateMathFunction multiply(double v) { // this * v
		return x -> value(x) * v ;
	}
	default MultiVariateMathFunction multiplyRev(double v) { // v * this
		return x -> v * value(x) ;
	}
	default MultiVariateMathFunction multiply(MultiVariateMathFunction v) { // this * v
		return x -> value(x) * v.value(x) ;
	}
	default MultiVariateMathFunction multiplyRev(MultiVariateMathFunction v) { // v * this
		return x -> v.value(x) * value(x) ;
	}

	/*-------- Division ----------*/

	default MultiVariateMathFunction divide(double v) { // this / v
		return x -> value(x) / v ;
	}
	default MultiVariateMathFunction divideRev(double v) { // v / this
		return x -> v / value(x) ;
	}
	default MultiVariateMathFunction divide(MultiVariateMathFunction v) { // this / v
		return x -> value(x) / v.value(x) ;
	}
	default MultiVariateMathFunction divideRev(MultiVariateMathFunction v) { // v / this
		return x -> v.value(x) / value(x) ;
	}

	/*-------- negation ----------*/

	default MultiVariateMathFunction negate() { // - this
		return x -> -value(x) ;
	}



}
