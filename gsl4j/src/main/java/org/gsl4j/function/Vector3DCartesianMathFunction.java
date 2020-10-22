package org.gsl4j.function;

public interface Vector3DCartesianMathFunction extends Vector3DMathFunction {

	default MultiVariateMathFunction div() {
		return x -> this.first().deriv(0).value(x) +
					this.second().deriv(1).value(x) +
					this.third().deriv(2).value(x) ;
	}

}
