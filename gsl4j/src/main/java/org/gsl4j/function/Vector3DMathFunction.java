package org.gsl4j.function;

@FunctionalInterface
public interface Vector3DMathFunction extends VectorMathFunction {


	default MultiVariateMathFunction first() {
		return x -> value(x)[0] ;
	}

	default MultiVariateMathFunction second() {
		return x -> value(x)[1] ;
	}

	default MultiVariateMathFunction third() {
		return x -> value(x)[2] ;
	}


}
