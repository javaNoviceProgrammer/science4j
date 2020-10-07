package org.gsl4j.function;

import org.gsl4j.complex.Complex;

/**
 * Represents a Complex-valued single-variable function.
 *
 * @author Meisam
 *
 */
@FunctionalInterface
public interface ComplexMathFunction {
	Complex value(Complex z) ;
}
