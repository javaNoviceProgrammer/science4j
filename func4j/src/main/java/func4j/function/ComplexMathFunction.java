package func4j.function;

import util4j.complex.Complex;
import util4j.complex.ComplexNumber;

/**
 * Represents a Complex-valued single-variable function f(z=x+jy).
 *
 * @author Meisam
 * @since 1.0
 *
 */
@FunctionalInterface
public interface ComplexMathFunction {

	/**
	 * This method returns the {@link ComplexNumber} value of the function.
	 *
	 * @param z : {@link ComplexNumber} point at which the function is evaluated.
	 * @return {@link ComplexNumber} : the complex value of the function
	 */
	ComplexNumber value(ComplexNumber z) ;

	/**
	 * This method allows defining the f(z) in terms of real and imaginary parts of z: f(re+j*im)
	 *
	 * @param re : real part of z
	 * @param im : imaginary part of z
	 * @return {@link ComplexNumber} : the complex value of the function
	 */
	default ComplexNumber value(double re, double im) {
		return value(Complex.ofRect(re, im)) ;
	}

	default double[] toArray(double re, double im) {
		ComplexNumber z = value(re, im)  ;
		return new double[] {z.re(), z.im()} ;
	}

	default double[] toArray(ComplexNumber z) {
		ComplexNumber w = value(z)  ;
		return new double[] {w.re(), w.im()} ;
	}

}
