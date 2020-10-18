package org.gsl4j.matrix;


import java.util.List;
import java.util.function.Function;

import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexNumber;
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
		this.re = re ;
		this.im = im ;
		this.size = re.length ;
	}

	public ComplexVector(int size) {
		this.size = size ;
		this.re = new double[size] ;
		this.im = new double[size] ;
	}

	@Override
	public String toString() {
		// initialize complex builder
//		ComplexBuilder z = new ComplexBuilder() ;
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
	public double[] re() {
		return re ;
	}

	@Override
	public double[] im() {
		return im ;
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
	public ComplexAlgebraVector getBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexAlgebraVector apply(ComplexMathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}










}
