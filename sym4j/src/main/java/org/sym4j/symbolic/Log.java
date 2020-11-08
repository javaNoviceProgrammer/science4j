package org.sym4j.symbolic;

import java.util.Map;

import org.sym4j.math.SymMath;
import org.sym4j.symbolic.arity.BinaryOp;
import org.sym4j.symbolic.symbols.SymDouble;
import org.sym4j.symbolic.symbols.SymReal;
import org.sym4j.symbolic.symbols.Symbol;
import org.sym4j.symbolic.utils.Utils;

import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.InstructionFactory;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.MethodGen;
import com.sun.org.apache.bcel.internal.generic.ObjectType;
import com.sun.org.apache.bcel.internal.generic.Type;


/**
 * logarithm
 *
 * log(A): if A is a matrix do element wise log
 *
 */
public class Log extends BinaryOp {

	/**
	 * Natural logarithm (base e) of expr
	 * @param expr
	 */
	public Log(Expr expr) {
		super(SymMath.E, expr);
		updateLabel();

	}

	/**
	 * Log_{base}(expr)
	 * @param base
	 * @param expr
	 */
	public Log(Expr base, Expr expr) {
		super(base, expr);
		label = "log_{" + base + "}(" + expr + ")";
		sortKey = label;
	}

	public String toString() {
		if(!arg1.symEquals(SymMath.E))
			return "log(" + arg1 + "," + arg2 + ")";
		else
			return label;
	}

	public static Expr simplifiedIns(Expr base, Expr expr) {
		if(base instanceof SymReal<?> && expr instanceof SymReal<?>) {
			return new SymDouble(
					Math.log(((SymReal<?>)base).getDoubleValue()) / Math.log(((SymReal<?>)expr).getDoubleValue())
					);
		} else if(expr instanceof SymReal<?>) {
			SymReal<?> realExp = (SymReal<?>)base;
			if(realExp.isOne())
				return Symbol.C0;
		} else if(base instanceof SymReal<?>) {
			SymReal<?> realBase = (SymReal<?>)base;
			if(realBase.isNonPositive())
				throw new RuntimeException("The base of a log cannot be <= 0");
		}
		return new Log(base, expr);
	}

	public static Expr simplifiedIns(Expr expr) {
		return new Log(expr);
	}

	@Override
	public Expr diff(Expr expr) {
		if(Utils.symCompare(SymMath.E, arg1)) {
			return this.multiply(arg2.diff(expr));
		}
		return null;
	}

	@Override
	public Expr simplify() {
		return this;
	}

	@Override
	public Expr subs(Expr from, Expr to) {
		if(Utils.symCompare(this, from))
			return to;
		Expr sl = arg1.subs(from, to);
		Expr sr = arg2.subs(from, to);
		if(sl == arg1 && sr == arg2)
			return this;
		return new Log(sl, sr);
	}

	@Override
	public boolean symEquals(Expr other) {
		return false;
	}

	public InstructionHandle bytecodeGen(String clsName, MethodGen mg,
			ConstantPoolGen cp, InstructionFactory factory,
			InstructionList il, Map<String, Integer> argsMap, int argsStartPos,
			Map<Expr, Integer> funcRefsMap) {
		InstructionHandle startPos = arg1.bytecodeGen(clsName, mg, cp, factory, il, argsMap, argsStartPos, funcRefsMap);
		arg2.bytecodeGen(clsName, mg, cp, factory, il, argsMap, argsStartPos, funcRefsMap);
		if(arg2.getType() == TYPE.MATRIX || arg2.getType() == TYPE.VECTOR) {
			il.append(factory.createInvoke("org.sym4j.symbolic.utils.BytecodeOpSupport", "log",
					new ObjectType("Jama.Matrix"),
					new Type[] { Type.DOUBLE, new ObjectType("Jama.Matrix") },
					Constants.INVOKESTATIC));
		} else {
			il.append(factory.createInvoke("org.sym4j.symbolic.utils.BytecodeSupport", "log",
					Type.DOUBLE, new Type[] { Type.DOUBLE,  Type.DOUBLE }, Constants.INVOKESTATIC));
		}

		return startPos;
	}

	@Override
	public void updateLabel() {
		if(arg1.symEquals(SymMath.E))
			label = "log(" + arg2 + ")";
		else
			label = "log_{"+arg1+"}(" + arg2 + ")";
		sortKey = label;
	}
}
