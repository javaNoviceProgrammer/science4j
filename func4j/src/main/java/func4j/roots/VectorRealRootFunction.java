package func4j.roots;

import func4j.function.VectorMathFunction;

@FunctionalInterface
public interface VectorRealRootFunction extends VectorMathFunction {


	double[] value(double... x) ;
}
