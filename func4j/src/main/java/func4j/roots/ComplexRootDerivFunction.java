package func4j.roots;

import func4j.function.ComplexVectorMathFunction;
import util4j.complex.ComplexNumber;

@FunctionalInterface
public interface ComplexRootDerivFunction extends ComplexVectorMathFunction {
	
	ComplexNumber[] value(ComplexNumber... z) ;

}
