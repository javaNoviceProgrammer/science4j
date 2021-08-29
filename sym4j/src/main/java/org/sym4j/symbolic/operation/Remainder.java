package org.sym4j.symbolic.operation;

//import static com.sun.org.apache.bcel.internal.generic.InstructionConstants.DREM;
//import static com.sun.org.apache.bcel.internal.generic.InstructionConstants.FREM;
//import static com.sun.org.apache.bcel.internal.generic.InstructionConstants.IREM;
//import static com.sun.org.apache.bcel.internal.generic.InstructionConstants.LREM;

import java.util.Map;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionConst;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.arity.BinaryOp;
import org.sym4j.symbolic.utils.BytecodeUtils;
import org.sym4j.symbolic.utils.Utils;

//import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
//import com.sun.org.apache.bcel.internal.generic.InstructionFactory;
//import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
//import com.sun.org.apache.bcel.internal.generic.InstructionList;
//import com.sun.org.apache.bcel.internal.generic.MethodGen;

/**
 * a % b
 *
 */
public class Remainder extends BinaryOp {

	public Remainder(Expr arg1, Expr arg2) {
		super(arg1, arg2);
		updateLabel();
	}

	@Override
	public Expr simplify() {
		return this;
	}

	@Override
	public boolean symEquals(Expr other) {
		//TODO
		return false;
	}

	@Override
	public Expr diff(Expr expr) {
		throw new UnsupportedOperationException();
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
			il.append(InstructionConst.DREM);
		else if(ty == TYPE.INT)
			il.append(InstructionConst.IREM);
		else if(ty == TYPE.LONG)
			il.append(InstructionConst.LREM);
		else if(ty == TYPE.FLOAT)
			il.append(InstructionConst.FREM);
		else
			il.append(InstructionConst.IREM);
		return startPos;
	}

	@Override
	public void updateLabel() {
		this.label = arg1 + "%" + arg2;
		this.sortKey = this.label;
		this.latexLabel = arg1.getLatexLabel() + "\\%" + arg2.getLatexLabel() ;
	}
}
