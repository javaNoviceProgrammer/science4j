package func4j.integration;

import func4j.function.MultiVariateMathFunction;

/**
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface IntegralFunctionND extends MultiVariateMathFunction {

	/**
	 *
	 * @param x
	 * @return
	 */
	double value(double... x) ;
}
