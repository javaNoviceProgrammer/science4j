package org.gsl4j.matrix;

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


public class RealVector implements RealAlgebraVector {

	RealBuilder rb ;
	RealVectorBuilder rvb ;

	ComplexBuilder cb ;
	ComplexVectorBuilder cvb ;


	double[] x ;
	double[] im ;
	int size ;

	// auxiliary array for math operations
	double[] y ;

	public RealVector(double... x) {
		this.size = x.length ;
		this.x = x ;
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
	public double[] re() {
		return x ;
	}

	@Override
	public double[] im() {
		if(im==null) {
			im = new double[size] ;
		}
		return im ;
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
		return Arrays.asList(toArray()) ;
	}

	@Override
	public void set(int index, RealNumber z) {
		x[index] = z.re() ;
	}


	@Override
	public void setAll(RealNumber z) {
		Arrays.fill(x, z.re()) ;
	}

	@Override
	public RealAlgebraVector conjugate() {
		return this ;
	}

	@Override
	public RealAlgebraVector getBuilder() {
		if(rvb==null) {
			rvb = new RealVectorBuilder(this) ;
		} else {

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
		if(y==null) {
			y = new double[size] ;
		}
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









	@Override
	public AlgebraVector add(double v) {
		if(y==null) {
			y = new double[size] ;
		}
		for(int i=0; i<size; i++) {
			y[i] = x[i] + v ;
		}
		return new RealVector(y) ;
	}

	@Override
	public AlgebraVector addRev(double v) {
		if(y==null) {
			y = new double[size] ;
		}
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = v + x[i] ;
		}
		return new RealVector(y) ;
	}

	@Override
	public AlgebraVector add(AlgebraVector v) {
		return null ;
	}

	@Override
	public AlgebraVector addRev(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}


	public RealVector add(RealVector v) {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = x[i] + v.x[i] ;
		}
		return new RealVector(y) ;
	}








	@Override
	public AlgebraVector subtract(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector subtractRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector subtract(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector subtractRev(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector multiply(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector multiplyRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector multiply(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector multiplyRev(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector divide(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector divideRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector divide(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector divideRev(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public RealAlgebraVector add(RealAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector addRev(RealAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector subtract(RealAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector subtractRev(RealAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector multiply(RealAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector multiplyRev(RealAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector divide(RealAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector divideRev(RealAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector negate() {
		double[] y = new double[size] ;
		for(int i=0; i<size; i++) {
			y[i] = -x[i] ;
		}
		return new RealVector(y) ;
	}





























}
