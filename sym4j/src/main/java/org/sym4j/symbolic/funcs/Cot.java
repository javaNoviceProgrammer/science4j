package org.sym4j.symbolic.funcs;

import java.util.Map;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.Type;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.arity.UnaryOp;
import org.sym4j.symbolic.utils.Utils;

//import com.sun.org.apache.bcel.internal.Constants;
//import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
//import com.sun.org.apache.bcel.internal.generic.InstructionFactory;
//import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
//import com.sun.org.apache.bcel.internal.generic.InstructionList;
//import com.sun.org.apache.bcel.internal.generic.MethodGen;
//import com.sun.org.apache.bcel.internal.generic.Type;



public class Cot extends UnaryOp {

	public Cot(Expr arg) {
		super(arg);
		updateLabel();
	}

	@Override
	public Expr diff(Expr expr) {
		// -(1+cot^2(x)) = -csc^2(x)
		return arg.diff(expr).multiply(new Pow(this, Expr.valueOf(2)).add(1))
				.multiply(-1) ;
	}

	public static Expr simplifiedIns(Expr expr) {
		return new Cot(expr);
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
		return new Cot(sl);
	}

	@Override
	public boolean symEquals(Expr other) {
		if(other instanceof Cot) {
			return Utils.symCompare(this.arg, ((Cot) other).arg);
		}
		return false;
	}

	@Override
	public InstructionHandle bytecodeGen(String clsName, MethodGen mg,
			ConstantPoolGen cp, InstructionFactory factory,
			InstructionList il, Map<String, Integer> argsMap, int argsStartPos,
			Map<Expr, Integer> funcRefsMap) {
		InstructionHandle startPos = arg.bytecodeGen(clsName, mg, cp, factory, il, argsMap, argsStartPos, funcRefsMap);
		il.append(factory.createInvoke("org.sym4j.symbolic.utils.BytecodeSupport", "cot",
				Type.DOUBLE,
				new Type[] { Type.DOUBLE },
		Constants.INVOKESTATIC));
		return startPos;
	}

	@Override
	public void updateLabel() {
		label = "cot(" + arg + ")" ;
		sortKey = label ;
		latexLabel = "\\cot(" + arg.getLatexLabel() + ")" ;
	}
}
