package org.sym4j.symbolic.funcs;

import java.util.Map;

import org.sym4j.math.SymMath;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.arity.UnaryOp;
import org.sym4j.symbolic.utils.Utils;

import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.InstructionFactory;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.MethodGen;
import com.sun.org.apache.bcel.internal.generic.ObjectType;
import com.sun.org.apache.bcel.internal.generic.Type;


public class Coth extends UnaryOp {

	public Coth(Expr arg) {
		super(arg);
		updateLabel();
	}

	@Override
	public Expr diff(Expr expr) {
		return arg.diff(expr) * 2.0/(1.0 - SymMath.cosh(2.0*arg)) ;
	}

	public static Expr simplifiedIns(Expr expr) {
		return new Coth(expr);
	}

	@Override
	public Expr simplify() {
		return this;
	}

	@Override
	public Expr subs(Expr from, Expr to) {
		if(Utils.symCompare(this, from))
			return to;
		Expr sl = arg.subs(from, to);
		if(sl == arg)
			return this;
		return new Coth(sl);
	}

	@Override
	public boolean symEquals(Expr other) {
		if(other instanceof Coth) {
			return Utils.symCompare(this.arg, ((Coth) other).arg);
		}
		return false;
	}

	@Override
	public InstructionHandle bytecodeGen(String clsName, MethodGen mg,
			ConstantPoolGen cp, InstructionFactory factory,
			InstructionList il, Map<String, Integer> argsMap, int argsStartPos,
			Map<Expr, Integer> funcRefsMap) {
		InstructionHandle startPos = arg.bytecodeGen(clsName, mg, cp, factory, il, argsMap, argsStartPos, funcRefsMap);
		if(arg.getType() == TYPE.MATRIX || arg.getType() == TYPE.VECTOR) {
			il.append(factory.createInvoke("org.sym4j.symbolic.utils.BytecodeOpSupport", "tanh",
					new ObjectType("Jama.Matrix"),
					new Type[] { new ObjectType("Jama.Matrix") },
					Constants.INVOKESTATIC));
		} else {
			il.append(factory.createInvoke("org.sym4j.symbolic.utils.BytecodeSupport", "coth",
					Type.DOUBLE,
					new Type[] { Type.DOUBLE },
					Constants.INVOKESTATIC));
		}
		return startPos;
	}

	@Override
	public void updateLabel() {
		label = "coth(" + arg + ")";
		sortKey = label;
		latexLabel = "\\coth(" + arg.getLatexLabel() + ")" ;
	}

}