package org.gsl4j.vector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.RealNumber;
import org.gsl4j.function.ComplexMathFunction;
import org.gsl4j.function.MathFunction;



public class ComplexVectorBuilder implements ComplexAlgebraVector, VectorBuilder {

	ComplexBuilder cb ;

	public static boolean debug = false ;
	public static long count = 0 ;

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

		if(debug) {
			count++ ;
			System.out.println("ComplexVectorBuilder count = " + count);
		}
	}

	public ComplexVectorBuilder(int size) {
		this.size = size ;
		this.re = new double[size] ;
		this.im = new double[size] ;

		if(debug) {
			count++ ;
			System.out.println("ComplexVectorBuilder count = " + count);
		}
	}

	public ComplexVectorBuilder(AlgebraVector v) {
		this.size = v.size() ;
		this.re = v.re() ;
		this.im = v.im() ;

		if(debug) {
			count++ ;
			System.out.println("ComplexVectorBuilder count = " + count);
		}
	}

	@Override
	public String toString() {
		// initialize complex builder
		if(cb != null) { cb.reset(); }
		else { cb = new ComplexBuilder() ; }
		// create a strign builder
		StringBuilder sb = new StringBuilder() ;
		sb.append("[") ;
		for(int i=0; i<size-1; i++) {
			cb.set(re[i], im[i]) ;
			sb.append(cb.toString()).append(", ") ;
			cb.reset();
		}
		cb.add(re[size-1], im[size-1]) ;
		sb.append(cb.toString());
		sb.append("]") ;
		return sb.toString() ;
	}

	@Override
	public ComplexNumber[] toArray() {
		ComplexNumber[] a = new ComplexNumber[size] ;
		for(int i=0; i<size; i++) {
			a[i] = Complex.ofRect(re[i], im[i]) ;
		}
		return a ;
	}

	@Override
	public List<ComplexNumber> toList() {
		List<ComplexNumber> list = new ArrayList<>(size) ;
		for(int i=0; i<size; i++) {
			list.add(Complex.ofRect(re[i], im[i])) ;
		}
		return list ;
	}

	@Override
	public ComplexVectorBuilder set(int index, double z) {
		re[index] = z ;
		return this ;
	}

	@Override
	public ComplexVectorBuilder setAll(double z) {
		for(int i=0; i<size; i++) {
			re[i] = z ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder set(int index, RealNumber z) {
		re[index] = z.re() ;
		return this ;
	}

	@Override
	public ComplexVectorBuilder setAll(RealNumber z) {
		for(int i=0; i<size; i++) {
			re[i] = z.re() ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder set(int index, double re, double im) {
		this.re[index] = re ;
		this.im[index] = im ;
		return this ;
	}

	@Override
	public ComplexVectorBuilder setAll(double re, double im) {
		for(int i=0; i<size; i++) {
			this.re[i] = re ;
			this.im[i] = im ;
		}
		return this ;
	}

	@Override
	public ComplexVectorBuilder set(int index, ComplexNumber z) {
		this.re[index] = z.re() ;
		this.im[index] = z.im() ;
		return this ;
	}

	@Override
	public ComplexVectorBuilder setAll(ComplexNumber z) {
		for(int i=0; i<size; i++) {
			this.re[i] = z.re() ;
			this.im[i] = z.im() ;
		}
		return this ;
	}

	public ComplexVectorBuilder set(ComplexVector v) {
		for(int i=0; i<size; i++) {
			re[i] = v.re[i] ;
			im[i] = v.im[i] ;
		}
		return this ;
	}

	@Override
	public int size() {
		return size ;
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
	public ComplexVectorBuilder apply(Function<ComplexNumber, ComplexNumber> func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder applyReal(MathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder applyComplex(ComplexMathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}


	//*********** support for algebraic operations *************

	/*----- addition ------*/

	@Override
	public ComplexVectorBuilder add(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder addRev(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}



	/*----- subtraction ------*/

	@Override
	public ComplexVectorBuilder subtract(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder subtractRev(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}




	/*----- multiplication ------*/

	@Override
	public ComplexVectorBuilder multiply(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiplyRev(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divide(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divideRev(AlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}





	/*----- division ------*/


	@Override
	public ComplexVectorBuilder add(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder addRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder add(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder addRev(RealNumber v) {
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
	public ComplexVectorBuilder add(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder addRev(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder subtract(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder subtractRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder subtract(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder subtractRev(RealNumber v) {
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
	public ComplexVectorBuilder subtract(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder subtractRev(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiply(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiplyRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiply(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiplyRev(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiply(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiplyRev(ComplexNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiply(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiplyRev(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divide(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divideRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divide(RealNumber v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divideRev(RealNumber v) {
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
	public ComplexVectorBuilder divide(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divideRev(VectorBuilder v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder negate() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ComplexVectorBuilder conjugate() {
		// TODO Auto-generated method stub
		return null;
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
	public ComplexVectorBuilder apply(ComplexMathFunction func) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder add(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder addRev(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder subtract(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder subtractRev(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiply(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder multiplyRev(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divide(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexVectorBuilder divideRev(ComplexAlgebraVector v) {
		// TODO Auto-generated method stub
		return null;
	}




}
