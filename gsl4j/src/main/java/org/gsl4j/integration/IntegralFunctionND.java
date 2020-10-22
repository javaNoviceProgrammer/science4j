package org.gsl4j.integration;

/**
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface IntegralFunctionND {

	/**
	 *
	 * @param x
	 * @return
	 */
	double value(double... x) ;
}
