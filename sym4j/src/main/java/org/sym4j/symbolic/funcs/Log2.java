package org.sym4j.symbolic.funcs;

import org.sym4j.symbolic.Expr;

public class Log2 extends Log {

	public Log2(Expr expr) {
		super(Expr.valueOf(2), expr);
		updateLabel();
	}

	public static Expr simplifiedIns(Expr expr) {
		return new Log2(expr);
	}

	@Override
	public void updateLabel() {
		label = "log2(" + arg2 + ")";
		sortKey = label;
		latexLabel = "\\log_{2}{(" + arg2 + ")}";
	}
}
