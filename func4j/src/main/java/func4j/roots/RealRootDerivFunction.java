package func4j.roots;


/**
 *
 * This interface represents a function f(x) and its derivative f'(x) for root finding
 * algorithms that require the derivative of the function (e.g. Newton-Raphson method)
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface RealRootDerivFunction {

	/**
	 *
	 * @param x : point at which f(x) and f'(x) are calculated
	 * @return {@code double[]} : array of {f(x), f'(x)} values
	 */
	double[] value(double x) ;
}
