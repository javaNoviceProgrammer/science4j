package org.gsl4j.function;


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
	 * @param x
	 * @return
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
