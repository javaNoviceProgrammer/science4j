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
	
	default ComplexNumber[] value(ComplexNumber[] x) {
		int dim = x.length ;
		ComplexNumber[] y = new ComplexNumber[dim] ;
		for(int i=0; i<dim; i++) {
			y[i] = value(x[i]) ;
		}
		return y ;
	}

	default double[] toArray(double re, double im) {
		ComplexNumber z = value(re, im)  ;
		return new double[] {z.re(), z.im()} ;
	}

	default double[] toArray(ComplexNumber z) {
		ComplexNumber w = value(z)  ;
		return new double[] {w.re(), w.im()} ;
	}
	
	default ComplexMathFunction compose(ComplexMathFunction func) {
		return x -> value(func.value(x)) ;
	}
	
//	default ComplexMathFunction deriv() {
//		return x -> NumericalDiff.central(this, x, 0.1) ;
//	}
//
//	default ComplexMathFunction deriv2() {
//		return x -> NumericalDiff.central(this.deriv(), x, 0.1) ;
//	}

	/*-------- addition ----------*/

	default ComplexMathFunction add(double v) { // this + v
		return x -> value(x).add(v) ;
	}
	
	default ComplexMathFunction addRev(double v) { // v + this
		return x -> value(x).addRev(v) ;
	}
	
	default ComplexMathFunction add(ComplexNumber v) { // this + v
		return x -> value(x).add(v) ;
	}
	
	default ComplexMathFunction addRev(ComplexNumber v) { // v + this
		return x -> value(x).addRev(v) ;
	}
	
	default ComplexMathFunction add(ComplexMathFunction v) { // this + v
		return x -> value(x).add(v.value(x)) ;
	}
	
	default ComplexMathFunction addRev(ComplexMathFunction v) { // v + this
		return x -> value(x).addRev(v.value(x)) ;
	}
	
	default ComplexMathFunction plus(double v) { // this + v
		return x -> value(x).add(v) ;
	}
	
	default ComplexMathFunction plus(ComplexNumber v) { // this + v
		return x -> value(x).add(v) ;
	}
	
	default ComplexMathFunction plus(ComplexMathFunction v) { // this + v
		return x -> value(x).add(v.value(x)) ;
	}

	/*-------- subtraction ----------*/

	default ComplexMathFunction subtract(double v) { // this - v
		return x -> value(x).subtract(v) ;
	}
	
	default ComplexMathFunction subtractRev(double v) { // v - this
		return x -> value(x).subtractRev(v) ;
	}
	
	default ComplexMathFunction subtract(ComplexNumber v) { // this - v
		return x -> value(x).subtract(v) ;
	}
	
	default ComplexMathFunction subtractRev(ComplexNumber v) { // v - this
		return x -> value(x).subtractRev(v) ;
	}
	
	default ComplexMathFunction subtract(ComplexMathFunction v) { // this - v
		return x -> value(x).subtract(v.value(x)) ;
	}
	
	default ComplexMathFunction subtractRev(ComplexMathFunction v) { // v - this
		return x -> value(x).subtractRev(v.value(x)) ;
	}
	
	default ComplexMathFunction minus(double v) { // this - v
		return x -> value(x).subtract(v) ;
	}
	
	default ComplexMathFunction minus(ComplexNumber v) { // this - v
		return x -> value(x).subtract(v) ;
	}
	
	default ComplexMathFunction minus(ComplexMathFunction v) { // this - v
		return x -> value(x).subtract(v.value(x)) ;
	}

	/*-------- Multiplication ----------*/

	default ComplexMathFunction multiply(double v) { // this * v
		return x -> value(x).multiply(v) ;
	}
	
	default ComplexMathFunction multiplyRev(double v) { // v * this
		return x -> value(x).multiplyRev(v) ;
	}
	
	default ComplexMathFunction multiply(ComplexNumber v) { // this * v
		return x -> value(x).multiply(v) ;
	}
	
	default ComplexMathFunction multiplyRev(ComplexNumber v) { // v * this
		return x -> value(x).multiplyRev(v) ;
	}
	
	default ComplexMathFunction multiply(ComplexMathFunction v) { // this * v
		return x -> value(x).multiply(v.value(x)) ;
	}
	
	default ComplexMathFunction multiplyRev(ComplexMathFunction v) { // v * this
		return x -> value(x).multiplyRev(v.value(x)) ;
	}
	
	default ComplexMathFunction times(double v) { // this * v
		return x -> value(x).multiply(v) ;
	}
	
	default ComplexMathFunction times(ComplexNumber v) { // this * v
		return x -> value(x).multiply(v) ;
	}
	
	default ComplexMathFunction times(ComplexMathFunction v) { // this * v
		return x -> value(x).multiply(v.value(x)) ;
	}

	/*-------- Division ----------*/

	default ComplexMathFunction divide(double v) { // this / v
		return x -> value(x).divide(v) ;
	}
	
	default ComplexMathFunction divideRev(double v) { // v / this
		return x -> value(x).divideRev(v) ;
	}
	
	default ComplexMathFunction divide(ComplexNumber v) { // this / v
		return x -> value(x).divide(v) ;
	}
	
	default ComplexMathFunction divideRev(ComplexNumber v) { // v / this
		return x -> value(x).divideRev(v) ;
	}
	
	default ComplexMathFunction divide(ComplexMathFunction v) { // this / v
		return x -> value(x).divide(v.value(x)) ;
	}
	
	default ComplexMathFunction divideRev(ComplexMathFunction v) { // v / this
		return x -> value(x).divideRev(v.value(x)) ;
	}
	
	default ComplexMathFunction over(double v) { // this / v
		return x -> value(x).divide(v) ;
	}
	
	default ComplexMathFunction over(ComplexNumber v) { // this / v
		return x -> value(x).divide(v) ;
	}
	
	default ComplexMathFunction over(ComplexMathFunction v) { // this / v
		return x -> value(x).divide(v.value(x)) ;
	}

	/*-------- negation ----------*/

	default ComplexMathFunction negate() { // - this
		return x -> value(x).negate() ;
	}

	
}
