package org.gsl4j.function;

import org.gsl4j.complex.ComplexNumber;

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
	 * This method returns the {@link ComplexNumber} value of the function.
	 *
	 * @param z : {@link ComplexNumber} point at which the function is evaluated.
	 * @return {@link ComplexNumber} : the complex value of the function
	 */
	ComplexNumber value(ComplexNumber z) ;
}
