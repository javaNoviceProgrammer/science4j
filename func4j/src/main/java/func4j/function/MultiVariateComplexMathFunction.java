package func4j.function;

import util4j.complex.ComplexNumber;

/**
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface MultiVariateComplexMathFunction {

	ComplexNumber value(ComplexNumber... z) ;

}
