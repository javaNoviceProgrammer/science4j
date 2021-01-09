package func4j.function;

import java.util.Arrays;

import func4j.diff.NumericalDiff;
import util4j.complex.Real;
import util4j.complex.RealNumber;


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
	 * @return {@code double} : the value of function f(x)
	 */
	double value(double x) ;

	/**
	 *
	 * @param x : a boxed {@link RealNumber}
	 * @return {@code double} : a double value for f(x)
	 */
	default double value(RealNumber x) {
		return value(x.re()) ;
	}

	/**
	 * This method provides boxed representation of the real value that the function
	 * returns.
	 *
	 * @param x : {@code double} value
	 * @return {@link RealNumber} : a boxed real number for f(x)
	 */
	default RealNumber boxedValue(double x) {
		return Real.of(value(x)) ;
	}

	/**
	 * This method provides boxed representation of the real value that the function
	 * returns when a boxed real value is passed to the function.
	 *
	 * @param x : a boxed {@link RealNumber}
	 * @return {@link RealNumber} : a boxed real number for f(x)
	 */
	default RealNumber boxedValue(RealNumber x) {
		return boxedValue(x.re()) ;
	}

	default double[] value(double[] x) {
		return Arrays.stream(x).map(this::value).toArray() ;
	}

	default MathFunction compose(MathFunction func) {
		return x -> value(func.value(x)) ;
	}

	default MathFunction deriv() {
		return x -> NumericalDiff.central(this, x, 0.1) ;
	}

	default MathFunction deriv2() {
		return x -> NumericalDiff.central(this.deriv(), x, 0.1) ;
	}




	/*-------- addition ----------*/

	default MathFunction add(double v) { // this + v
		return x -> value(x) + v ;
	}
	default MathFunction addRev(double v) { // v + this
		return x -> v + value(x) ;
	}
	default MathFunction add(MathFunction v) { // this + v
		return x -> value(x) + v.value(x) ;
	}
	default MathFunction addRev(MathFunction v) { // v + this
		return x -> v.value(x) + value(x) ;
	}

	/*-------- subtraction ----------*/

	default MathFunction subtract(double v) { // this - v
		return x -> value(x) - v ;
	}
	default MathFunction subtractRev(double v) { // v - this
		return x -> v - value(x) ;
	}
	default MathFunction subtract(MathFunction v) { // this - v
		return x -> value(x) - v.value(x) ;
	}
	default MathFunction subtractRev(MathFunction v) { // v - this
		return x -> v.value(x) - value(x) ;
	}

	/*-------- Multiplication ----------*/

	default MathFunction multiply(double v) { // this * v
		return x -> value(x) * v ;
	}
	default MathFunction multiplyRev(double v) { // v * this
		return x -> v * value(x) ;
	}
	default MathFunction multiply(MathFunction v) { // this * v
		return x -> value(x) * v.value(x) ;
	}
	default MathFunction multiplyRev(MathFunction v) { // v * this
		return x -> v.value(x) * value(x) ;
	}

	/*-------- Division ----------*/

	default MathFunction divide(double v) { // this / v
		return x -> value(x) / v ;
	}
	default MathFunction divideRev(double v) { // v / this
		return x -> v / value(x) ;
	}
	default MathFunction divide(MathFunction v) { // this / v
		return x -> value(x) / v.value(x) ;
	}
	default MathFunction divideRev(MathFunction v) { // v / this
		return x -> v.value(x) / value(x) ;
	}

	/*-------- negation ----------*/

	default MathFunction negate() { // - this
		return x -> -value(x) ;
	}


}
