package org.sym4j.domains;

import org.sym4j.math.Transformation;
import org.sym4j.symbolic.Expr;


public class DomainND extends Domain {
	/**
	 * Construct a N Dimensional domain with a given label(name) and
	 * a list of coordinate variables
	 * @param label
	 * @param coordVars
	 */
	public DomainND(String label, Expr ...coordVars) {
		this.label = label;
		this.coordVars = coordVars;
	}

	@Override
	public Domain transform(String label, Transformation trans) {
		return new DomainND(label, trans.getToVars());
	}

	@Override
	public int getDim() {
		return coordVars.length;
	}
}
