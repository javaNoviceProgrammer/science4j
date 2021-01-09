package func4j.function;

public interface Vector3DCartesianMathFunction extends Vector3DMathFunction {

	default MultiVariateMathFunction div() {
		return x -> this.first().deriv(0).value(x) +
					this.second().deriv(1).value(x) +
					this.third().deriv(2).value(x) ;
	}

	default Vector3DCartesianMathFunction curl() {
		MultiVariateMathFunction f1 = x -> this.third().deriv(1).value(x)
				- this.second().deriv(2).value(x) ;
		MultiVariateMathFunction f2 = x -> this.first().deriv(2).value(x)
				- this.third().deriv(0).value(x) ;
		MultiVariateMathFunction f3 = x -> this.second().deriv(0).value(x)
				- this.first().deriv(1).value(x) ;
		return x -> new double[] {f1.value(x), f2.value(x), f3.value(x)} ;
	}

}
