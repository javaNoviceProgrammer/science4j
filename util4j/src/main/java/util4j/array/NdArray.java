package util4j.array;

import java.util.Arrays;

import util4j.math.AlgebraicEntity;

public class NdArray implements AlgebraicEntity<NdArray> {
	
	double[] data ; // can be any dimension, layout: dim1 --> dim2 --> dim3 --> ...
	Shape shape ;
	
	public NdArray(double[] data, Shape shape) {
		this.shape = shape ;
		this.data = data ;
	}
	
	public NdArray(Shape shape) {
		this.shape = shape ;
		this.data = new double[shape.length] ;
	}
	
	public int dim() {
		return shape.dim ;
	}
	
	public double at(int... index) {
		return data[shape.index(index)];
	}
	
	public double get(int... index) {
		return data[shape.index(index)] ;
	}
	
	public void set(double value, int... index) {
		data[shape.index(index)] = value ;
	}
	
	public Shape shape() {
		return shape ;
	}
	
	public double[] data() {
		return data ;
	}
	
	public NdArray reshape(Shape shape) {
		return new NdArray(data.clone(), shape) ;
	}
	
	@Override
	public String toString() {
		return "NdArray" + Arrays.toString(shape.size) ;
	}

	/*---------- Shape Class (unmodifiable) -----------------*/
	
	public static class Shape { 
		
		public final int dim ;
		public final int length ;
		public final int[] size ;
		
		public Shape(int... size) {
			dim = size.length ;
			this.size = size ;
			int len = 1 ;
			if(dim==1) {
				len = size[0] ;
			}
			else {
				for(int i=0; i<dim; i++)
					len *= size[i] ;
			}
			this.length = len ;
		}
		
		public static Shape of(int... size) {
			return new Shape(size) ;
		}
		
		public int index(int... coordinate) {
			if(coordinate.length != dim)
				throw new IllegalArgumentException("dimensions don't match") ;
			for(int i=0; i<dim; i++) {
				if(coordinate[i] >= size[i])
					throw new ArrayIndexOutOfBoundsException(String.format("index[%d]=%d is out of range", i, coordinate[i])) ;
			}
			int idx = 0 ;
			for(int i=0; i<dim-1; i++) {
				idx += coordinate[i]*size[i+1] ;
			}
			idx += coordinate[dim-1] ;
			return idx ;
		}

		@Override
		public String toString() {
			return "Shape" + Arrays.toString(size) ;
		}
		
	}
	
	/*---------- identity -----------------*/

	@Override
	public NdArray zero() {
		return new NdArray(shape) ;
	}

	@Override
	public NdArray one() {
		return null;
	}
	
	/*---------- addition -----------------*/

	@Override
	public NdArray add(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray add(NdArray v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray subtract(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray subtract(NdArray v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray multiply(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray multiply(NdArray v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray divide(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray divideRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray divide(NdArray v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray divideRev(NdArray v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NdArray negate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
