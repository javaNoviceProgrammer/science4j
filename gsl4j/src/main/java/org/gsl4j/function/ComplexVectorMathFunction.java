package org.gsl4j.function;

import org.gsl4j.complex.ComplexNumber;

/**
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface ComplexVectorMathFunction {

	/**
	 *
	 *
	 * @param z : array of {@link ComplexNumber}
	 * @return {@code ComplexNumber[]} : array of complex values of f(z[])
	 */
	ComplexNumber[] value(ComplexNumber... z) ;
}
