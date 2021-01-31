package func4j.roots;

import func4j.function.ComplexMathFunction;
import util4j.complex.ComplexNumber;

public interface ComplexRootFunction extends ComplexMathFunction {
	
	ComplexNumber value(ComplexNumber z) ;

}
