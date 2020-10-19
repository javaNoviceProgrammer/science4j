package org.gsl4j.matrix;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;



public class RealVectorBuilder implements RealAlgebraVector, VectorBuilder {

	final double[] x ;
	final int size ;

	double[] im ;


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
	public String toString() {
		return Arrays.toString(x) ;
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
	public RealNumber at(int index) {
		return Real.of(x[index]) ;
	}

	@Override
	public RealNumber get(int index) {
		return Real.of(x[index]) ;
	}

	@Override
	public double atIndex(int index) {
		return x[index] ;
	}

	@Override
	public double atReal(int index) {
		return x[index] ;
	}

	@Override
	public double atImag(int index) {
		return 0.0 ;
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
	public ComplexVectorBuilder add(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder addRev(ComplexNumber v) {
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
	public ComplexVectorBuilder subtract(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder subtractRev(ComplexNumber v) {
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
	public ComplexVectorBuilder divide(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divideRev(ComplexNumber v) {
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
	public void reset() {
		// TODO Auto-generated method stub

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
	public void set(int index, double re, double im) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAll(double re, double im) {
		// TODO Auto-generated method stub

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
	public RealVectorBuilder negate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealNumber[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] toDoubleArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RealNumber> toList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector conjugate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder getBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder cachedBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealAlgebraVector applyReal(MathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder add(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder addRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder add(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder addRev(RealNumber v) {
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
	public RealVectorBuilder subtract(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder subtractRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder subtract(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder subtractRev(RealNumber v) {
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
	public RealVectorBuilder multiply(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder multiplyRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder multiply(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder multiplyRev(RealNumber v) {
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
	public RealVectorBuilder divide(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder divideRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder divide(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealVectorBuilder divideRev(RealNumber v) {
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





}
