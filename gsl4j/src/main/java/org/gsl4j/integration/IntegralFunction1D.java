package org.gsl4j.integration;

import org.gsl4j.function.MathFunction;

@FunctionalInterface
public interface IntegralFunction1D extends MathFunction {

	double value(double x) ;

}
