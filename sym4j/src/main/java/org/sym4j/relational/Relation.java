package org.sym4j.relational;

import org.sym4j.symbolic.Expr;

public interface Relation {
	Expr lhs();
	Expr rhs();
}
