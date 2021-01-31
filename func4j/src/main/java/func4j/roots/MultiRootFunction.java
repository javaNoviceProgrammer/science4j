package func4j.roots;

import func4j.function.VectorMathFunction;

@FunctionalInterface
public interface MultiRootFunction extends VectorMathFunction {

	/**
	 * x is the vector input: x = [x1, x2, x3, ..., xN] <p>
	 * f(x) is the vector function: f(x1,x2,...) = [f1, f2, ..., fN]
	 * 
	 */
	double[] value(double... x) ;
}
