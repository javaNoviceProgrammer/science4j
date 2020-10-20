package org.gsl4j.vector;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;


public class ComplexVector implements ComplexAlgebraVector {

	ComplexBuilder cb ;
	ComplexVectorBuilder cvb ;

	public static boolean debug = false ;
	public static long count = 0 ;

	double[] re ;
	double[] im ;
	int size ;

	// auxiliary arrays for math operations
	double[] x ;
	double[] y ;

	public ComplexVector(double[] re, double[] im) {
		if(re.length != im.length) {
			throw new IllegalArgumentException("re[] and im[] arrays must have the same size") ;
		}
		this.size = re.length ;
		this.re = re.clone() ; // make new copies
		this.im = im.clone() ; // make new copies

		if(debug) {
			count++ ;
			System.out.println("ComplexVector count = " + count);
		}
	}

	public ComplexVector(double... re) {
		this.size = re.length ;
		this.re = re.clone() ;       // make new copies
		this.im = new double[size] ; // make new copies

		if(debug) {
			count++ ;
			System.out.println("ComplexVector count = " + count);
		}
	}

	public ComplexVector(int size) {
		this.size = size ;
		this.re = new double[size] ;
		this.im = new double[size] ;

		if(debug) {
			count++ ;
			System.out.println("ComplexVector count = " + count);
		}
	}

	@Override
	public String toString() {
		// initialize complex builder
		if(cb != null) { cb.reset(); }
		else { cb = new ComplexBuilder() ; }
		// create a strign builder
		StringBuilder sb = new StringBuilder() ;
		sb.append("[") ;
		for(int i=0; i<size-1; i++) {
			cb.set(re[i], im[i]) ;
			sb.append(cb.toString()).append(", ") ;
			cb.reset();
		}
		cb.add(re[size-1], im[size-1]) ;
		sb.append(cb.toString());
		sb.append("]") ;
		return sb.toString() ;
	}

	@Override
	public ComplexNumber[] toArray() {
		ComplexNumber[] a = new ComplexNumber[size] ;
		for(int i=0; i<size; i++) {
			a[i] = Complex.ofRect(re[i], im[i]) ;
		}
		return a ;
	}

	@Override
	public List<ComplexNumber> toList() {
		List<ComplexNumber> list = new ArrayList<>(size) ;
		for(int i=0; i<size; i++) {
			list.add(Complex.ofRect(re[i], im[i])) ;
		}
		return list ;
	}

	@Override
	public int size() {
		return size ;
	}

	@Override
	public ComplexNumber at(int index) {
		return Complex.ofRect(re[index], im[index]) ;
	}

	@Override
	public ComplexNumber get(int index) {
		return Complex.ofRect(re[index], im[index]) ;
	}

	@Override
	public double atReal(int index) {
		return re[index] ;
	}

	@Override
	public double atImag(int index) {
		return im[index] ;
	}

	@Override
	public double[] re() {
		return re.clone() ;
	}

	@Override
	public double[] im() {
		return im.clone() ;
	}

	@Override
	public ComplexVector conjugate() {
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			y[i] = -im[i] ;
		}
		return new ComplexVector(re, y) ;
	}

	@Override
	public ComplexVectorBuilder getBuilder() {
		return new ComplexVectorBuilder(this) ;
	}

	@Override
	public ComplexVectorBuilder cachedBuilder() {
		if (cvb == null) { cvb = new ComplexVectorBuilder(this) ; }
		else { cvb.set(this) ; }
		return cvb ;
	}


	@Override
	public ComplexVector apply(Function<ComplexNumber, ComplexNumber> func) {
		// initialize the complex builder
		if(cb != null) { cb.reset(); }
		else { cb = new ComplexBuilder() ; }
		// apply the function to the complex builder
		for(int i=0; i<size; i++) {
			cb.set(func.apply(cb));
			re[i] = cb.re() ;
			im[i] = cb.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector applyReal(MathFunction func) {
		for(int i=0; i<size; i++) {
			re[i] = func.value(re[i]) ; // apply to re and im parts separately
			im[i] = func.value(im[i]) ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector applyComplex(ComplexMathFunction func) {
		// initialize the complex builder
		if(cb != null) { cb.reset(); }
		else { cb = new ComplexBuilder() ; }
		// apply the function to the complex builder
		for(int i=0; i<size; i++) {
			cb.set(func.value(cb));
			re[i] = cb.re() ;
			im[i] = cb.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public ComplexVector apply(ComplexMathFunction func) {
		// initialize the complex builder
		if(cb != null) { cb.reset(); }
		else { cb = new ComplexBuilder() ; }
		// apply the function to the complex builder
		for(int i=0; i<size; i++) {
			cb.set(func.value(cb));
			re[i] = cb.re() ;
			im[i] = cb.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	//*********** support for algebraic operations *************

	/*----- addition ------*/

	@Override
	public ComplexVector add(double v) {
		if(x==null) { x = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] + v ;
		}
		return new ComplexVector(x, im) ;
	}

	@Override
	public ComplexVector addRev(double v) {
		if(x==null) { x = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v + re[i] ;
		}
		return new ComplexVector(x, im) ;
	}

	@Override
	public ComplexVector add(RealNumber v) {
		if(x==null) { x = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] + v.re() ;
		}
		return new ComplexVector(x, im) ;
	}

	@Override
	public ComplexVector addRev(RealNumber v) {
		if(x==null) { x = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v.re() + re[i] ;
		}
		return new ComplexVector(x, im) ;
	}

	@Override
	public ComplexVector add(ComplexNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] + v.re() ;
			y[i] = im[i] + v.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector addRev(ComplexNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v.re() + re[i] ;
			y[i] = v.im() + im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector add(AlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] + v.atReal(i) ;
			y[i] = im[i] + v.atImag(i) ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector addRev(AlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v.atReal(i) + re[i] ;
			y[i] = v.atImag(i) + im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector add(ComplexAlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] + v.atReal(i) ;
			y[i] = im[i] + v.atImag(i) ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector addRev(ComplexAlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v.atReal(i) + re[i] ;
			y[i] = v.atImag(i) + im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	/*----- subtraction ------*/

	@Override
	public ComplexVector subtract(double v) {
		if(x==null) { x = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] - v ;
		}
		return new ComplexVector(x, im) ;
	}

	@Override
	public ComplexVector subtractRev(double v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v - re[i] ;
			y[i] = -im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector subtract(RealNumber v) {
		if(x==null) { x = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] - v.re() ;
		}
		return new ComplexVector(x, im) ;
	}

	@Override
	public ComplexVector subtractRev(RealNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v.re() - re[i] ;
			y[i] = -im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector subtract(ComplexNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] - v.re() ;
			y[i] = im[i] - v.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector subtractRev(ComplexNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v.re() - re[i] ;
			y[i] = v.im() - im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector subtract(AlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] - v.atReal(i) ;
			y[i] = im[i] - v.atImag(i) ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector subtractRev(AlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v.atReal(i) - re[i] ;
			y[i] = v.atImag(i) - im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector subtract(ComplexAlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] - v.atReal(i) ;
			y[i] = im[i] - v.atImag(i) ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector subtractRev(ComplexAlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v.atReal(i) - re[i] ;
			y[i] = v.atImag(i) - im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	/*----- multiplication ------*/

	@Override
	public ComplexVector multiply(double v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] * v ;
			y[i] = im[i] * v ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector multiplyRev(double v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v * re[i] ;
			y[i] = v * im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector multiply(RealNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] * v.re() ;
			y[i] = im[i] * v.re() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector multiplyRev(RealNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = v.re() * re[i] ;
			y[i] = v.re() * im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector multiply(ComplexNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.multiply(v) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector multiplyRev(ComplexNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.multiplyRev(v) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector multiply(AlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.multiply(v.atReal(i), v.atImag(i)) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector multiplyRev(AlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.multiplyRev(v.atReal(i), v.atImag(i)) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector multiply(ComplexAlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.multiply(v.atReal(i), v.atImag(i)) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector multiplyRev(ComplexAlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.multiplyRev(v.atReal(i), v.atImag(i)) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	/*----- division ------*/

	@Override
	public ComplexVector divide(double v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] / v ;
			y[i] = im[i] / v ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector divideRev(double v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.divideRev(v) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector divide(RealNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = re[i] / v.re() ;
			y[i] = im[i] / v.re() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector divideRev(RealNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.divideRev(v.re(), v.im()) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector divide(ComplexNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.divide(v.re(), v.im()) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector divideRev(ComplexNumber v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.divideRev(v.re(), v.im()) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}


	@Override
	public ComplexVector divide(AlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.divide(v.atReal(i), v.atImag(i)) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector divideRev(AlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.divideRev(v.atReal(i), v.atImag(i)) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector divide(ComplexAlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.divide(v.atReal(i), v.atImag(i)) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	@Override
	public ComplexVector divideRev(ComplexAlgebraVector v) {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		if(cb==null) { cb = new ComplexBuilder() ; }
		for(int i=0; i<size; i++) {
			cb.set(re[i], im[i]) ;
			cb.divideRev(v.atReal(i), v.atImag(i)) ;
			x[i] = cb.re() ;
			y[i] = cb.im() ;
		}
		return new ComplexVector(x, y) ;
	}

	/*----- negation ------*/

	@Override
	public ComplexVector negate() {
		if(x==null) { x = new double[size] ; }
		if(y==null) { y = new double[size] ; }
		for(int i=0; i<size; i++) {
			x[i] = -re[i] ;
			y[i] = -im[i] ;
		}
		return new ComplexVector(x, y) ;
	}

}
