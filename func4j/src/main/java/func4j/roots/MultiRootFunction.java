package func4j.roots;

import func4j.function.VectorMathFunction;

@FunctionalInterface
public interface MultiRootFunction extends VectorMathFunction {


	double[] value(double... x) ;
}
