package org.gsl4j.matrix;


import java.util.List;
import java.util.function.Function;

import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;


public class ComplexVector implements ComplexAlgebraVector {

	double[] re ;
	double[] im ;
	int size ;

	ComplexBuilder temp ;
	ComplexVectorBuilder cvb ;

	public ComplexVector(double[] re, double[] im) {
		if(re.length != im.length) {
			throw new IllegalArgumentException("re[] and im[] arrays must have the same size") ;
		}
		this.size = re.length ;
		this.re = re.clone() ; // make new copies
		this.im = im.clone() ; // make new copies
	}

	public ComplexVector(double... re) {
		this.size = re.length ;
		this.re = re.clone() ; // make new copies
		this.im = new double[size] ; // make new copies
	}

	public ComplexVector(int size) {
		this.size = size ;
		this.re = new double[size] ;
		this.im = new double[size] ;
	}

	@Override
	public String toString() {
		// initialize complex builder
		if(temp != null) {
			temp.reset();
		} else {
			temp = new ComplexBuilder() ;
		}
		StringBuilder sb = new StringBuilder() ;
		sb.append("[") ;
		for(int i=0; i<size-1; i++) {
			temp.add(re[i], im[i]) ;
			sb.append(temp.toString()).append(", ") ;
			temp.reset();
		}
		temp.add(re[size-1], im[size-1]) ;
		sb.append(temp.toString());
		sb.append("]") ;
		return sb.toString() ;
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
		return re ;
	}

	@Override
	public double[] im() {
		return im ;
	}

	@Override
	public ComplexVectorBuilder getBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder cachedBuilder() {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public AlgebraVector apply(Function<ComplexNumber, ComplexNumber> func) {
		// initialize the complex builder
		if(temp != null) {
			temp.reset();
		} else {
			temp = new ComplexBuilder() ;
		}
		// apply the function to the complex builder
		for(int i=0; i<size; i++) {
			temp.set(func.apply(temp));
			re[i] = temp.re() ;
			im[i] = temp.im() ;
		}
		return new ComplexVector(re, im) ;
	}

	@Override
	public AlgebraVector applyReal(MathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlgebraVector applyComplex(ComplexMathFunction func) {
		// initialize the complex builder
		if(temp != null) {
			temp.reset();
		} else {
			temp = new ComplexBuilder() ;
		}
		// apply the function to the complex builder
		for(int i=0; i<size; i++) {
			temp.set(func.value(temp));
			re[i] = temp.re() ;
			im[i] = temp.im() ;
		}
		return new ComplexVector(re, im) ;
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
