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




}
