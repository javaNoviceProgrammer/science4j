package org.sym4j.symbolic.symbols;

import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.TypeInfo;
import org.sym4j.symbolic.utils.Utils;

/**
 * TODO
 *
 */
public class SymComplex extends Expr {

	Expr real;
	Expr imaginary;

	public SymComplex(Expr re, Expr im) {
		real = re;
		imaginary = im;
	}

	@Override
	public Expr diff(Expr expr) {
		return new SymComplex(real.diff(expr), imaginary.diff(expr));
	}

	public String toString() {
//		return real.label + "+" + imaginary.label + "i";
		return real.getLabel() + "+" + imaginary.getLabel() + "i";
	}

	@Override
	public Expr subs(Expr from, Expr to) {
		if(Utils.symCompare(this, from))
			return to;
		return new SymComplex(real.subs(from, to), imaginary.subs(from, to));
	}

	@Override
	public Expr simplify() {
		return new SymComplex(real.simplify(), imaginary.simplify());
	}

	@Override
	public boolean symEquals(Expr other) {
		if(other instanceof SymComplex) {
			SymComplex o = (SymComplex)other;
			if(real.symEquals(o.real) && imaginary.symEquals(o.imaginary))
				return true;
		}
		return false;
	}

	@Override
	public TypeInfo getTypeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expr[] args() {
		return new Expr[] {real, imaginary};
	}

	@Override
	public void updateLabel() {
		// TODO Auto-generated method stub

	}

}
