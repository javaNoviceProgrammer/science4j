package org.gsl4j.matrix;

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

/**
 * This class represents an immutable view of a real double-precision vector.
 *
 * @author Meisam
 * @since 1.0
 */
public class RealVector implements RealAlgebraVector {

	private RealBuilder rb ;
	private RealVectorBuilder rvb ;

	private ComplexBuilder cb ;
	private ComplexVectorBuilder cvb ;

	final double[] x ;
	final int size ;

	// auxiliary array for math operations (lazy initialization)
	private double[] re ;
	private double[] im ;

	public RealVector(double... x) {
		this.size = x.length ;
		this.x = x ; // make new copy (clone)??  --> probably not (immutable)
	}

	public RealVector(int size) {
		this.size = size ;
		this.x = new double[size] ;
	}

	@Override
	public String toString() {
		return Arrays.toString(x) ;
	}

	@Override
	public int size() {
		return size ;
	}

	@Override
	public RealNumber at(int index) {
		return Real.of(x[index]) ;
	}

	@Override
	public RealNumber get(int index) {
		return Real.of(x[index]) ;
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
		return x.clone() ;
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
	public List<RealNumber> toList() {
		List<RealNumber> y = new ArrayList<>(size) ;
		for(int i=0; i<size; i++) {
			y.add(Real.valueOf(x[i])) ;
		}
		return y ;
	}

	@Override
	public double[] toDoubleArray() {
		return x.clone() ;
	}

	@Override
	public RealAlgebraVector conjugate() {
		return this ;
	}

	@Override
	public RealVectorBuilder getBuilder() {
		return new RealVectorBuilder(this) ;
	}

	@Override
	public RealVectorBuilder cachedBuilder() {
		if(rvb==null) {
			rvb = new RealVectorBuilder(this) ;
		} else {
			rvb.set(this) ;
		}
		return rvb ;
	}


	@Override
	public AlgebraVector apply(Function<ComplexNumber, ComplexNumber> func) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		if (cb==null) {
			cb = new ComplexBuilder() ;
		}
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.set(func.apply(cb)) ;
			re[i] = cb.re() ;
			im[i] = cb.im() ;
			cb.reset() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public RealAlgebraVector applyReal(MathFunction func) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = func.value(x[i]) ;
		}
		return new RealVector(y) ;
	}

	@Override
	public ComplexAlgebraVector applyComplex(ComplexMathFunction func) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		if (cb==null) {
			cb = new ComplexBuilder() ;
		}
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.set(func.value(cb)) ;
			re[i] = cb.re() ;
			im[i] = cb.im() ;
			cb.reset() ;
		}
		return new ComplexVector(re, im) ;
	}


	//*********** support for algebraic operations *************

	public static RealVector valueOf(double[] v) {
		return new RealVector(v) ;
	}

	/*----- addition ------*/

	@Override
	public RealAlgebraVector add(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] + v ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector addRev(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v + x[i] ;
		}
		return new RealVector(y) ;
	}

	public RealAlgebraVector add(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] + v.re() ;
		}
		return new RealVector(y) ;
	}

	public RealAlgebraVector addRev(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.re() + x[i] ;
		}
		return new RealVector(y) ;
	}

	public ComplexAlgebraVector add(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = x[i] + v.re() ;
			im[i] = v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	public ComplexAlgebraVector addRev(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = v.re() + x[i] ;
			im[i] = v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexAlgebraVector add(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) + x[i] ;
			im[i] = v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexAlgebraVector addRev(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = x[i] + v.atReal(i) ;
			im[i] = v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}


	public RealVector add(RealVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] + v.x[i] ;
		}
		return new RealVector(y) ;
	}

	public RealVector addRev(RealVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.x[i] + x[i]  ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector add(RealAlgebraVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] + v.atReal(i) ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector addRev(RealAlgebraVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.atReal(i) + x[i]  ;
		}
		return new RealVector(y) ;
	}

	/*----- subtraction ------*/

	@Override
	public RealAlgebraVector subtract(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] - v ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector subtractRev(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v - x[i] ;
		}
		return new RealVector(y) ;
	}

	public RealAlgebraVector subtract(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] - v.re() ;
		}
		return new RealVector(y) ;
	}

	public RealAlgebraVector subtractRev(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.re() - x[i] ;
		}
		return new RealVector(y) ;
	}

	public ComplexAlgebraVector subtract(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = x[i] - v.re() ;
			im[i] = -v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	public ComplexAlgebraVector subtractRev(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = v.re() - x[i] ;
			im[i] = v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexAlgebraVector subtract(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = x[i] - v.atReal(i) ;
			im[i] = -v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexAlgebraVector subtractRev(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) - x[i] ;
			im[i] = v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	public RealVector subtract(RealVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] - v.x[i] ;
		}
		return new RealVector(y) ;
	}

	public RealVector subtractRev(RealVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.x[i] - x[i]  ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector subtract(RealAlgebraVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] - v.atReal(i) ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector subtractRev(RealAlgebraVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.re()[i] - x[i]  ;
		}
		return new RealVector(y) ;
	}


	/*----- multiplication ------*/

	@Override
	public RealAlgebraVector multiply(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] * v ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector multiplyRev(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v * x[i] ;
		}
		return new RealVector(y) ;
	}

	public RealAlgebraVector multiply(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] * v.re() ;
		}
		return new RealVector(y) ;
	}

	public RealAlgebraVector multiplyRev(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.re() * x[i] ;
		}
		return new RealVector(y) ;
	}

	public ComplexAlgebraVector multiply(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.re() ;
			im[i] = x[i] * v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	public ComplexAlgebraVector multiplyRev(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = v.re() * x[i] ;
			im[i] = v.im() * x[i] ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexAlgebraVector multiply(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.atReal(i) ;
			im[i] = x[i] * v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexAlgebraVector multiplyRev(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) * x[i] ;
			im[i] = v.atImag(i) * x[i] ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public RealAlgebraVector multiply(RealAlgebraVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] * v.atReal(i) ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector multiplyRev(RealAlgebraVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.atReal(i) * x[i] ;
		}
		return new RealVector(y) ;
	}


	/*----- division ------*/

	@Override
	public RealAlgebraVector divide(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] / v ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector divideRev(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v / x[i] ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector divide(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] / v.re() ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealAlgebraVector divideRev(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.re() / x[i] ;
		}
		return new RealVector(y) ;
	}

	@Override
	public ComplexAlgebraVector divide(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		if(cb==null) {cb = new ComplexBuilder() ;}
		else {cb.reset();}
		for(int i=0; i<size; i++) {
			cb.set(v.re(), v.im()) ;
//			cb.set(x[i]/cb) ;
			cb.set(cb.divideRev(x[i])) ;
			re[i] = cb.re() ;
			im[i] = cb.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexAlgebraVector divideRev(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		if(cb==null) {cb = new ComplexBuilder() ;}
		else {cb.reset();}
		for(int i=0; i<size; i++) {
			cb.set(v.re(), v.im()) ;
//			cb.set(cb/x[i]) ;
			cb.set(cb.divide(x[i])) ;
			re[i] = cb.re() ;
			im[i] = cb.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexAlgebraVector divide(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		if(cb==null) {cb = new ComplexBuilder() ;}
		else {cb.reset();}
		for(int i=0; i<size; i++) {
			cb.set(v.atReal(i), v.atImag(i)) ;
//			cb.set(x[i]/cb) ;
			cb.set(cb.divideRev(x[i])) ;
			re[i] = cb.re() ;
			im[i] = cb.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexAlgebraVector divideRev(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		if(cb==null) {cb = new ComplexBuilder() ;}
		else {cb.reset();}
		for(int i=0; i<size; i++) {
			cb.set(v.atReal(i), v.atImag(i)) ;
//			cb.set(cb/x[i]) ;
			cb.set(cb.divide(x[i])) ;
			re[i] = cb.re() ;
			im[i] = cb.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public RealAlgebraVector divide(RealAlgebraVector v) {
		double[] re = new double[size] ;
		if(rb==null) {rb = new RealBuilder() ;}
		else {rb.reset();}
		for(int i=0; i<size; i++) {
			rb.set(v.atReal(i)) ;
//			rb.set(x[i]/rb) ;
			rb.set(rb.divideRev(x[i])) ;
			re[i] = rb.re() ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealAlgebraVector divideRev(RealAlgebraVector v) {
		double[] re = new double[size] ;
		if(rb==null) {rb = new RealBuilder() ;}
		else {rb.reset();}
		for(int i=0; i<size; i++) {
			rb.set(v.atReal(i)) ;
//			rb.set(rb/x[i]) ;
			rb.set(rb.divide(x[i])) ;
			re[i] = rb.re() ;
		}
		return new RealVector(re) ;
	}

	/*----- negation ------*/

	@Override
	public RealAlgebraVector negate() {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = -x[i] ;
		}
		return new RealVector(y) ;
	}


































}
