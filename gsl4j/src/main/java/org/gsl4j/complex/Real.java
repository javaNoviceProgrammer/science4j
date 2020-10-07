package org.gsl4j.complex;

import java.io.Serializable;

import org.gsl4j.OperatorOverloading;

public class Real implements Serializable, OperatorOverloading<Real> {

	private static final long serialVersionUID = 1L;
	public static double EPSILON = 1e-10 ;

	double x ;

	public Real(double v) {
		this.x = v ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Real other = (Real) obj;
		if (Math.abs(x-other.x)>EPSILON)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(x) ;
	}

	public static Real valueOf(double v) {
		return new Real(v) ;
	}

	public static Real valueOf(Double v) {
		return new Real(v) ;
	}

	public static Real valueOf(Real v) {
		return new Real(v.x) ;
	}

	@Override
	public Real add(double v) {
		return new Real(x+v) ;
	}

	@Override
	public Real addRev(double v) {
		return new Real(v+x) ;
	}

	@Override
	public Real add(Real v) {
		return new Real(this.x+v.x) ;
	}

	@Override
	public Real addRev(Real v) {
		return new Real(v.x+this.x) ;
	}

	@Override
	public Real subtract(double v) {
		return new Real(x-v) ;
	}

	@Override
	public Real subtractRev(double v) {
		return new Real(v-x) ;
	}

	@Override
	public Real subtract(Real v) {
		return new Real(this.x-v.x) ;
	}

	@Override
	public Real subtractRev(Real v) {
		return new Real(v.x-this.x) ;
	}

	@Override
	public Real multiply(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real multiplyRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real multiply(Real v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real multiplyRev(Real v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real divide(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real divideRev(double v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real divide(Real v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real divideRev(Real v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Real negate() {
		return new Real(-x) ;
	}

	public static void main(String[] args) {
		Real r = 1.1 ;
		System.out.println(r);
	}

}
