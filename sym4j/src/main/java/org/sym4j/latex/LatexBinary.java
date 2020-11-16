package org.sym4j.latex;

import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.arity.BinaryOp;
import org.sym4j.symbolic.symbols.Symbol;

public abstract class LatexBinary extends BinaryOp {

	public LatexBinary(Expr arg1, Expr arg2) {
		super(arg1, arg2);
	}

	@Override
	public Expr simplify() {
		return this ;
	}

	@Override
	public boolean symEquals(Expr other) {
		return false;
	}

	@Override
	public Expr diff(Expr x) {
		return null;
	}

	/*----------Frac Symbol-----------*/
	public static class Frac extends LatexBinary {

		public Frac(Expr arg1, Expr arg2) {
			super(arg1, arg2);
			updateLabel();
		}

		@Override
		public void updateLabel() {
			label = "\\frac" + "{" + arg1 + "}" + "{" + arg2 + "}" ;
			sortKey = label ;
			latexLabel = "\\frac" + "{" + arg1.getLatexLabel() + "}" + "{" + arg2.getLatexLabel() + "}" ;
		}
	}

	public static LatexBinary frac(Expr expr1, Expr expr2) {
		return new Frac(expr1, expr2) ;
	}

	/*----------Underbrace Symbol-----------*/
	public static class Underbrace extends LatexBinary {

		public Underbrace(Expr arg1, Expr arg2) {
			super(arg1, arg2);
			updateLabel();
		}

		@Override
		public void updateLabel() {
			label = "\\underbrace" + "{" + arg1 + "}" + "_" + "{" + arg2 + "}" ;
			sortKey = label ;
			latexLabel = "\\underbrace" + "{" + arg1.getLatexLabel() + "}" + "_" + "{" + arg2.getLatexLabel() + "}" ;
		}
	}

	public static LatexBinary underbrace(Expr expr1, Expr expr2) {
		return new Underbrace(expr1, expr2) ;
	}

	/*----------Overbrace Symbol-----------*/
	public static class Overbrace extends LatexBinary {

		public Overbrace(Expr arg1, Expr arg2) {
			super(arg1, arg2);
			updateLabel();
		}

		@Override
		public void updateLabel() {
			label = "\\overbrace" + "{" + arg1 + "}" + "^" + "{" + arg2 + "}" ;
			sortKey = label ;
			latexLabel = "\\overbrace" + "{" + arg1.getLatexLabel() + "}" + "^" + "{" + arg2.getLatexLabel() + "}" ;
		}
	}

	public static LatexBinary overbrace(Expr expr1, Expr expr2) {
		return new Overbrace(expr1, expr2) ;
	}

	/*----------diff Symbol-----------*/
	public static class Diff extends LatexBinary {

		public Diff(Expr arg1, Expr arg2) {
			super(arg1, arg2);
			updateLabel();
		}

		@Override
		public void updateLabel() {
			if(arg2 instanceof Symbol)
				label = "\\frac" + "{" + "d" + "}" + "{" + "d" + arg2 + "}" + "(" + arg1 + ")" ;
			else
				label = "\\frac" + "{" + "d" + "}" + "{" + "d(" + arg2 + ")}" + "(" + arg1 + ")" ;
			sortKey = label ;
			if(arg2 instanceof Symbol)
				latexLabel = "\\frac" + "{" + "d" + "}" + "{" + "d" + arg2.getLatexLabel() + "}" + "(" + arg1.getLatexLabel() + ")" ;
			else
				latexLabel = "\\frac" + "{" + "d" + "}" + "{" + "d(" + arg2.getLatexLabel() + ")}" + "(" + arg1.getLatexLabel() + ")" ;
		}
	}

	public static LatexBinary diff(Expr expr1, Expr expr2) {
		return new Diff(expr1, expr2) ;
	}

	/*----------partial diff Symbol-----------*/
	public static class PartialDiff extends LatexBinary {

		public PartialDiff(Expr arg1, Expr arg2) {
			super(arg1, arg2);
			updateLabel();
		}

		@Override
		public void updateLabel() {
			if(arg2 instanceof Symbol)
				label = "\\frac" + "{" + "\\partial" + "}" + "{" + "\\partial " + arg2 + "}" + "(" + arg1 + ")" ;
			else
				label = "\\frac" + "{" + "\\partial" + "}" + "{" + "\\partial (" + arg2 + ")}" + "(" + arg1 + ")" ;
			sortKey = label ;
			if(arg2 instanceof Symbol)
				latexLabel = "\\frac" + "{" + "\\partial" + "}" + "{" + "\\partial " + arg2.getLatexLabel() + "}" + "(" + arg1.getLatexLabel() + ")" ;
			else
				latexLabel = "\\frac" + "{" + "\\partial" + "}" + "{" + "\\partial (" + arg2.getLatexLabel() + ")}" + "(" + arg1.getLatexLabel() + ")" ;
		}
	}

	public static LatexBinary partialDiff(Expr expr1, Expr expr2) {
		return new PartialDiff(expr1, expr2) ;
	}





}
