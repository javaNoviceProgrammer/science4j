package org.gsl4j.integration;

@FunctionalInterface
public interface IntegralFunctionND {
	double value(double... x) ;
}
