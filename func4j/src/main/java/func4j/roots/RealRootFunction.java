package func4j.roots;

import func4j.function.MathFunction;

/**
 *
 * This interface extends {@link MathFunction} and represents a function subject to
 * finding its real roots.
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface RealRootFunction extends MathFunction {

	double value(double x) ;

}
