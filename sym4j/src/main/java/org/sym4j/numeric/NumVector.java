package org.sym4j.numeric;

import org.sym4j.bytecode.BytecodeBatchFunc;
import org.sym4j.matrix.ExprVector;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.utils.JIT;

public class NumVector {
	BytecodeBatchFunc func;
	int size;
	double[] lastEvalData;

	public NumVector() {

	}

	public NumVector(int size) {
		this.size = size;
	}

	public NumVector(ExprVector sv, Expr[] args) {
		this.size = sv.dim();
		this.func = JIT.compileBatchFunc(args, sv.getData());
	}

	public int dim() {
		return this.size;
	}

	public double[] eval(double[] outAry, double ...args) {
		func.apply(outAry, 0, args);
		this.lastEvalData = outAry;
		return lastEvalData;
	}

	public double[] getData() {
		return lastEvalData;
	}
}

