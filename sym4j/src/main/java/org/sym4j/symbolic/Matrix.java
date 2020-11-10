package org.sym4j.symbolic;

import java.util.Map;

import org.sym4j.matrix.ExprMatrix;

import com.sun.org.apache.bcel.internal.Constants;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ArrayType;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import com.sun.org.apache.bcel.internal.generic.InstructionFactory;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.MethodGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.ObjectType;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.Type;



public class Matrix extends Tensor {

	public int nRowStart;
	public int nColStart;
	public int nRow;
	public int nCol;
	public Matrix parent;

	protected int indexLVT = -1;
	public Matrix(String name, int nRow, int nCol) {
		super(name);
		nRowStart = 0;
		nColStart = 0;
		this.nRow = nRow;
		this.nCol = nCol;
	}

	public Matrix(Matrix parent, int nRowStart, int nColStart, int nRow, int nCol) {
		super(parent.label+"_"+nRowStart+"_"+nColStart+"_"+nRow+"_"+nCol);
		this.nRowStart = nRowStart;
		this.nColStart = nColStart;
		this.nRow = nRow;
		this.nCol = nCol;
		this.parent = parent;
	}

	public Matrix(Matrix parent, String name, int nRowStart, int nColStart, int nRow, int nCol) {
		super(name);
		this.nRowStart = nRowStart;
		this.nColStart = nColStart;
		this.nRow = nRow;
		this.nCol = nCol;
		this.parent = parent;
	}


	public ExprMatrix split(int nRowBlock, int nColBlock) {
		int m = nRow/nRowBlock;
		if(nRow%nRowBlock > 0)
			m = (nRow+(nRowBlock-nRow%nRowBlock))/nRowBlock;
		int n = nCol/nColBlock;
		if(nCol%nColBlock > 0)
			n = (nCol+(nColBlock-nCol%nColBlock))/nColBlock;
		int last_m = nRow%m==0?m:nRow%m;
		int last_n = nCol%n==0?n:nCol%n;
/*		System.out.println(m);
		System.out.println(n);
		System.out.println(last_m);
		System.out.println(last_n);
*/		Expr[][] items = new Expr[nRowBlock][nColBlock];
		for(int i=0; i<nRowBlock; i++) {
			int nr = m;
			if(i == nRowBlock-1) nr = last_m;

			for(int j=0; j<nColBlock; j++) {
				int nc = n;
				if(j == nColBlock-1) nc = last_n;

				items[i][j] = new Matrix(this, this.label+"_"+i+"_"+j, i*m, j*n, nr, nc);
			}
		}
		return new ExprMatrix(items);
	}

	@Override
	public InstructionHandle bytecodeGen(String clsName, MethodGen mg,
			ConstantPoolGen cp, InstructionFactory factory,
			InstructionList il, Map<String, Integer> argsMap, int argsStartPos,
			Map<Expr, Integer> funcRefsMap) {
		// Create an instance of Jama.Matrix and store it to local variable index with this.indexLVT
		if(indexLVT == -1) {
			//jama.Matrix l_m = null;
			LocalVariableGen lg = mg.addLocalVariable("l_"+getLabel(),
					new ObjectType("Jama.Matrix"), null, null);
			indexLVT = lg.getIndex();
//			il.append(InstructionConstants.ACONST_NULL);
//			lg.setStart(il.append(new DSTORE(idx)));

			// First time touch the matrix, declare a local reference of Java.Matrix
			il.append(new NEW(cp.addClass("Jama.Matrix")));
		    il.append(InstructionConstants.DUP);

		    //prepare argument: double[] vals
			//il.append(new ALOAD(argsStartPos));
			//il.append(new PUSH(cp, argsMap.get(this.label)));
			//il.append(InstructionConstants.DALOAD); //Load double from array
			//////////////il.append(new ALOAD(argsMap.get(this.label))); //Load reference from local variable (from function arguments)
			//il.append(new ALOAD(1)); //Load reference from local variable (from function arguments)

			il.append(new ALOAD(argsStartPos));
			il.append(new PUSH(cp, argsMap.get(this.label)));
			il.append(InstructionConstants.AALOAD); //Load double from array

			//prepare argument: double m - number of rows
    		il.append(new PUSH(cp, nRow));
			il.append(factory.createInvoke("Jama.Matrix", "<init>",
				Type.VOID, new Type[] { new ArrayType(Type.DOUBLE, 1), Type.INT },
				Constants.INVOKESPECIAL));

			//jama.Matrix l_m = new jama.Matrix(args[], nRow);
			lg.setStart(il.append(new ASTORE(indexLVT)));
		}
		// Retrun the local reference of the matrix
		return il.append(new ALOAD(indexLVT));

		//For test purpose
		//il.append(new ALOAD(indexLVT));
		//il.append(new PUSH(cp, 1.0));
		//return il.append(InstructionConstants.DRETURN);
	}

	@Override
	public TypeInfo getTypeInfo() {
		TypeInfo ti = new TypeInfo(TYPE.MATRIX, new int[2]);
		ti.dim[0] = this.nRow;
		ti.dim[1] = this.nCol;
		return ti;
	}

	public void bytecodeGenReset() {
		this.indexLVT = -1;
	}

	@Override
	public Expr getParent() {
		return this.parent;
	}

	public static void main(String[] args) {
		Matrix m = new Matrix("A",6,8);
		ExprMatrix sm = m.split(3, 2);
		System.out.println(sm);

	}
}
