package org.gsl4j.function;

import org.gsl4j.complex.Complex;

@FunctionalInterface
public interface MultivariateComplexMathFunction {
	Complex value(Complex... z) ;
}
