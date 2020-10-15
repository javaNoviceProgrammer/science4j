package org.gsl4j.complex;

import java.io.Serializable;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.gsl4j.function.MathFunction;

/**
 * This class represents a builder for avoiding multiple heap allocations when performing
 * math operations on real double-precision numbers.
 *
 * @author Meisam
 * @since 1.0
 *
 */
public class RealBuilder implements Serializable, ComplexNumber {

	private static final long serialVersionUID = 6445188397847083940L;

	double x ;

	public RealBuilder() {
		x = 0.0 ;
	}

	public RealBuilder(double v) {
		x = v ;
	}

	public RealBuilder(Real v) {
		x = v.x ;
	}

	@Override
	public double re() {
		return x ;
	}

	@Override
	public double im() {
		return 0.0 ;
	}

	public RealBuilder getBuilder() {
		return this ;
	}

	public Real toReal() {
		return new Real(x) ;
	}

	@Override
	public String toString() {
		return String.valueOf(x) ;
	}

	public void reset() {
		x = 0.0 ;
	}

	//*********** support for complex math functions *************

	@Override
	public ComplexNumber apply(BiFunction<Double, Double, double[]> func) {
		return new ComplexBuilder(x, 0.0).apply(func) ;
	}

	@Override
	public ComplexNumber apply(Function<ComplexNumber, ComplexNumber> func) {
		return new ComplexBuilder(x, 0.0).apply(func) ;
	}

	public RealBuilder apply(MathFunction func) {
		x = func.value(x) ;
		return this ;
	}

	//*********** support for algebraic operations *************


	/*----- addition ------*/

	@Override
	public RealBuilder add(double v) {
		x += v ;
		return this ;
	}

	@Override
	public RealBuilder addRev(double v) {
		x += v ;
		return this ;
	}

	public RealBuilder add(Real v) {
		x += v.x ;
		return this ;
	}

	public RealBuilder addRev(Real v) {
		x += v.x ;
		return this ;
	}

	@Override
	public ComplexBuilder add(double re, double im) {
		return new ComplexBuilder(x, 0.0).add(re, im) ;
	}

	@Override
	public ComplexBuilder addRev(double re, double im) {
		return new ComplexBuilder(x, 0.0).add(re, im) ;
	}

	@Override
	public ComplexBuilder add(ComplexNumber v) {
		return new ComplexBuilder(x, 0.0).add(v) ;
	}

	@Override
	public ComplexBuilder addRev(ComplexNumber v) {
		return new ComplexBuilder(x, 0.0).add(v) ;
	}

	/*----- subtraction ------*/

	@Override
	public RealBuilder subtract(double v) {
		x -= v ;
		return this ;
	}

	@Override
	public RealBuilder subtractRev(double v) {
		x = v - x ;
		return this ;
	}

	public RealBuilder subtract(Real v) {
		x -= v.x ;
		return this ;
	}

	public RealBuilder subtractRev(Real v) {
		x = v.x - x ;
		return this ;
	}

	@Override
	public ComplexBuilder subtract(double re, double im) {
		return new ComplexBuilder(x, 0.0).subtract(re, im) ;
	}

	@Override
	public ComplexBuilder subtractRev(double re, double im) {
		return new ComplexBuilder(x, 0.0).subtractRev(re, im) ;
	}

	@Override
	public ComplexBuilder subtract(ComplexNumber v) {
		return new ComplexBuilder(x, 0.0).subtract(v) ;
	}

	@Override
	public ComplexNumber subtractRev(ComplexNumber v) {
		return new ComplexBuilder(x, 0.0).subtractRev(v) ;
	}

	/*----- multiplication ------*/

	@Override
	public RealBuilder multiply(double v) {
		x *= v ;
		return this ;
	}

	@Override
	public RealBuilder multiplyRev(double v) {
		x *= v ;
		return this ;
	}

	public RealBuilder multiply(Real v) {
		x *= v.x ;
		return this ;
	}

	public RealBuilder multiplyRev(Real v) {
		x *= v.x ;
		return this ;
	}

	@Override
	public ComplexBuilder multiply(double re, double im) {
		return new ComplexBuilder(x, 0.0).multiply(re, im) ;
	}

	@Override
	public ComplexBuilder multiplyRev(double re, double im) {
		return new ComplexBuilder(x, 0.0).multiplyRev(re, im) ;
	}

	@Override
	public ComplexBuilder multiply(ComplexNumber v) {
		return new ComplexBuilder(x, 0.0).multiply(v) ;
	}

	@Override
	public ComplexBuilder multiplyRev(ComplexNumber v) {
		return new ComplexBuilder(x, 0.0).multiply(v) ;
	}

	/*----- division ------*/

	@Override
	public RealBuilder divide(double v) {
		x = x/v ;
		return this ;
	}

	@Override
	public RealBuilder divideRev(double v) {
		x = v/x ;
		return this ;
	}

	public RealBuilder divide(Real v) {
		x = x / v.x ;
		return this ;
	}

	public RealBuilder divideRev(Real v) {
		x = v.x / x ;
		return this ;
	}

	@Override
	public ComplexBuilder divide(double re, double im) {
		return new ComplexBuilder(x, 0.0).divide(re, im) ;
	}

	@Override
	public ComplexNumber divideRev(double re, double im) {
		return new ComplexBuilder(x, 0.0).divideRev(re, im) ;
	}

	@Override
	public ComplexNumber divide(ComplexNumber v) {
		return new ComplexBuilder(x, 0.0).divide(v) ;
	}

	@Override
	public ComplexNumber divideRev(ComplexNumber v) {
		return new ComplexBuilder(x, 0.0).divideRev(v) ;
	}

	/*----- negation ------*/

	@Override
	public RealBuilder negate() {
		x *= -1.0 ;
		return this ;
	}

}
