package org.sym4j.symbolic.funcs;

import org.sym4j.symbolic.Expr;

public class Log10 extends Log {

	public Log10(Expr expr) {
		super(Expr.valueOf(10), expr);
		updateLabel();
	}

	public static Expr simplifiedIns(Expr expr) {
		return new Log10(expr);
	}

	@Override
	public void updateLabel() {
		label = "log10(" + arg2 + ")";
		sortKey = label;
		latexLabel = "\\log_{10}{(" + arg2 + ")}";
	}

}
