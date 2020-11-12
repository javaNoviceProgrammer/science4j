package org.sym4j.latex;

import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.arity.UnaryOp;

public abstract class LatexUnary extends UnaryOp {

	public LatexUnary(Expr arg) {
		super(arg);
		updateLabel() ;
	}

	@Override
	public Expr simplify() {
		return this ;
	}

	@Override
	public Expr diff(Expr x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean symEquals(Expr other) {
		return false;
	}


	/*----------OverArc Symbol-----------*/

	public static class OverArc extends LatexUnary {

		public OverArc(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\overarc" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\overarc" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary overarc(Expr expr) {
		return new OverArc(expr) ;
	}

	/*----------OverRightArrow Symbol-----------*/

	public static class OverRightArrow extends LatexUnary {

		public OverRightArrow(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\overrightarrow" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\overrightarrow" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary overrightarrow(Expr expr) {
		return new OverRightArrow(expr) ;
	}

	/*----------OverLeftArrow Symbol-----------*/

	public static class OverLeftArrow extends LatexUnary {

		public OverLeftArrow(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\overleftarrow" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\overleftarrow" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary overleftarrow(Expr expr) {
		return new OverLeftArrow(expr) ;
	}







}
