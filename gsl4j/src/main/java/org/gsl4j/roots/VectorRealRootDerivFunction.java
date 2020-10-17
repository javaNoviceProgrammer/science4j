package org.gsl4j.roots;

import org.gsl4j.function.VectorMathFunction;

@FunctionalInterface
public interface VectorRealRootDerivFunction extends VectorMathFunction {

	double[] value(double... x) ;

}
