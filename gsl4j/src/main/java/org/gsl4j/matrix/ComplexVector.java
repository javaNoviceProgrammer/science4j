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

	public ComplexVector(double[] re, double[] im) {
		if(re.length != im.length) {
			throw new IllegalArgumentException("re[] and im[] arrays must have the same size") ;
		}
		this.re = re ;
		this.im = im ;
		this.size = re.length ;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder() ;
		ComplexBuilder z = new ComplexBuilder() ;
		sb.append("[") ;
		for(int i=0; i<size-1; i++) {
			z.add(re[i], im[i]) ;
			sb.append(z.toString()).append(", ") ;
			z.reset();
		}
		z.add(re[size-1], im[size-1]) ;
		sb.append(z.toString());
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
	public void set(int index, double z) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAll(double z) {
		// TODO Auto-generated method stub

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
