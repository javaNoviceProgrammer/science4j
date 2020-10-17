package org.gsl4j.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;
import org.gsl4j.util.NativeLibraryLoader;


public class RealVector implements RealAlgebraVector {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	ComplexBuilder temp ;
	ComplexVectorBuilder cvb ;

	double[] x ;
	int size ;

	public RealVector(double... x) {
		this.x = x ;
		this.size = x.length ;
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
		return new double[size] ; // initialized to 0.0
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
	public void set(int index, double z) {
		x[index] = z ;
	}

	@Override
	public void setAll(RealNumber z) {
		Arrays.fill(x, z.re()) ;
	}

	@Override
	public void setAll(double z) {
		Arrays.fill(x, z) ;
	}

	@Override
	public RealAlgebraVector conjugate() {
		return this ;
	}

	@Override
	public RealAlgebraVector getBuilder() {
		return null;
	}




	@Override
	public AlgebraVector apply(Function<ComplexNumber, ComplexNumber> func) {
		double[] re = new double[size] ;
		double[] im = new double[size] ;
		if (temp==null) {
			temp = new ComplexBuilder() ;
		}
		for(int i=0; i<size; i++) {
			temp.set(x[i], 0.0) ;
			temp.set(func.apply(temp)) ;
			re[i] = temp.re() ;
			im[i] = temp.im() ;
			temp.reset() ;
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
		if (temp==null) {
			temp = new ComplexBuilder() ;
		}
		for(int i=0; i<size; i++) {
			temp.set(x[i], 0.0) ;
			temp.set(func.value(temp)) ;
			re[i] = temp.re() ;
			im[i] = temp.im() ;
			temp.reset() ;
		}
		return new ComplexVector(re, im) ;
	}









	@Override
	public AlgebraVector add(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector addRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector add(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector addRev(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
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
	public AlgebraVector negate() {
		// TODO Auto-generated method stub
		return null;
	}





























}
