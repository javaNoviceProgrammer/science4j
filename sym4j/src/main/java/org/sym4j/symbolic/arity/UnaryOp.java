package org.sym4j.symbolic.arity;

import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.TypeInfo;

public abstract class UnaryOp extends Expr {
	public Expr arg;

	public UnaryOp(Expr arg) {
		this.arg = arg;
		setSimplifyOps(
				arg.getSimplifyOps()
				);
	}

	@Override
	public Expr[] args() {
		return new Expr[] { arg };
	}

	@Override
	public TypeInfo getTypeInfo() {
		return arg.getTypeInfo();
	}

	public Expr setArg(int index, Expr arg) {
		this.arg = arg;
		updateLabel();
		return this;
	}

}
