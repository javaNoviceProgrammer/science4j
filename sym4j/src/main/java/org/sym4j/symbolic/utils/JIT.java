package org.sym4j.symbolic.utils;

import java.util.ArrayList;
import java.util.List;

import org.sym4j.bytecode.BytecodeBatchFunc;
import org.sym4j.bytecode.BytecodeFunc;
import org.sym4j.bytecode.BytecodeVecFunc;
import org.sym4j.bytecode.IR;
import org.sym4j.bytecode.VecFuncs;
import org.sym4j.matrix.ExprMatrix;
import org.sym4j.numeric.NumMatrix;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.Func;
import org.sym4j.symbolic.Symbol;

import com.sun.org.apache.bcel.internal.generic.ClassGen;



public class JIT {

	public static BytecodeFunc compile(Expr[] args, Expr expr) {
		if(expr instanceof Func) {
			Func func = (Func)expr;
			return func.toBytecodeFunc();
		} else {
			Func func = new Func("JITFunc_"+java.util.UUID.randomUUID().toString().replaceAll("-", ""), expr);
			func.args = args;
			return func.toBytecodeFunc(true, false);
		}
	}

	public static BytecodeFunc compile(Expr expr) {
		if(expr instanceof Func) {
			Func func = (Func)expr;
			return func.toBytecodeFunc();
		} else {
			Func func = new Func("JITFunc_"+java.util.UUID.randomUUID().toString().replaceAll("-", ""), expr);
			return func.toBytecodeFunc(false, false);
		}
	}

	public static NumMatrix compile(Expr[] args, ExprMatrix m) {
		return new NumMatrix(m, args);
	}

	public static BytecodeBatchFunc compileBatchFunc(Expr[] args, Expr[] exprs) {
		boolean isWriteFile = true;
		boolean staticMethod = false;
		try {
			int NMaxExpr = 36;
			FuncClassLoader<BytecodeBatchFunc> fcl = new FuncClassLoader<BytecodeBatchFunc>();
			List<Expr> nonZeroList = new ArrayList<Expr>();
			List<Integer> nonZeroIdx = new ArrayList<Integer>();
			for(int i=0; i<exprs.length; i++) {
				if(!Utils.symCompare(Symbol.C0, exprs[i])) {
					nonZeroList.add(exprs[i]);
					nonZeroIdx.add(i);
				}
			}
			if(exprs.length > NMaxExpr) {
				int N = exprs.length;
				List<Expr> batchExprs = new ArrayList<Expr>();
				List<Integer> batchOutPos = new ArrayList<Integer>();
				VecFuncs ret = new VecFuncs();
				for(int i = 0; i<nonZeroList.size(); i++) {
					batchExprs.add(nonZeroList.get(i));
					batchOutPos.add(nonZeroIdx.get(i));
					if(i%NMaxExpr == NMaxExpr-1) {
						String className = "JITVecFunc_XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX__"+i+"___outof___"+exprs.length+"___XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+java.util.UUID.randomUUID().toString().replaceAll("-", "");
						ClassGen genClass = BytecodeUtils.genClassBytecodeBatchFunc(className, batchExprs, batchOutPos, args,
								isWriteFile, staticMethod);
						BytecodeBatchFunc func = fcl.newInstance(genClass);
						ret.addFunc(func, batchOutPos.get(0));
						batchExprs.clear();
						batchOutPos.clear();
					}
				}
				int remain = N%NMaxExpr;
				if(remain > 0) {
					String className = "JITVecFunc_XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX__"+(N-remain)+"___outof___"+exprs.length+"___XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+java.util.UUID.randomUUID().toString().replaceAll("-", "");
					ClassGen genClass = BytecodeUtils.genClassBytecodeBatchFunc(className, batchExprs, batchOutPos, args,
							isWriteFile, staticMethod);
					BytecodeBatchFunc func = fcl.newInstance(genClass);
					ret.addFunc(func, batchOutPos.get(0));
				}
				return ret;
			} else {
				String className = "JITVecFunc_XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX__"+exprs.length+"___XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+java.util.UUID.randomUUID().toString().replaceAll("-", "");
				ClassGen genClass = BytecodeUtils.genClassBytecodeBatchFunc(className, nonZeroList, nonZeroIdx, args,
						isWriteFile, staticMethod);
				return fcl.newInstance(genClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BytecodeVecFunc compileVecFunc(Expr[] args, Expr expr) {
		String className = "JITVecFunc_YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY"+java.util.UUID.randomUUID().toString().replaceAll("-", "");
		ClassGen genClass = BytecodeUtils.genClassBytecodeVecFunc(className,expr, args, true, false);
		FuncClassLoader<BytecodeVecFunc> fcl = new FuncClassLoader<BytecodeVecFunc>();
		return fcl.newInstance(genClass);
	}

//	public static IR getIR(String name, Expr[] args, Expr expr) {
//		String className = name;
//		Func func = new Func(className, expr, args);
//		ClassGen genClass = BytecodeUtils.genClassBytecodeFunc(func, false, false);
//		IR ir =  new IR();
//		ir.type = FUNC_TYPE.SCALAR;
//		ir.name = genClass.getJavaClass().getClassName();
//		ir.bytes = genClass.getJavaClass().getBytes();
//		return ir;
//
//	}

	public static void main(String[] args) {
//		Expr[] exprs = new Expr[3];
//		exprs[0] = Symbol.x;
//		exprs[1] = Symbol.x * Symbol.y;
//		exprs[2] = Symbol.y + 1;
//		BytecodeVecFunc vecFunc = compile(new Expr[]{Symbol.x, Symbol.y}, exprs);
//		double[] outAry = new double[3];
//		vecFunc.apply(outAry, 0, 10.0,20.0);
//		for(double d : outAry)
//			System.out.println(d);

		Expr expr = Symbol.x.add(Symbol.y);
		BytecodeVecFunc vecFunc = JIT.compileVecFunc(new Expr[]{Symbol.x, Symbol.y}, expr);
		double[] outAry = new double[3];
		double[][] params = {
				{1.0,  2.0, 3.0},
				{10.0, 20.0, 30.0}
		};
		vecFunc.apply(outAry, 0, params);
		for(double d : outAry)
			System.out.println(d);

	}
}
