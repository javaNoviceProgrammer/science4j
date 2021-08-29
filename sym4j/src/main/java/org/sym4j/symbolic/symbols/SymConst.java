package org.sym4j.symbolic.symbols;

import java.util.Map;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.PUSH;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.TypeInfo;

//import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
//import com.sun.org.apache.bcel.internal.generic.InstructionFactory;
//import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
//import com.sun.org.apache.bcel.internal.generic.InstructionList;
//import com.sun.org.apache.bcel.internal.generic.MethodGen;
//import com.sun.org.apache.bcel.internal.generic.PUSH;

/**
 * An object of SymConst represent a mathematical constant such as PI, E.
 * The constant is displayed as its label but used as a double number
 * in numerical computation.
 *
 */
public class SymConst extends Expr {

	double value;

	public SymConst(String label, double value) {
		this.label = label;
		this.sortKey = label;
		this.value = value;
		updateLabel() ;
	}

	public double getValue() {
		return value;
	}

	@Override
	public Expr diff(Expr expr) {
		return Symbol.C0;
	}

	@Override
	public Expr simplify() {
		return this;
	}

	@Override
	public boolean symEquals(Expr other) {
//		return this.label.equals(other.label);
		return this.label.equals(other.getLabel());
	}

	@Override
	public InstructionHandle bytecodeGen(String clsName, MethodGen mg,
			ConstantPoolGen cp, InstructionFactory factory,
			InstructionList il, Map<String, Integer> argsMap, int argsStartPos,
			Map<Expr, Integer> funcRefsMap) {
		return il.append(new PUSH(cp, value));
	}

	@Override
	public Expr[] args() {
		return new Expr[0];
	}

	@Override
	public TypeInfo getTypeInfo() {
		return TypeInfo.tiDouble;
	}

	@Override
	public void updateLabel() {
		latexLabel = this.label ;
	}
}
