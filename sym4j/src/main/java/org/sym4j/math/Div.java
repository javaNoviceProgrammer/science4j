package org.sym4j.math;

import java.util.ArrayList;
import java.util.List;

import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.arity.NaryOp;
import org.sym4j.symbolic.utils.Utils;



/**
 * Divergence Operator
 *
 */
public class Div extends NaryOp {
	protected Expr expr;

	public Div(ExprVector vec) {
		super(null);
		if(vec instanceof Grad) {
			Grad g = (Grad)vec;
			if(g.isAbstract()) {
				this.args = g.getData();
				this.label = "div(" +g+ ")";
				this.sortKey = this.label;
				return;
			}
		}
		List<Expr> freeVars = Utils.extractSymbols(vec.getData());
		create(vec, freeVars.toArray(new Expr[0]));
	}

	public Div(ExprVector vec, Expr... freeVars) {
		super(null);
		create(vec, freeVars);
	}

	private Div create(ExprVector vec, Expr... freeVars) {
		if(vec.dim() != freeVars.length) {
			throw new RuntimeException("vec.dim() != freeVars.length");
		}
		this.args = vec.getData();

		List<Expr> list = new ArrayList<Expr>();
		for(int i=0; i<this.args.length; i++) {
			list.add(vec.get(i).diff(freeVars[i]));
		}
		this.expr = Utils.addListToExpr(list).simplify();
		this.label = expr.getLabel();
		this.sortKey = expr.getSortKey();
		return this;
	}

	public static Div apply(ExprVector vec) {
		return new Div(vec);
	}

	public static Div apply(ExprVector vec, Expr ...expr) {
		return new Div(vec, expr);
	}

	@Override
	public boolean isAbstract() {
		return expr == null;
	}

	public Expr getExpr() {
		return this.expr;
	}

	@Override
	public Expr simplify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean symEquals(Expr other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Expr diff(Expr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeInfo getTypeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateLabel() {
		// TODO Auto-generated method stub

	}

}
