package org.sym4j.symbolic.operation;

import java.util.Map;

import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.Pow;
import org.sym4j.symbolic.arity.UnaryOp;
import org.sym4j.symbolic.symbols.SymDouble;
import org.sym4j.symbolic.symbols.SymPrinting;
import org.sym4j.symbolic.symbols.SymReal;
import org.sym4j.symbolic.symbols.Symbol;
import org.sym4j.symbolic.utils.BytecodeUtils;
import org.sym4j.symbolic.utils.Utils;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import com.sun.org.apache.bcel.internal.generic.InstructionFactory;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.MethodGen;

public class Reciprocal extends UnaryOp {

	public Reciprocal(Expr base) {
		super(base);
		updateLabel();
	}

	@Override
	public Expr diff(Expr expr) {
		return new Negate(Pow.simplifiedIns(arg,Expr.valueOf(-2))).multiply(arg.diff(expr));
	}

	@Override
	public Expr simplify() {
		if(this.isSimplified)
			return this;
		if(arg instanceof Pow) {
			Pow p = (Pow)arg.simplify();
			p.isSimplified = true;
			Expr rlt = Pow.simplifiedIns(p.arg1, p.arg2.negate());
			rlt.isSimplified = true;
			return rlt;
		}
		this.isSimplified = true;
		return this;
	}

	public static Expr simplifiedIns(Expr expr) {
		if(expr instanceof SymReal<?>) {
			Number n = (Number)((SymReal<?>)expr).getValue();
			return new SymDouble(1.0/n.doubleValue());
		}
		return new Reciprocal(expr);
	}

	@Override
	public boolean symEquals(Expr other) {
		if(other instanceof Reciprocal) {
			Reciprocal o = (Reciprocal)other;
			return arg.symEquals(o.arg);
		} else if(other instanceof Divide) {
			Divide o = (Divide)other;
			return o.arg1.symEquals(Symbol.C1) && arg.symEquals(o.arg2);
		}
		return false;
	}

	@Override
	public Expr subs(Expr from, Expr to) {
		if(Utils.symCompare(this, from))
			return to;
		if(arg.subs(from,to) == arg)
			return this;
		return Reciprocal.simplifiedIns(arg.subs(from, to));
	}

	@Override
	public InstructionHandle bytecodeGen(String clsName, MethodGen mg,
			ConstantPoolGen cp, InstructionFactory factory,
			InstructionList il, Map<String, Integer> argsMap, int argsStartPos,
			Map<Expr, Integer> funcRefsMap) {
		InstructionHandle startPos = il.append(InstructionConstants.DCONST_1);
		arg.bytecodeGen(clsName, mg, cp, factory, il, argsMap, argsStartPos, funcRefsMap);
		BytecodeUtils.typeCast(il, arg.getType(), TYPE.DOUBLE);
		il.append(InstructionConstants.DDIV);
		return startPos;
	}

	@Override
	public void updateLabel() {
		label =  "1/" +  SymPrinting.addParenthsesIfNeeded(arg, this);
		sortKey = arg.getSortKey();
		latexLabel =  "\\frac{1}{" +  SymPrinting.addParenthsesIfNeededLatex(arg, this) + "}" ;
	}

}
