package func4j.function;


/**
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface VectorMathFunction {

	/**
	 *
	 * @param x = [x1, x2, x3, ..., xN]
	 * @return f(x) = [f1, f2, ...., fN]
	 */
	double[] value(double... x) ;


	default MultiVariateMathFunction at(int k) {
		return x -> value(x)[k] ;
	}

	default MultiVariateMathFunction get(int k) {
		return x -> value(x)[k] ;
	}

	default MultiVariateMathFunction dot(VectorMathFunction vec) {
		return x -> {
			double[] v = vec.value(x) ;
			double[] val = value(x) ;
			double result = 0.0 ;
			for(int i=0; i<val.length; i++)
				result = val[i] * v[i] ;
			return result ;
		} ;
	}

}
