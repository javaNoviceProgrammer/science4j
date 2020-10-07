package org.gsl4j.roots;

@FunctionalInterface
public interface VectorRealRootFunction {
	double[] value(double... x) ;
}
