package org.gsl4j.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;



public class RealVectorBuilder implements RealAlgebraVector, VectorBuilder {

	double[] x ;
	double[] im ;
	int size ;


	public RealVectorBuilder(double... x) {
		this.x = x ;
		this.size = x.length ;
	}

	public RealVectorBuilder(int size) {
		this.size = size ;
		this.x = new double[size] ;
	}

	public RealVectorBuilder(RealAlgebraVector v) {
		this.size = v.size() ;
		this.x = v.re() ;
	}

















	@Override
	public int size() {
		return size ;
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
	public void set(int index, double z) {
		x[index] = z ;
	}

	@Override
	public void setAll(double z) {
		Arrays.fill(x, z) ;
	}

	@Override
	public void set(AlgebraVector v) {
		for(int i=0; i<size; i++) {
			x[i] = v.re()[i] ; // ignore imaginary part
		}
	}

	@Override
	public AlgebraVector apply(Function<ComplexNumber, ComplexNumber> func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector applyComplex(ComplexMathFunction func) {
		// TODO Auto-generated method stub
		return null;
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
	public RealNumber at(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealNumber get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealNumber[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RealNumber> toList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int index, RealNumber z) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAll(RealNumber z) {
		// TODO Auto-generated method stub

	}

	@Override
	public RealAlgebraVector conjugate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder getBuilder() {
		return this ;
	}

	@Override
	public VectorBuilder cachedBuilder() {
		return this ;
	}

	@Override
	public RealAlgebraVector applyReal(MathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int index, ComplexNumber z) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAll(ComplexNumber z) {
		// TODO Auto-generated method stub

	}



}
