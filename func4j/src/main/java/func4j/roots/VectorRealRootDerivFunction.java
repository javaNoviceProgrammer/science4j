package func4j.roots;

import func4j.function.VectorMathFunction;

@FunctionalInterface
public interface VectorRealRootDerivFunction extends VectorMathFunction {

	double[] value(double... x) ;

}
