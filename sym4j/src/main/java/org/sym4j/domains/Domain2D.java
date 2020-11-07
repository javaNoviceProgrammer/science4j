package org.sym4j.domains;

import org.sym4j.math.Transformation;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.Symbol;

public class Domain2D extends Domain {

	/**
	 * Construct a 2D domain with a given label(name) and with
	 * default coordinate variables x and y
	 *
	 * @param label
	 */
	public Domain2D(String label) {
		this.label = label;
		this.coordVars = new Expr[]{Symbol.x, Symbol.y};
	}

	/**
	 * Construct a 2D domain with a given label(name) and
	 * a list of coordinate variables
	 * @param label
	 * @param coordVars
	 */
	public Domain2D(String label, Expr ...coordVars) {
		this.label = label;
		this.coordVars = coordVars;
	}

	@Override
	public Domain transform(String label, Transformation trans) {
		return new Domain2D(label, trans.getToVars());
	}

	@Override
	public int getDim() {
		return 2;
	}
}
