package org.gsl4j.function;


/**
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface VectorMathFunction {
	double[] value(double... x) ;
}
