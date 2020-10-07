package org.gsl4j.function;

@FunctionalInterface
public interface VectorMathFunction {
	double[] value(double... x) ;
}
