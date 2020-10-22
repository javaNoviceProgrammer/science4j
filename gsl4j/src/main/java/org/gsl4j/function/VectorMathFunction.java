package org.gsl4j.function;


/**
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface VectorMathFunction {

	/**
	 *
	 * @param x
	 * @return
	 */
	double[] value(double... x) ;


	default MultiVariateMathFunction at(int k) {
		return x -> value(x)[k] ;
	}

	default MultiVariateMathFunction get(int k) {
		return x -> value(x)[k] ;
	}

}
