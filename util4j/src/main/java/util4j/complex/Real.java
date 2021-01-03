package util4j.complex;

import java.io.Serializable;
import java.util.function.BiFunction;
import java.util.function.Function;

import util4j.constants.MathConstants;

/**
 * A class representing a real double-precision number.
 * The {@code equals()} method is correctly implemented to compare real numbers with a given tolerance.
 * The default tolerance epsilon is 1e-10.
 * This class implements the {@link ComplexNumber} interface, so that each real number is also a complex number.
 *
 * @author Meisam
 * @since 1.0
 *
 */
public final class Real implements Serializable, RealNumber {

	private static final long serialVersionUID = 1L;
	public static double EPSILON = 1e-10 ;

	public static boolean debug = false ;
	public static long count = 0 ;

	RealBuilder rb ;

	final double x ;

	public Real(double v) {
		this.x = v ;
		if(debug) {
			count++ ;
			System.out.println("Real count = " + count);
		}
	}

	public static Real of(double v) {
		return new Real(v) ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Real other = (Real) obj;
		if (Math.abs(x-other.x)>EPSILON)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(x) ;
	}

	@Override
	public double re() {
		return x ;
	}

	@Override
	public double im() {
		return 0.0 ;
	}

	@Override
	public ComplexNumber conjugate() {
		return this ;
	}

	@Override
	public RealBuilder getBuilder() {
		return new RealBuilder(this) ;
	}

	@Override
	public RealBuilder cachedBuilder() {
		if(rb==null) {
			rb = new RealBuilder(this) ;
		} else {
			rb.set(this) ;
		}
		System.out.println("cached builder value = " + rb.x);
		return rb ;
	}

	//************ Finite or Infinite **************

	public boolean isInf() {
		return MathConstants.isInf(x) ;
	}

	public boolean isPositiveInf() {
		return MathConstants.isPositiveInf(x) ;
	}

	public boolean isNegativeInf() {
		return MathConstants.isNegativeInf(x) ;
	}

	public boolean isFinite() {
		return MathConstants.isFinite(x) ;
	}

	//*********** support for complex math functions *************

	@Override
	public ComplexNumber apply(BiFunction<Double, Double, double[]> func) {
		return Complex.ofArray(func.apply(x, 0.0)) ;
	}

	@Override
	public ComplexNumber apply(Function<ComplexNumber, ComplexNumber> func) {
		return Complex.of(func.apply(this)) ;
	}

	//********** Algebraic Operations *****************

	public static Real valueOf(double v) {
		return new Real(v) ;
	}

	public static Real valueOf(Double v) {
		return new Real(v) ;
	}

	public static Real valueOf(Real v) {
		return new Real(v.x) ;
	}

	/*----- addition ------*/

	@Override
	public Real add(double v) {
		return new Real(x+v) ;
	}

	@Override
	public Real addRev(double v) {
		return new Real(v+x) ;
	}

	public Real add(Real v) {
		return new Real(this.x+v.x) ;
	}

	public Real addRev(Real v) {
		return new Real(v.x+this.x) ;
	}

	@Override
	public RealNumber add(RealNumber v) {
		return new Real(this.x + v.re()) ;
	}

	@Override
	public RealNumber addRev(RealNumber v) {
		return new Real(v.re() + this.x) ;
	}

	@Override
	public ComplexNumber add(double re, double im) {
		return Complex.ofRect(x+re, im) ;
	}

	@Override
	public ComplexNumber addRev(double re, double im) {
		return Complex.ofRect(x+re, im) ;
	}

	@Override
	public ComplexNumber add(ComplexNumber v) {
		return Complex.ofRect(x+v.re(), v.im()) ;
	}

	@Override
	public ComplexNumber addRev(ComplexNumber v) {
		return Complex.ofRect(v.re()+x, v.im()) ;
	}

	public RealBuilder add(RealBuilder v) {
		return v.add(x) ;
	}

	public RealBuilder addRev(RealBuilder v) {
		return v.addRev(x) ;
	}

	public ComplexBuilder add(ComplexBuilder v) {
		return v.add(x) ;
	}

	public ComplexBuilder addRev(ComplexBuilder v) {
		return v.addRev(x) ;
	}

	/*----- subtraction ------*/

	@Override
	public Real subtract(double v) {
		return new Real(x-v) ;
	}

	@Override
	public Real subtractRev(double v) {
		return new Real(v-x) ;
	}

	public Real subtract(Real v) {
		return new Real(this.x-v.x) ;
	}

	public Real subtractRev(Real v) {
		return new Real(v.x-this.x) ;
	}

	@Override
	public RealNumber subtract(RealNumber v) {
		return new Real(this.x - v.re()) ;
	}

	@Override
	public RealNumber subtractRev(RealNumber v) {
		return new Real(v.re() - this.x) ;
	}

	@Override
	public ComplexNumber subtract(ComplexNumber v) {
		return Complex.ofRect(x-v.re(), -v.im()) ;
	}

	@Override
	public ComplexNumber subtractRev(ComplexNumber v) {
		return Complex.ofRect(v.re()-x, v.im()) ;
	}

	@Override
	public ComplexNumber subtract(double re, double im) {
		return Complex.ofRect(x-re, -im) ;
	}

	@Override
	public ComplexNumber subtractRev(double re, double im) {
		return Complex.ofRect(re-x, im) ;
	}

	public RealBuilder subtract(RealBuilder v) {
		return v.subtract(x) ;
	}

	public RealBuilder subtractRev(RealBuilder v) {
		return v.subtractRev(x) ;
	}

	public ComplexBuilder subtract(ComplexBuilder v) {
		return v.subtract(x) ;
	}

	public ComplexBuilder subtractRev(ComplexBuilder v) {
		return v.subtractRev(x) ;
	}

	/*----- multiplication ------*/

	@Override
	public Real multiply(double v) {
		return new Real(x*v) ;
	}

	@Override
	public Real multiplyRev(double v) {
		return new Real(v*x) ;
	}

	public Real multiply(Real v) {
		return new Real(this.x*v.x) ;
	}

	public Real multiplyRev(Real v) {
		return new Real(v.x * this.x) ;
	}

	@Override
	public RealNumber multiply(RealNumber v) {
		return new Real(this.x * v.re()) ;
	}

	@Override
	public RealNumber multiplyRev(RealNumber v) {
		return new Real(v.re() * this.x) ;
	}

	@Override
	public ComplexNumber multiply(ComplexNumber v) {
		return Complex.ofRect(x*v.re(), x*v.im()) ;
	}

	@Override
	public ComplexNumber multiplyRev(ComplexNumber v) {
		return Complex.ofRect(x*v.re(), x*v.im()) ;
	}

	@Override
	public ComplexNumber multiply(double re, double im) {
		return Complex.ofRect(x*re, x*im) ;
	}

	@Override
	public ComplexNumber multiplyRev(double re, double im) {
		return Complex.ofRect(x*re, x*im) ;
	}

	public RealBuilder multiply(RealBuilder v) {
		return v.multiply(x) ;
	}

	public RealBuilder multiplyRev(RealBuilder v) {
		return v.multiplyRev(x) ;
	}

	public ComplexBuilder multiply(ComplexBuilder v) {
		return v.multiply(x) ;
	}

	public ComplexBuilder multiplyRev(ComplexBuilder v) {
		return v.multiplyRev(x) ;
	}

	/*----- division ------*/

	@Override
	public Real divide(double v) {
		return new Real(x/v) ;
	}

	@Override
	public Real divideRev(double v) {
		return new Real(v/x) ;
	}

	public Real divide(Real v) {
		return new Real(this.x/v.x) ;
	}

	public Real divideRev(Real v) {
		return new Real(v.x/this.x) ;
	}

	@Override
	public RealNumber divide(RealNumber v) {
		return new Real(this.x/v.re()) ;
	}

	@Override
	public RealNumber divideRev(RealNumber v) {
		return new Real(v.re()/this.x) ;
	}

	@Override
	public ComplexNumber divide(ComplexNumber v) {
		double mag = v.re()*v.re()+v.im()*v.im() ;
		return Complex.ofRect(x*v.re()/mag, -x*v.im()/mag) ;
	}

	@Override
	public ComplexNumber divideRev(ComplexNumber v) {
		return Complex.ofRect(v.re()/x, v.im()/x) ;
	}

	public ComplexNumber divide(double re, double im) {
		double mag = re*re+im*im ;
		return Complex.ofRect(x*re/mag, -x*im/mag) ;
	}

	public ComplexNumber divideRev(double re, double im) {
		return Complex.ofRect(re/x, im/x) ;
	}

	public RealBuilder divide(RealBuilder v) {
		return v.divide(x) ;
	}

	public RealBuilder divideRev(RealBuilder v) {
		return v.divideRev(x) ;
	}

	public ComplexBuilder divide(ComplexBuilder v) {
		return v.divide(x) ;
	}

	public ComplexBuilder divideRev(ComplexBuilder v) {
		return v.divideRev(x) ;
	}

	/*----- negation ------*/

	@Override
	public Real negate() {
		return new Real(-x) ;
	}


}
