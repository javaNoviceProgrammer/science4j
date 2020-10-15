package org.gsl4j.function;

import org.gsl4j.complex.Complex;

/**
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface MultivariateComplexMathFunction {
	Complex value(Complex... z) ;
}
