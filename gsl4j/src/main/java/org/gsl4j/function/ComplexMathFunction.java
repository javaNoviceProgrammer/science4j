package org.gsl4j.function;

import org.gsl4j.complex.Complex;

/**
 * Represents a Complex-valued single-variable function f(z=x+jy).
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface ComplexMathFunction {

	/**
	 * This method returns the {@link Complex} value of the function.
	 *
	 * @param z : {@link Complex} point at which the function is evaluated.
	 * @return {@link Complex} : the complex value of the function
	 */
	Complex value(Complex z) ;
}
