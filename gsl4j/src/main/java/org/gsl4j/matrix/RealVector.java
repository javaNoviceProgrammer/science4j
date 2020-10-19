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
//	private ComplexVectorBuilder cvb ;

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
	public ComplexVector apply(Function<ComplexNumber, ComplexNumber> func) {
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
	public RealVector applyReal(MathFunction func) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = func.value(x[i]) ;
		}
		return new RealVector(y) ;
	}

	@Override
	public ComplexVector applyComplex(ComplexMathFunction func) {
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
	public RealVector add(double v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] + v ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector addRev(double v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v + x[i] ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector add(RealNumber v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] + v.re() ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector addRev(RealNumber v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.re() + x[i] ;
		}
		return new RealVector(re) ;
	}

	@Override
	public ComplexVector add(ComplexNumber v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] + v.re() ;
			im[i] = v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector addRev(ComplexNumber v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.re() + x[i] ;
			im[i] = v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector add(AlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) + x[i] ;
			im[i] = v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector addRev(AlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] + v.atReal(i) ;
			im[i] = v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public RealVector add(RealAlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] + v.atReal(i) ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector addRev(RealAlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) + x[i]  ;
		}
		return new RealVector(re) ;
	}

	public RealVector add(RealVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] + v.x[i] ;
		}
		return new RealVector(re) ;
	}

	public RealVector addRev(RealVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.x[i] + x[i]  ;
		}
		return new RealVector(re) ;
	}

	public RealVectorBuilder add(RealVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.x[i] = x[i] + v.x[i] ;
		}
		return v ;
	}

	public RealVectorBuilder addRev(RealVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.x[i] = v.x[i] + x[i] ;
		}
		return v ;
	}

	public ComplexVector add(ComplexVector v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] + v.re[i] ;
			im[i] = x[i] + v.im[i] ;
		}
		return new ComplexVector(re, im) ;
	}

	public ComplexVector addRev(ComplexVector v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.re[i] + x[i] ;
			im[i] = v.im[i] + x[i] ;
		}
		return new ComplexVector(re, im) ;
	}

	public ComplexVectorBuilder add(ComplexVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.re[i] = x[i] + v.re[i] ;
			v.im[i] = x[i] + v.im[i] ;
		}
		return v ;
	}

	public ComplexVectorBuilder addRev(ComplexVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.re[i] = v.re[i] + x[i] ;
			v.im[i] = v.im[i] + x[i] ;
		}
		return v ;
	}

	/*----- subtraction ------*/

	@Override
	public RealVector subtract(double v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] - v ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector subtractRev(double v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v - x[i] ;
		}
		return new RealVector(re) ;
	}

	public RealVector subtract(RealNumber v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] - v.re() ;
		}
		return new RealVector(re) ;
	}

	public RealVector subtractRev(RealNumber v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.re() - x[i] ;
		}
		return new RealVector(re) ;
	}

	public ComplexVector subtract(ComplexNumber v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] - v.re() ;
			im[i] = -v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	public ComplexVector subtractRev(ComplexNumber v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.re() - x[i] ;
			im[i] = v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector subtract(AlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] - v.atReal(i) ;
			im[i] = -v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector subtractRev(AlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) - x[i] ;
			im[i] = v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	public RealVector subtract(RealVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] - v.x[i] ;
		}
		return new RealVector(re) ;
	}

	public RealVector subtractRev(RealVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.x[i] - x[i]  ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector subtract(RealAlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] - v.atReal(i) ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector subtractRev(RealAlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.re()[i] - x[i]  ;
		}
		return new RealVector(re) ;
	}

	public RealVectorBuilder subtract(RealVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.x[i] = x[i] - v.x[i] ;
		}
		return v ;
	}

	public RealVectorBuilder subtractRev(RealVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.x[i] = v.x[i] - x[i]  ;
		}
		return v ;
	}

	public ComplexVectorBuilder subtract(ComplexVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.re[i] = x[i] - v.re[i] ;
			v.im[i] = x[i] - v.re[i] ;
		}
		return v ;
	}

	public ComplexVectorBuilder subtractRev(ComplexVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.re[i] = v.re[i] - x[i] ;
			v.im[i] = v.im[i] - x[i] ;
		}
		return v ;
	}


	/*----- multiplication ------*/

	@Override
	public RealVector multiply(double v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector multiplyRev(double v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v * x[i] ;
		}
		return new RealVector(re) ;
	}

	public RealVector multiply(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] * v.re() ;
		}
		return new RealVector(y) ;
	}

	public RealVector multiplyRev(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.re() * x[i] ;
		}
		return new RealVector(y) ;
	}

	public ComplexVector multiply(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.re() ;
			im[i] = x[i] * v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	public ComplexVector multiplyRev(ComplexNumber v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = v.re() * x[i] ;
			im[i] = v.im() * x[i] ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector multiply(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.atReal(i) ;
			im[i] = x[i] * v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector multiplyRev(AlgebraVector v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) * x[i] ;
			im[i] = v.atImag(i) * x[i] ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public RealVector multiply(RealAlgebraVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] * v.atReal(i) ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealVector multiplyRev(RealAlgebraVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.atReal(i) * x[i] ;
		}
		return new RealVector(y) ;
	}

	public RealVector multiply(RealVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] * v.atReal(i) ;
		}
		return new RealVector(y) ;
	}

	public RealVector multiplyRev(RealVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.atReal(i) * x[i] ;
		}
		return new RealVector(y) ;
	}

	public RealVectorBuilder multiply(RealVectorBuilder v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] * v.atReal(i) ;
		}
		return new RealVectorBuilder(y) ;
	}

	public RealVectorBuilder multiplyRev(RealVectorBuilder v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.atReal(i) * x[i] ;
		}
		return new RealVectorBuilder(y) ;
	}

	public ComplexVectorBuilder multiply(ComplexVectorBuilder v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.atReal(i) ;
			im[i] = x[i] * v.atImag(i) ;
		}
		return new ComplexVectorBuilder(re, im) ;
	}

	public ComplexVectorBuilder multiplyRev(ComplexVectorBuilder v) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) * x[i] ;
			im[i] = v.atImag(i) * x[i] ;
		}
		return new ComplexVectorBuilder(re, im) ;
	}


	/*----- division ------*/

	@Override
	public RealVector divide(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] / v ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealVector divideRev(double v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v / x[i] ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealVector divide(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] / v.re() ;
		}
		return new RealVector(y) ;
	}

	@Override
	public RealVector divideRev(RealNumber v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v.re() / x[i] ;
		}
		return new RealVector(y) ;
	}

	@Override
	public ComplexVector divide(ComplexNumber v) {
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
	public ComplexVector divideRev(ComplexNumber v) {
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
	public ComplexVector divide(AlgebraVector v) {
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
	public ComplexVector divideRev(AlgebraVector v) {
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
	public RealVector divide(RealAlgebraVector v) {
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
	public RealVector divideRev(RealAlgebraVector v) {
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

	public RealVector divide(RealVector v) {
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


	public RealVector divideRev(RealVector v) {
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

	public RealVectorBuilder divide(RealVectorBuilder v) {
		double[] re = new double[size] ;
		if(rb==null) {rb = new RealBuilder() ;}
		else {rb.reset();}
		for(int i=0; i<size; i++) {
			rb.set(v.atReal(i)) ;
//			rb.set(x[i]/rb) ;
			rb.set(rb.divideRev(x[i])) ;
			re[i] = rb.re() ;
		}
		return new RealVectorBuilder(re) ;
	}


	public RealVectorBuilder divideRev(RealVectorBuilder v) {
		double[] re = new double[size] ;
		if(rb==null) {rb = new RealBuilder() ;}
		else {rb.reset();}
		for(int i=0; i<size; i++) {
			rb.set(v.atReal(i)) ;
//			rb.set(rb/x[i]) ;
			rb.set(rb.divide(x[i])) ;
			re[i] = rb.re() ;
		}
		return new RealVectorBuilder(re) ;
	}

	public ComplexVectorBuilder divide(ComplexVectorBuilder v) {
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
		return new ComplexVectorBuilder(re, im) ;
	}

	public ComplexVectorBuilder divideRev(ComplexVectorBuilder v) {
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
		return new ComplexVectorBuilder(re, im) ;
	}

	/*----- negation ------*/

	@Override
	public RealVector negate() {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = -x[i] ;
		}
		return new RealVector(y) ;
	}


































}
