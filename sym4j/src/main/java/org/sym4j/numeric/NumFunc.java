package org.sym4j.numeric;

import org.sym4j.symbolic.Expr;

/**
 * An abstract class which can be used to define your own function
 * For example:
		NumFunc<Double> myFun = new NumFunc<Double>() {
			@Override
			public Double apply(double ...args) {
				double x = args[0], y = args[1];
				return x*x + y*y;
			}
		};
 *
 * @param <T>
 */
public abstract class NumFunc<T> extends Expr {

	/**
	 * The derivative class should implement this method
	 * @param args
	 * @return
	 */
	public abstract T apply(double ...args);

	@Override
	public Expr diff(Expr expr) {
		throw new UnsupportedOperationException("");
	}

	@Override
	public Expr simplify() {
		return this;
	}

	@Override
	public boolean symEquals(Expr other) {
		return false;
	}
}
