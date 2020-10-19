package org.gsl4j.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;



public class ComplexVectorBuilder implements ComplexAlgebraVector, VectorBuilder {

	double[] re ;
	double[] im ;
	int size ;

	public ComplexVectorBuilder(double[] re, double[] im) {
		if(re.length != im.length) {
			throw new IllegalArgumentException("re[] and im[] arrays must have the same size") ;
		}
		this.re = re ;
		this.im = im ;
		this.size = re.length ;
	}

	public ComplexVectorBuilder(int size) {
		this.size = size ;
		this.re = new double[size] ;
		this.im = new double[size] ;
	}

	public ComplexVectorBuilder(AlgebraVector v) {
		this.size = v.size() ;
		this.re = v.re() ;
		this.im = v.im() ;
	}












	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] re() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] im() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexNumber at(int index) {
		return new ComplexBuilder(re[index], im[index]) ;
	}

	@Override
	public ComplexNumber get(int index) {
		return new ComplexBuilder(re[index], im[index]) ;
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
	public void reset() {
		for(int i=0; i<size; i++) {
			re[i] = 0.0 ;
			im[i] = 0.0 ;
		}
	}




	@Override
	public AlgebraVector apply(Function<ComplexNumber, ComplexNumber> func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector applyReal(MathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector applyComplex(ComplexMathFunction func) {
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
	public VectorBuilder set(int index, double z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder setAll(double z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder set(int index, RealNumber z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder setAll(RealNumber z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder set(int index, double re, double im) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder setAll(double re, double im) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder set(int index, ComplexNumber z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder setAll(ComplexNumber z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder add(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder addRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder add(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder addRev(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder add(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder addRev(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder add(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder addRev(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder subtract(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder subtractRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder subtract(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder subtractRev(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder subtract(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder subtractRev(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder subtract(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder subtractRev(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder multiply(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder multiplyRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder multiply(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder multiplyRev(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder multiply(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder multiplyRev(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder multiply(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder multiplyRev(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder divide(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder divideRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder divide(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder divideRev(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder divide(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder divideRev(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder divide(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder divideRev(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder negate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexNumber[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComplexNumber> toList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector conjugate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder getBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorBuilder cachedBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector apply(ComplexMathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector add(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector addRev(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector subtract(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector subtractRev(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector multiply(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector multiplyRev(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector divide(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector divideRev(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}




}
