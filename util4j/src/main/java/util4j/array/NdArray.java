package util4j.array;

import java.util.Arrays;

import util4j.math.AlgebraicEntity;

public class NdArray implements AlgebraicEntity<NdArray> {
	
	public final double[] data ; // can be any dimensions, layout: dim1 --> dim2 --> dim3 --> ...
	public final Shape shape ;
	
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
	
	public void setAll(double value) {
		for(int i=0; i<data.length; i++) {
			data[i] = value ;
		}
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + dim;
			result = prime * result + length;
			result = prime * result + Arrays.hashCode(size);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Shape other = (Shape) obj;
			if (dim != other.dim)
				return false;
			if (length != other.length)
				return false;
			if (!Arrays.equals(size, other.size))
				return false;
			return true;
		}
		
	}
	
	private void checkShape(NdArray a) {
		if(!shape.equals(a.shape)) 
			throw new IllegalArgumentException("Shapes don't match") ;
	}
	
	/*---------- identity -----------------*/

	@Override
	public NdArray zero() {
		return new NdArray(shape) ;
	}

	@Override
	public NdArray one() {
		NdArray array = new NdArray(shape) ;
		array.setAll(1.0);
		return array ;
	}
	
	/*---------- addition -----------------*/

	@Override
	public NdArray add(double v) {
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = data[i] + v ;
		}
		return array ;
	}

	@Override
	public NdArray add(NdArray v) {
		checkShape(v) ;
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = data[i] + v.data[i] ;
		}
		return array ;
	}

	@Override
	public NdArray subtract(double v) {
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = data[i] - v ;
		}
		return array ;
	}

	@Override
	public NdArray subtract(NdArray v) {
		checkShape(v) ;
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = data[i] - v.data[i] ;
		}
		return array ;
	}

	@Override
	public NdArray multiply(double v) {
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = data[i] * v ;
		}
		return array ;
	}

	@Override
	public NdArray multiply(NdArray v) {
		checkShape(v) ;
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = data[i] * v.data[i] ;
		}
		return array ;
	}

	@Override
	public NdArray divide(double v) {
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = data[i] / v ;
		}
		return array ;
	}

	@Override
	public NdArray divideRev(double v) {
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = v / data[i] ;
		}
		return array ;
	}

	@Override
	public NdArray divide(NdArray v) {
		checkShape(v) ;
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = data[i] / v.data[i] ;
		}
		return array ;
	}

	@Override
	public NdArray divideRev(NdArray v) {
		checkShape(v) ;
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = v.data[i] / data[i] ;
		}
		return array ;
	}

	@Override
	public NdArray negate() {
		NdArray array = new NdArray(shape) ;
		for(int i=0; i<array.data.length; i++) {
			array.data[i] = -data[i] ;
		}
		return array ;
	}
	
}
