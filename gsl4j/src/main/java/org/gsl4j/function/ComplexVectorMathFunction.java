package org.gsl4j.function;

import org.gsl4j.complex.Complex;

@FunctionalInterface
public interface ComplexVectorMathFunction {
	Complex[] value(Complex... z) ;
}
