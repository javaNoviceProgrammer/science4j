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
	 * @param z
	 * @return
	 */
	ComplexNumber[] value(ComplexNumber... z) ;
}
