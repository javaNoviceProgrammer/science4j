package org.gsl4j.roots;

@FunctionalInterface
public interface RealRootDerivFunction {
	double[] value(double x) ;
}
