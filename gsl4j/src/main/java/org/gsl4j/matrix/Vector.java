package org.gsl4j.matrix;

import org.gsl4j.OperatorOverloading;
import org.gsl4j.util.NativeLibraryLoader;

public class Vector implements OperatorOverloading<Vector> {

	static {
		NativeLibraryLoader.loadLibraries();
	}

	double[] x ;
	int dim ;

	public Vector(double[] x) {
		this.x = x ;
		this.dim = x.length ;
	}

	public double at(int index) {
		return x[index] ;
	}

	public double[] toArray() {
		return x ;
	}

	/*------------- Operator Overloading ----------------*/

	@Override
	public Vector add(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector addRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector add(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector addRev(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector subtract(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector subtractRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector subtract(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector subtractRev(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector multiply(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector multiplyRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector multiply(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector multiplyRev(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector divide(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector divideRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector divide(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector divideRev(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector negate() {
		// TODO Auto-generated method stub
		return null;
	}

}
