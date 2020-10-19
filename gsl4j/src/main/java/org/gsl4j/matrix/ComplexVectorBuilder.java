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
	public void set(int index, double z) {
		re[index] = z ;
		im[index] = 0.0 ;
	}

	@Override
	public void setAll(double z) {
		Arrays.fill(re, z);
		Arrays.fill(im, 0.0);
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
	public ComplexAlgebraVector add(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector addRev(double v) {
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
	public ComplexAlgebraVector subtract(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector subtractRev(double v) {
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
	public ComplexAlgebraVector multiply(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector multiplyRev(double v) {
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
	public ComplexAlgebraVector divide(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector divideRev(double v) {
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
	public void set(int index, ComplexNumber z) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAll(ComplexNumber z) {
		// TODO Auto-generated method stub

	}

	@Override
	public ComplexAlgebraVector conjugate() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ComplexAlgebraVector apply(ComplexMathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(AlgebraVector v) {
		// TODO Auto-generated method stub

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
	public void set(int index, RealNumber z) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAll(RealNumber z) {
		// TODO Auto-generated method stub

	}

	@Override
	public ComplexAlgebraVector add(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector addRev(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector add(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector addRev(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector subtract(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector subtractRev(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector subtract(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector subtractRev(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector multiply(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector multiplyRev(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector multiply(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector multiplyRev(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector divide(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector divideRev(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector divide(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector divideRev(ComplexNumber v) {
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

	@Override
	public ComplexAlgebraVector negate() {
		// TODO Auto-generated method stub
		return null;
	}



}
