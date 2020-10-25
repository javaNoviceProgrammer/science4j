package org.simd4j.base;

public class ArrayShape {

	/*
	 *  shape[0] = number of rows (dim1)
	 *  shape[1] = number of columns (dim2)
	 *  shape[2] = number of dim3
	 *  shape[3] = number of dim4
	 *  ...
	 */
	int[] shape ;
	int dim ;

	public ArrayShape(int... shape) {
		this.dim = shape.length ;
		this.shape = shape ;
	}

	public void reshape(int... shape) {
		this.shape = shape ;
	}

	public int getIndex(int... shape) {
		return 0 ;
	}

}
