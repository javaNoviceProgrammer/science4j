package util4j.array;

import java.util.Arrays;

import util4j.array.NdArray.Shape;
import util4j.complex.Complex;
import util4j.math.AlgebraicEntity;

public class ComplexNdArray implements AlgebraicEntity<ComplexNdArray> {
	
	public final double[] dataRe ;
	public final double[] dataIm ;
	public final Shape shape ;
	
	public ComplexNdArray(double[] dataRe, double[] dataIm, Shape shape) {
		this.shape = shape ;
		this.dataRe = dataRe ;
		this.dataIm = dataIm ;
	}
	
	public ComplexNdArray(Shape shape) {
		this.shape = shape ;
		this.dataRe = new double[shape.length] ;
		this.dataIm = new double[shape.length] ;
	}
	
	public int dim() {
		return shape.dim ;
	}
	
	public Complex at(int... index) {
		return Complex.ofRect(dataRe[shape.index(index)], dataIm[shape.index(index)]) ;
	}
	
	public Complex get(int... index) {
		return Complex.ofRect(dataRe[shape.index(index)], dataIm[shape.index(index)]) ;
	}
	
	public void set(double valueRe, double valueIm, int... index) {
		dataRe[shape.index(index)] = valueRe ;
		dataIm[shape.index(index)] = valueIm ;
	}
	
	public void set(Complex value, int... index) {
		dataRe[shape.index(index)] = value.re() ;
		dataIm[shape.index(index)] = value.im() ;
	}
	
	public void setAll(double valueRe, double valueIm) {
		for(int i=0; i<shape.length; i++) {
			dataRe[i] = valueRe ;
			dataIm[i] = valueIm ;
		}
	}
	
	public void setAll(Complex value) {
		for(int i=0; i<shape.length; i++) {
			dataRe[i] = value.re() ;
			dataIm[i] = value.im() ;
		}
	}
	
	public Shape shape() {
		return shape ;
	}
	
	public double[] dataRe() {
		return dataRe ;
	}
	
	public double[] dataIm() {
		return dataIm ;
	}
	
	public ComplexNdArray reshape(Shape shape) {
		return new ComplexNdArray(dataRe.clone(), dataIm.clone(), shape) ;
	}
	
	@Override
	public String toString() {
		return "ComplexNdArray" + Arrays.toString(shape.size) ;
	}
	
	private void checkShape(ComplexNdArray a) {
		if(!shape.equals(a.shape)) 
			throw new IllegalArgumentException("Shapes don't match") ;
	}
	
	/*---------- identity -----------------*/

	@Override
	public ComplexNdArray zero() {
		return new ComplexNdArray(shape) ;
	}

	@Override
	public ComplexNdArray one() {
		ComplexNdArray array = new ComplexNdArray(shape) ;
		array.setAll(1.0, 0.0);
		return array ;
	}
	
	/*---------- addition -----------------*/

	@Override
	public ComplexNdArray add(double v) {
		ComplexNdArray array = new ComplexNdArray(shape) ;
		for(int i=0; i<array.shape.length; i++) {
			array.dataRe[i] = dataRe[i] + v ;
			array.dataIm[i] = dataIm[i] + v ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray add(ComplexNdArray v) {
		checkShape(v) ;
		ComplexNdArray array = new ComplexNdArray(shape) ;
		for(int i=0; i<array.shape.length; i++) {
			array.dataRe[i] = dataRe[i] + v.dataRe[i] ;
			array.dataIm[i] = dataIm[i] + v.dataIm[i] ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray subtract(double v) {
		ComplexNdArray array = new ComplexNdArray(shape) ;
		for(int i=0; i<array.shape.length; i++) {
			array.dataRe[i] = dataRe[i] - v ;
			array.dataIm[i] = dataIm[i] - v ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray subtract(ComplexNdArray v) {
		checkShape(v) ;
		ComplexNdArray array = new ComplexNdArray(shape) ;
		for(int i=0; i<array.shape.length; i++) {
			array.dataRe[i] = dataRe[i] - v.dataRe[i] ;
			array.dataIm[i] = dataIm[i] - v.dataIm[i] ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray multiply(double v) {
		ComplexNdArray array = new ComplexNdArray(shape) ;
		for(int i=0; i<array.shape.length; i++) {
			array.dataRe[i] = dataRe[i] * v ;
			array.dataIm[i] = dataIm[i] * v ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray multiply(ComplexNdArray v) {
		checkShape(v) ;
		ComplexNdArray array = new ComplexNdArray(shape) ;
		for(int i=0; i<array.shape.length; i++) {
			array.dataRe[i] = dataRe[i] * v.dataRe[i] - dataIm[i] * v.dataIm[i] ;
			array.dataIm[i] = dataRe[i] * v.dataIm[i] + dataIm[i] * v.dataRe[i] ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray divide(double v) {
		ComplexNdArray array = new ComplexNdArray(shape) ;
		for(int i=0; i<array.shape.length; i++) {
			array.dataRe[i] = dataRe[i] / v ;
			array.dataIm[i] = dataIm[i] / v ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray divideRev(double v) {
		ComplexNdArray array = new ComplexNdArray(shape) ;
		double mag ;
		for(int i=0; i<array.shape.length; i++) {
			mag = dataRe[i]*dataRe[i] + dataIm[i]*dataIm[i] ;
			array.dataRe[i] = v * dataRe[i]/mag ;
			array.dataIm[i] = - v* dataIm[i]/mag ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray divide(ComplexNdArray v) {
		ComplexNdArray array = new ComplexNdArray(shape) ;
		double mag ;
		for(int i=0; i<array.shape.length; i++) {
			mag = v.dataRe[i]*v.dataRe[i] + v.dataIm[i]*v.dataIm[i] ;
			array.dataRe[i] = (dataRe[i] * v.dataRe[i] + dataIm[i] * v.dataIm[i])/mag ;
			array.dataIm[i] = (-dataRe[i] * v.dataIm[i] + dataIm[i] * v.dataRe[i])/mag ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray divideRev(ComplexNdArray v) {
		ComplexNdArray array = new ComplexNdArray(shape) ;
		double mag ;
		for(int i=0; i<array.shape.length; i++) {
			mag = dataRe[i]*dataRe[i] + dataIm[i]*dataIm[i] ;
			array.dataRe[i] = (v.dataRe[i] * dataRe[i] + v.dataIm[i] * dataIm[i])/mag ;
			array.dataIm[i] = (-v.dataRe[i] * dataIm[i] + v.dataIm[i] * dataRe[i])/mag ;
		}
		return array ;
	}

	@Override
	public ComplexNdArray negate() {
		ComplexNdArray array = new ComplexNdArray(shape) ;
		for(int i=0; i<array.shape.length; i++) {
			array.dataRe[i] = -dataRe[i] ;
			array.dataIm[i] = -dataIm[i] ;
		}
		return array ;
	}
	
}
