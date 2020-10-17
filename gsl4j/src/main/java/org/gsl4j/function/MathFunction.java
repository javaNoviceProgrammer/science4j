package org.gsl4j.function;

import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealNumber;

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

}
