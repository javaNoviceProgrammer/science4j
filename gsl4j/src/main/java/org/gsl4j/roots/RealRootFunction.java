package org.gsl4j.roots;

import org.gsl4j.function.MathFunction;

@FunctionalInterface
public interface RealRootFunction extends MathFunction {

	double value(double x) ;

}
