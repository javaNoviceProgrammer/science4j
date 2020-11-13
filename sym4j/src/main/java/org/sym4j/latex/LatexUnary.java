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

	/*----------OverLeftRightArrow Symbol-----------*/

	public static class OverLeftRightArrow extends LatexUnary {

		public OverLeftRightArrow(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\overleftrightarrow" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\overleftrightarrow" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary overleftrightarrow(Expr expr) {
		return new OverLeftRightArrow(expr) ;
	}

	/*----------Hat Symbol-----------*/
	public static class Hat extends LatexUnary {

		public Hat(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\hat" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\hat" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary hat(Expr expr) {
		return new Hat(expr) ;
	}

	/*----------Breve Symbol-----------*/
	public static class Breve extends LatexUnary {

		public Breve(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\breve" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\breve" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary breve(Expr expr) {
		return new Breve(expr) ;
	}

	/*----------Grave Symbol-----------*/
	public static class Grave extends LatexUnary {

		public Grave(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\grave" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\grave" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary grave(Expr expr) {
		return new Grave(expr) ;
	}

	/*----------Bar Symbol-----------*/
	public static class Bar extends LatexUnary {

		public Bar(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\bar" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\bar" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary bar(Expr expr) {
		return new Bar(expr) ;
	}

	/*----------Check Symbol-----------*/
	public static class Check extends LatexUnary {

		public Check(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\check" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\check" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary check(Expr expr) {
		return new Check(expr) ;
	}

	/*----------Acute Symbol-----------*/
	public static class Acute extends LatexUnary {

		public Acute(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\acute" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\acute" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary acute(Expr expr) {
		return new Acute(expr) ;
	}

	/*----------Tilde Symbol-----------*/
	public static class Tilde extends LatexUnary {

		public Tilde(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\tilde" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\tilde" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary tilde(Expr expr) {
		return new Tilde(expr) ;
	}

	/*----------Vec Symbol-----------*/
	public static class Vec extends LatexUnary {

		public Vec(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\vec" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\vec" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary vec(Expr expr) {
		return new Vec(expr) ;
	}

	/*----------Dot Symbol-----------*/
	public static class Dot extends LatexUnary {

		public Dot(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\dot" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\dot" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary dot(Expr expr) {
		return new Dot(expr) ;
	}

	/*----------DDot Symbol-----------*/
	public static class DDot extends LatexUnary {

		public DDot(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\ddot" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\ddot" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary ddot(Expr expr) {
		return new DDot(expr) ;
	}

	/*----------Mathring Symbol-----------*/
	public static class Mathring extends LatexUnary {

		public Mathring(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\mathring" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\mathring" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary mathring(Expr expr) {
		return new Mathring(expr) ;
	}

	/*----------Widehat Symbol-----------*/
	public static class Widehat extends LatexUnary {

		public Widehat(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\widehat" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\widehat" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary widehat(Expr expr) {
		return new Widehat(expr) ;
	}

	/*----------Widetilde Symbol-----------*/
	public static class Widetilde extends LatexUnary {

		public Widetilde(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\widetilde" + "{" + arg + "}" ;
			sortKey = label ;
			latexLabel = "\\widetilde" + "{" + arg.getLatexLabel() + "}" ;
		}
	}

	public static LatexUnary widetilde(Expr expr) {
		return new Widetilde(expr) ;
	}

	/*----------LeftRightRoundBrackets brackets-----------*/
	public static class LeftRightRoundBrackets extends LatexUnary {

		public LeftRightRoundBrackets(Expr arg) {
			super(arg) ;
		}

		@Override
		public void updateLabel() {
			label = "\\left(" + "{" + arg + "}" + "\\right)" ;
			sortKey = label ;
			latexLabel = "\\left(" + "{" + arg.getLatexLabel() + "}" + "\\right)" ;
		}
	}

	public static LatexUnary leftRightRoundBrackets(Expr expr) {
		return new LeftRightRoundBrackets(expr) ;
	}



}
