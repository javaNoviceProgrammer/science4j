package org.sym4j.numeric;

import org.sym4j.bytecode.BytecodeBatchFunc;
import org.sym4j.matrix.ExprMatrix;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.utils.JIT;



public class NumMatrix {
	public BytecodeBatchFunc func;
	int nRow;
	int nCol;
	double[] lastEvalData;

	public NumMatrix() {
	}

	/**
	 * Create an empty matrix
	 * @param m number of rows
	 * @param n number of columns
	 */
	public NumMatrix(int m, int n) {
		this.nRow = m;
		this.nCol = n;
	}

	public NumMatrix(ExprMatrix sm, Expr[] args) {
		this.nRow = sm.rowDim();
		this.nCol = sm.colDim();
		Expr[] exprs = new Expr[nRow*nCol];
		int idx = 0;
		for(int i=0; i<nRow; i++) {
			for(int j=0; j<nCol; j++) {
				exprs[idx++] = sm.get(i, j);
			}
		}
		this.func = JIT.compileBatchFunc(args, exprs);
	}

	public int rowDim() {
		return nRow;
	}

	public int colDim() {
		return nCol;
	}

	/**
	 * Return result: row by row
	 * @param args
	 * @return
	 */
	public void eval(double[] outAry, double ...args) {
		func.apply(outAry, 0, args);
		this.lastEvalData = outAry;
	}

	public double[][] copyData() {
		int m = rowDim();
		int n = colDim();
		double [][] ret = new double[m][n];
		for(int i=0; i<m; i++) {
			System.arraycopy(this.lastEvalData, i*n, ret[i], 0, nCol);
		}
		return ret;
	}
}
