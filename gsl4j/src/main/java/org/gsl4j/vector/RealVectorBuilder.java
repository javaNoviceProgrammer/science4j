package org.gsl4j.vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealBuilder;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;



public class RealVectorBuilder implements RealAlgebraVector, VectorBuilder {

	private ComplexBuilder cb ;

	public static boolean debug = false ;
	public static long count = 0 ;

	double[] x ;
	int size ;


	public RealVectorBuilder(double... x) {
		this.size = x.length ;
		this.x = x ;

		if(debug) {
			count++ ;
			System.out.println("RealVectorBuilder count = " + count);
		}
	}

	public RealVectorBuilder(int size) {
		this.size = size ;
		this.x = new double[size] ;

		if(debug) {
			count++ ;
			System.out.println("RealVectorBuilder count = " + count);
		}
	}

	public RealVectorBuilder(RealAlgebraVector v) {
		this.size = v.size() ;
		this.x = v.re() ;

		if(debug) {
			count++ ;
			System.out.println("RealVectorBuilder count = " + count);
		}
	}

	public RealVectorBuilder shiftLeft(RealAlgebraVector v) {
		this.size = v.size() ;
		for(int i=0; i<size; i++) {
			x[i] = v.atReal(i) ;
		}
		return this ;
	}

	@Override
	public String toString() {
		return Arrays.toString(x) ;
	}

	@Override
	public RealNumber[] toArray() {
		RealNumber[] y = new RealNumber[size] ;
		for(int i=0; i<size; i++) {
			y[i] = Real.valueOf(x[i]) ;
		}
		return y ;
	}

	@Override
	public double[] toDoubleArray() {
		return x.clone() ;
	}

	@Override
	public List<RealNumber> toList() {
		List<RealNumber> y = new ArrayList<>(size) ;
		for(int i=0; i<size; i++) {
			y.add(Real.valueOf(x[i])) ;
		}
		return y ;
	}

	@Override
	public int size() {
		return size ;
	}

	@Override
	public RealBuilder at(int index) {
		return new RealBuilder(x[index]) ;
	}

	@Override
	public RealBuilder get(int index) {
		return new RealBuilder(x[index]) ;
	}

	@Override
	public double atIndex(int index) {
		return x[index] ;
	}

	@Override
	public double atReal(int index) {
		return x[index] ;
	}

	@Override
	public double atImag(int index) {
		return 0.0 ;
	}

	@Override
	public double[] re() {
		return x ;
	}

	@Override
	public void reset() {
		for(int i=0; i<size; i++) {
			x[i] = 0.0 ;
		}
	}

	@Override
	public RealVectorBuilder set(int index, double z) {
		x[index] = z ;
		return this ;
	}

	@Override
	public RealVectorBuilder setAll(double z) {
		Arrays.fill(x, z) ;
		return this ;
	}

	public RealVectorBuilder set(RealVector v) {
		for(int i=0; i<size; i++) {
			x[i] = v.x[i] ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder set(int index, RealNumber z) {
		x[index] = z.re() ;
		return this ;
	}

	@Override
	public RealVectorBuilder setAll(RealNumber z) {
		for(int i=0; i<size; i++) {
			x[i] = z.re() ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder set(int index, double re, double im) {
		x[index] = re ; // ignore imaginary part
		return this ;
	}

	@Override
	public RealVectorBuilder setAll(double re, double im) {
		for(int i=0; i<size; i++) {
			x[i] = re ; // ignore imaginary part
		}
		return this ;
	}

	@Override
	public RealVectorBuilder set(int index, ComplexNumber z) {
		x[index] = z.re() ; // ignore imaginary part
		return this ;
	}

	@Override
	public RealVectorBuilder setAll(ComplexNumber z) {
		for(int i=0; i<size; i++) {
			x[i] = z.re() ; // ignore imaginary part
		}
		return this ;
	}

	@Override
	public RealAlgebraVector conjugate() {
		return this ;
	}

	@Override
	public RealVectorBuilder getBuilder() {
		return this ;
	}

	@Override
	public RealVectorBuilder cachedBuilder() {
		return this ;
	}

	@Override
	public ComplexVectorBuilder apply(Function<ComplexNumber, ComplexNumber> func) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.set(func.apply(cb)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	@Override
	public RealVectorBuilder applyReal(MathFunction func) {
		for(int i=0; i<size; i++) {
			x[i] = func.value(x[i]) ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder applyComplex(ComplexMathFunction func) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.set(func.value(cb)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	@Override
	public RealVectorBuilder add(double v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] + v ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder addRev(double v) {
		for(int i=0; i<size; i++) {
			x[i] = v + x[i] ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder add(RealNumber v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] + v.re() ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder addRev(RealNumber v) {
		for(int i=0; i<size; i++) {
			x[i] = v.re() + x[i] ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder add(RealAlgebraVector v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] + v.atReal(i) ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder addRev(RealAlgebraVector v) {
		for(int i=0; i<size; i++) {
			x[i] = v.atReal(i) + x[i] ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder add(ComplexNumber v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = x[i] + v.re() ;
			cvb.im[i] = x[i] + v.im() ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder addRev(ComplexNumber v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = v.re() + x[i] ;
			cvb.im[i] = v.im() + x[i] ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder add(AlgebraVector v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = x[i] + v.atReal(i) ;
			cvb.im[i] = x[i] + v.atImag(i) ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder addRev(AlgebraVector v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = v.atReal(i) + x[i] ;
			cvb.im[i] = v.atImag(i) + x[i] ;
		}
		return cvb ;
	}

	public RealVectorBuilder add(RealVector v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] + v.x[i] ;
		}
		return this ;
	}

	public RealVectorBuilder addRev(RealVector v) {
		for(int i=0; i<size; i++) {
			x[i] = v.x[i] + x[i] ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder add(VectorBuilder v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = x[i] + v.atReal(i) ;
			cvb.im[i] = x[i] + v.atImag(i) ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder addRev(VectorBuilder v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = v.atReal(i) + x[i] ;
			cvb.im[i] = v.atImag(i) + x[i] ;
		}
		return cvb ;
	}

	/*----- subtraction ------*/

	@Override
	public RealVectorBuilder subtract(double v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] - v ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder subtractRev(double v) {
		for(int i=0; i<size; i++) {
			x[i] = v - x[i] ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder subtract(RealNumber v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] - v.re() ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder subtractRev(RealNumber v) {
		for(int i=0; i<size; i++) {
			x[i] = v.re() - x[i] ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder subtract(RealAlgebraVector v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] - v.atReal(i) ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder subtractRev(RealAlgebraVector v) {
		for(int i=0; i<size; i++) {
			x[i] = v.atReal(i) - x[i] ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder subtract(ComplexNumber v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = x[i] - v.re() ;
			cvb.im[i] = x[i] - v.im() ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder subtractRev(ComplexNumber v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = v.re() - x[i] ;
			cvb.im[i] = v.im() - x[i] ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder subtract(AlgebraVector v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = x[i] - v.atReal(i) ;
			cvb.im[i] = x[i] - v.atImag(i) ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder subtractRev(AlgebraVector v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = v.atReal(i) - x[i] ;
			cvb.im[i] = v.atImag(i) - x[i] ;
		}
		return cvb ;
	}

	public RealVectorBuilder subtract(RealVector v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] - v.x[i] ;
		}
		return this ;
	}

	public RealVectorBuilder subtractRev(RealVector v) {
		for(int i=0; i<size; i++) {
			x[i] = v.x[i] - x[i] ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder subtract(VectorBuilder v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = x[i] - v.atReal(i) ;
			cvb.im[i] = x[i] - v.atImag(i) ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder subtractRev(VectorBuilder v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = v.atReal(i) - x[i] ;
			cvb.im[i] = v.atImag(i) - x[i] ;
		}
		return cvb ;
	}

	/*----- multiplication ------*/

	@Override
	public RealVectorBuilder multiply(double v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] * v ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder multiplyRev(double v) {
		for(int i=0; i<size; i++) {
			x[i] = v * x[i] ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder multiply(RealNumber v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] * v.re() ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder multiplyRev(RealNumber v) {
		for(int i=0; i<size; i++) {
			x[i] = v.re() * x[i] ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder multiply(ComplexNumber v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = x[i] * v.re() ;
			cvb.im[i] = x[i] * v.im() ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder multiplyRev(ComplexNumber v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = v.re() * x[i] ;
			cvb.im[i] = v.im() * x[i] ;
		}
		return cvb ;
	}

	@Override
	public RealVectorBuilder multiply(RealAlgebraVector v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] * v.atReal(i) ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder multiplyRev(RealAlgebraVector v) {
		for(int i=0; i<size; i++) {
			x[i] = v.atReal(i) * x[i] ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder multiply(AlgebraVector v) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.multiply(v.atReal(i), v.atImag(i)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder multiplyRev(AlgebraVector v) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.multiply(v.atReal(i), v.atImag(i)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder multiply(VectorBuilder v) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.multiply(v.atReal(i), v.atImag(i)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder multiplyRev(VectorBuilder v) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.multiply(v.atReal(i), v.atImag(i)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	/*----- division ------*/

	@Override
	public RealVectorBuilder divide(double v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] / v ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder divideRev(double v) {
		for(int i=0; i<size; i++) {
			x[i] = v / x[i] ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder divide(RealNumber v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] / v.re() ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder divideRev(RealNumber v) {
		for(int i=0; i<size; i++) {
			x[i] = v.re() / x[i] ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder divide(RealAlgebraVector v) {
		for(int i=0; i<size; i++) {
			x[i] = x[i] / v.atReal(i) ;
		}
		return this ;
	}

	@Override
	public RealVectorBuilder divideRev(RealAlgebraVector v) {
		for(int i=0; i<size; i++) {
			x[i] = v.atReal(i) / x[i] ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder divide(ComplexNumber v) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.divide(v) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder divideRev(ComplexNumber v) {
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cvb.re[i] = v.re() / x[i] ;
			cvb.im[i] = v.im() / x[i] ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder divide(AlgebraVector v) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.divide(v.atReal(i), v.atImag(i)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder divideRev(AlgebraVector v) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.divideRev(v.atReal(i), v.atImag(i)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}


	@Override
	public ComplexVectorBuilder divide(VectorBuilder v) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.divide(v.atReal(i), v.atImag(i)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	@Override
	public ComplexVectorBuilder divideRev(VectorBuilder v) {
		if (cb==null) { cb = new ComplexBuilder() ; }
		ComplexVectorBuilder cvb = new ComplexVectorBuilder(size) ;
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.divideRev(v.atReal(i), v.atImag(i)) ;
			cvb.re[i] = cb.re() ;
			cvb.im[i] = cb.im() ;
		}
		return cvb ;
	}

	/*----- negation ------*/

	@Override
	public RealVectorBuilder negate() {
		for(int i=0; i<size; i++) {
			x[i] = -x[i] ;
		}
		return this ;
	}

}
