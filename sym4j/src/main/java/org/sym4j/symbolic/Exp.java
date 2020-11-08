package org.sym4j.symbolic;

import org.sym4j.math.SymMath;
import org.sym4j.symbolic.symbols.SymReal;
import org.sym4j.symbolic.utils.Utils;


public class Exp extends Pow {
	public Exp(Expr arg) {
		super(SymMath.E, arg);
		//String displayExp = String.format("{%s}", this.arg2);
		String displayExp = String.format("%s", this.arg2);
		if(arg instanceof SymReal<?>) {
			SymReal<?> realExp = (SymReal<?>)arg;
			if(realExp.isInteger()) {
				displayExp = String.format("%d", realExp.getIntValue());
			}
			//if(realExp.isNegative())
			//	displayExp = "{"+displayExp+"}";
		}
		//label = "e^" + displayExp + "";
		label = "pow(e," + displayExp + ")";
		sortKey = "epower"+String.valueOf(displayExp);
	}

	public String toString() {
		return label;
	}

	public static Expr simplifiedIns(Expr expr) {
		return new Exp(expr);
	}

	@Override
	public Expr simplify() {
		return this;
	}

	@Override
	public boolean symEquals(Expr other) {
		if(other instanceof Exp) {
			return Utils.symCompare(this.arg2, other.args()[0]);
		}
		return false;
	}

	@Override
	public Expr diff(Expr expr) {
		return this.multiply(arg2.diff(expr));
	}
}
