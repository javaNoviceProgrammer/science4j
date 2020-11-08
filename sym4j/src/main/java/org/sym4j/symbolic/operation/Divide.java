package org.sym4j.symbolic.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.arity.BinaryOp;
import org.sym4j.symbolic.symbols.SymDouble;
import org.sym4j.symbolic.symbols.SymInteger;
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



public class Divide extends BinaryOp {

	public Divide(Expr numerator, Expr denominator) {
		super(numerator, denominator);
		updateLabel();
	}

	public static Expr shallowSimplifiedIns(Expr numerator, Expr denominator) {
		int simOps = numerator.getSimplifyOps() + denominator.getSimplifyOps() + 1;
		if(Symbol.C0.symEquals(numerator))
			return new SymInteger(0).setSimplifyOps(simOps).setAsSimplified();
		else if(numerator instanceof SymReal<?> && denominator instanceof SymReal<?>) {
			Number t1 = (Number)((SymReal<?>)numerator).getValue();
			Number t2 = (Number)((SymReal<?>)denominator).getValue();
			return new SymDouble(t1.doubleValue() / t2.doubleValue()).setSimplifyOps(simOps).setAsSimplified();
		} else if(denominator.symEquals(Symbol.C0))
			throw new IllegalArgumentException("Argument 'divisor' is 0");
		 else if(Symbol.C1.symEquals(numerator))
			return Reciprocal.simplifiedIns(denominator).setSimplifyOps(simOps).setAsSimplified();
		 else if(Symbol.C1.symEquals(denominator))
			return numerator.clone().setSimplifyOps(simOps).setAsSimplified();
		return new Divide(numerator, denominator).setAsSimplified();
	}

	public static Expr simplifiedIns(Expr numerator, Expr denominator) {
		//return shallowSimplifiedIns(numerator, denominator);
		return Utils.flattenSortAndSimplify(shallowSimplifiedIns(numerator, denominator));
	}

	@Override
	public Expr subs(Expr from, Expr to) {
		if(Utils.symCompare(this, from))
			return to;
		return new Divide(arg1.subs(from, to), arg2.subs(from, to));
	}

	@Override
	public Expr diff(Expr expr) {
		//For debug purpose
		if(expr instanceof Symbol) {
			boolean bl = Utils.containSymbol(arg1, (Symbol)expr);
			boolean br = Utils.containSymbol(arg2, (Symbol)expr);
			if(!bl && !br) {
				return Symbol.C0;
			} else if(!bl) {
				return arg1.multiply(Reciprocal.simplifiedIns(arg2).diff(expr));
			} else if(!br) {
				return arg1.diff(expr).multiply(Reciprocal.simplifiedIns(arg2));
			}
		}
		Expr n0 = arg1.diff(expr);
		Expr n1 = n0.multiply(arg2);
		Expr n2 = arg1.multiply(arg2.diff(expr));
		Expr n3 = n1.subtract(n2);
		Expr n4 = arg2.multiply(arg2);
		Expr n5 = n3.divide(n4);
		return n5;

	}

	@Override
	public Expr simplify() {
		if(!this.isSimplified) {
			return simplifiedIns(arg1, arg2);
		}
		return this;
	}

	@Override
	public void flattenAdd(List<Expr> outList) {
		List<Expr> list1 = new ArrayList<Expr>();
		arg1.flattenAdd(list1);
		Expr r = Reciprocal.simplifiedIns(arg2);
		for(Expr e : list1) {
			outList.add( new Multiply(e, r) );
		}
	}

	@Override
	public void flattenMultiply(List<Expr> outList) {
		arg1.flattenMultiply(outList);
		Reciprocal.simplifiedIns(arg2).flattenMultiply(outList);
	}

	public boolean symEquals(Expr other) {
		//return Utils.flattenSortAndCompare(this, other);
		return Utils.flattenSortAndCompare(this.simplify(), other.simplify());
	}

	@Override
	public InstructionHandle bytecodeGen(String clsName, MethodGen mg,
			ConstantPoolGen cp, InstructionFactory factory,
			InstructionList il, Map<String, Integer> argsMap, int argsStartPos,
			Map<Expr, Integer> funcRefsMap) {
		InstructionHandle startPos = arg1.bytecodeGen(clsName, mg, cp, factory, il, argsMap, argsStartPos, funcRefsMap);
		TYPE ty = Utils.getConvertedType(arg1.getType(), arg2.getType());
		BytecodeUtils.typeCast(il, arg1.getType(), ty);
		arg2.bytecodeGen(clsName, mg, cp, factory, il, argsMap, argsStartPos, funcRefsMap);
		BytecodeUtils.typeCast(il, arg2.getType(), ty);
		if(ty == TYPE.DOUBLE)
			il.append(InstructionConstants.DDIV);
		else if(ty == TYPE.INT)
			il.append(InstructionConstants.IDIV);
		else if(ty == TYPE.LONG)
			il.append(InstructionConstants.LDIV);
		else if(ty == TYPE.FLOAT)
			il.append(InstructionConstants.FDIV);
		else
			il.append(InstructionConstants.IDIV);
		return startPos;
	}

	@Override
	public void updateLabel() {
		label =  SymPrinting.addParenthsesIfNeeded(arg1, this)
				+ "/" +
				SymPrinting.addParenthsesIfNeeded2(arg2, this);
		sortKey = arg1.getSortKey()+arg2.getSortKey();
		latexLabel = "\\frac{" + SymPrinting.addParenthsesIfNeededLatex(arg1, this) + "}" +
					 "{" + SymPrinting.addParenthsesIfNeededLatex2(arg2, this) + "}" ;
	}
}
