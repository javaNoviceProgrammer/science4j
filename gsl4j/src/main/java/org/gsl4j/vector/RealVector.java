package org.gsl4j.vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.Real;
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


	private RealVectorBuilder rvb ;
	private ComplexBuilder cb ;

	private Map<Integer, Real> cache ;

	public static boolean debug = false ;
	public static long count = 0 ;

	final double[] x ;
	final int size ;

	// auxiliary array for math operations (lazy initialization)
	private double[] re ;
	private double[] im ;

	public RealVector(double... x) {
		this.size = x.length ;
		this.x = x.clone() ; // make new copy (clone)??

		if(debug) {
			count++ ;
			System.out.println("RealVector count = " + count);
		}
	}

	public RealVector(int size) {
		this.size = size ;
		this.x = new double[size] ;

		if(debug) {
			count++ ;
			System.out.println("RealVector count = " + count);
		}
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
	public int size() {
		return size ;
	}

	@Override
	public Real at(int index) { // cache the returned value
//		return Real.of(x[index]) ;

		if(cache == null) {
			cache = new HashMap<>() ;
			Real v = Real.of(x[index]) ;
			cache.put(index, v) ;
			return v ;
		}
		else if(cache.containsKey(index)) {
			return cache.get(index) ;
		}
		else {
			return Real.of(x[index]) ;
		}
	}

	@Override
	public Real get(int index) { // cache the returned value
//		return Real.of(x[index]) ;

		if(cache == null) {
			cache = new HashMap<>() ;
			Real v = Real.of(x[index]) ;
			cache.put(index, v) ;
			return v ;
		}
		else if(cache.containsKey(index)) {
			return cache.get(index) ;
		}
		else {
			return Real.of(x[index]) ;
		}
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
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		if (cb==null) {
			cb = new ComplexBuilder() ;
		}
		for(int i=0; i<size; i++) {
			cb.set(x[i], 0.0) ;
			cb.set(func.apply(cb)) ;
			re[i] = cb.re() ;
			im[i] = cb.im() ;
//			cb.reset() ;
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
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.re() ;
		}
		return new RealVector(re) ;
	}

	public RealVector multiplyRev(RealNumber v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.re() * x[i] ;
		}
		return new RealVector(re) ;
	}

	public ComplexVector multiply(ComplexNumber v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.re() ;
			im[i] = x[i] * v.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	public ComplexVector multiplyRev(ComplexNumber v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.re() * x[i] ;
			im[i] = v.im() * x[i] ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector multiply(AlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.atReal(i) ;
			im[i] = x[i] * v.atImag(i) ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector multiplyRev(AlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) * x[i] ;
			im[i] = v.atImag(i) * x[i] ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public RealVector multiply(RealAlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.atReal(i) ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector multiplyRev(RealAlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) * x[i] ;
		}
		return new RealVector(re) ;
	}

	public RealVector multiply(RealVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] * v.x[i] ;
		}
		return new RealVector(re) ;
	}

	public RealVector multiplyRev(RealVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.x[i] * x[i] ;
		}
		return new RealVector(re) ;
	}

	public RealVectorBuilder multiply(RealVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.x[i] = x[i] * v.x[i] ;
		}
		return v ;
	}

	public RealVectorBuilder multiplyRev(RealVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.x[i] = v.x[i] * x[i] ;
		}
		return v ;
	}

	public ComplexVectorBuilder multiply(ComplexVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.re[i] = x[i] * v.re[i] ;
			v.im[i] = x[i] * v.im[i] ;
		}
		return v ;
	}

	public ComplexVectorBuilder multiplyRev(ComplexVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.re[i] = v.re[i] * x[i] ;
			v.im[i] = v.re[i] * x[i] ;
		}
		return v ;
	}


	/*----- division ------*/

	@Override
	public RealVector divide(double v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] / v ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector divideRev(double v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v / x[i] ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector divide(RealNumber v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] / v.re() ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector divideRev(RealNumber v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.re() / x[i] ;
		}
		return new RealVector(re) ;
	}

	@Override
	public ComplexVector divide(ComplexNumber v) {
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
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
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
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
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
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
		if(re == null) { re = new double[size] ; }
		if(im == null) { im = new double[size] ; }
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
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] / v.atReal(i) ;
		}
		return new RealVector(re) ;
	}

	@Override
	public RealVector divideRev(RealAlgebraVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.atReal(i) / x[i] ;
		}
		return new RealVector(re) ;
	}

	public RealVector divide(RealVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = x[i] / v.x[i] ;
		}
		return new RealVector(re) ;
	}


	public RealVector divideRev(RealVector v) {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = v.x[i] / x[i] ;
		}
		return new RealVector(re) ;
	}

	public RealVectorBuilder divide(RealVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.x[i] = x[i] / v.x[i] ;
		}
		return v ;
	}


	public RealVectorBuilder divideRev(RealVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.x[i] = v.x[i] / x[i] ;
		}
		return v ;
	}

	public ComplexVectorBuilder divide(ComplexVectorBuilder v) {
		double mag ;
		for(int i=0; i<size; i++) {
			mag = v.re[i]*v.re[i] + v.im[i]*v.im[i] ;
			v.re[i] =  x[i] * v.re[i]/mag ;
			v.im[i] = -x[i] * v.im[i]/mag ;
		}
		return v ;
	}

	public ComplexVectorBuilder divideRev(ComplexVectorBuilder v) {
		for(int i=0; i<size; i++) {
			v.re[i] = v.re[i] / x[i] ;
			v.im[i] = v.im[i] / x[i] ;
		}
		return v ;
	}

	/*----- negation ------*/

	@Override
	public RealVector negate() {
		if(re == null) { re = new double[size] ; }
		for(int i=0; i<size; i++) {
			re[i] = -x[i] ;
		}
		return new RealVector(re) ;
	}


	// support for stream API

	public Stream<RealNumber> stream() {
		return Stream.of(toArray()) ;
	}

	public DoubleStream doubleStream() {
		return Arrays.stream(x) ;
	}


}
