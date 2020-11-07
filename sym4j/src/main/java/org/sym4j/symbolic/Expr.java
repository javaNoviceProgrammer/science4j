package org.sym4j.symbolic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.sym4j.logic.And;
import org.sym4j.logic.Not;
import org.sym4j.logic.Or;
import org.sym4j.logic.Xor;
import org.sym4j.symbolic.utils.Utils;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.InstructionFactory;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.MethodGen;



abstract public class Expr implements Cloneable {
	/**
	 * Label(or name) of an expression
	 */
	protected String label = null;

	/**
	 * Latex representation
	 */
	protected String latexLabel = null;

	/**
	 * A string used to sort the terms in an expression
	 */
	protected String sortKey = null;

	/**
	 * Number of operations for simplifying an expression
	 */
	public int simplifyOpNum = 0;

	/**
	 * Return true if simplify() is called
	 */
	public boolean isSimplified = false;

	/**
	 * Simplify the expression
	 * @return
	 */
	public abstract Expr simplify();

	/**
	 * Return true if two expressions are equal in the sense of mathematics
	 * @param other
	 * @return
	 */
	public abstract boolean symEquals(Expr other);

	/**
	 * Return the arguments of the expression
	 * @return
	 */
	public abstract Expr[] args();// { return new Expr[0]; }

	/**
	 * Derivative of the expression with respect to x
	 * @param x
	 * @return
	 */
	public abstract Expr diff(Expr x);

	/**
	 * Functional derivative of f with respect to df
	 * @param f
	 * @param df
	 * @return
	 */
	public Expr fdiff(Expr f, Expr df) {
		if(!(f instanceof Func) || !(df instanceof Func))
			throw new IllegalArgumentException();
		Func F = (Func)f;
		Symbol alpha = new Symbol("_alpha_");
		Expr ff = this.subs(f, f.add(alpha.multiply(df)));
		Expr dff = ff.diff(alpha);
		if(Symbol.C0.symEquals(dff)) {
			Func ret = new Func("0", F.args);
			ret.expr = Symbol.C0;
			return ret;
		}
		return dff.subs(alpha, 0).simplify();
	}

	/**
	 * Split the terms into a list for Add and Subtract
	 * @param outList
	 */
	public void flattenAdd(List<Expr> outList) {
		outList.add(this);
	};

	/**
	 * Split the terms into a list for Multiply and Divide
	 * @param outList
	 */
	public void flattenMultiply(List<Expr> outList) {
		outList.add(this);
	}

	/**
	 * Return true if the expression is an abstract thing.
	 * for example, a abstract function
	 * @return
	 */
	public boolean isAbstract() {
		return false;
	}

	/**
	 * Return the string representation of the expression
	 */
	public String toString() {
		return label;
	}

	/**
	 * Return the LaTex representation of the expression
	 * @return
	 */
	// TODO : fix the latexLabel representation
	public String toLaTex() {
//		if (latexLabel == null) {
//			latexLabel = label.replaceAll("[*]", "") ;
//			latexLabel = label.replaceAll("[*]", "\\\\times") ;
//			latexLabel = label.replaceAll("[*]", "\\\\;") ;
//		}
//		return latexLabel ;
//		return label ;
		latexLabel = label.replaceAll("[*]", "")
							.replaceAll("sin", "\\\\sin")
							.replaceAll("cos", "\\\\cos")
							.replaceAll("tan", "\\\\tan") ;

		return latexLabel ;
	}

	public void latexRender(String filePath, String fileName) {
		// create and save latex file
		StringBuilder sb = new StringBuilder(500) ;
		String sep = System.lineSeparator() ;
//		sb.append("\\documentclass[20pt]{extreport}").append(sep) ;
		sb.append("\\documentclass[preview]{standalone}").append(sep) ;
		sb.append("\\usepackage[usenames]{color} %used for font color").append(sep) ;
		sb.append("\\usepackage{amssymb} %maths").append(sep) ;
		sb.append("\\usepackage{amsmath} %maths").append(sep) ;
		sb.append("\\usepackage[utf8]{inputenc} %useful to type directly diacritic characters").append(sep) ;
		sb.append("\\pagestyle{empty}").append(sep) ;
		sb.append("\\begin{document}").append(sep) ;
//		sb.append("\\begin{align*}").append(sep) ;
//		sb.append(this.toLaTex()).append(sep) ;
//		sb.append("\\end{align*}").append(sep) ;
		sb.append("$").append(this.toLaTex()).append("$").append(sep) ;
		sb.append("\\end{document}") ;
		// run pdflatex
		String latexFile = filePath + File.separator + fileName + ".tex" ;
		System.out.println("File : " + latexFile);
		File texFile = new File(latexFile) ;
		try {
			FileWriter fwriter = new FileWriter(texFile) ;
			fwriter.write(sb.toString());
			fwriter.close() ;
			Runtime.getRuntime().exec(new String[] {"pdflatex", "-output-directory", filePath, latexFile}) ;
//			Process proc = Runtime.getRuntime().exec(new String[] {"pdflatex", "-output-directory", filePath, latexFile}) ;
//			proc.waitFor() ;
//			Scanner scanner = new Scanner(proc.getInputStream()) ;
//			while(scanner.hasNextLine()) {
//				System.out.println(scanner.nextLine());
//			}
//			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}

	}

	/**
	 * Set the label(or name) of the expression
	 * @param label
	 * @return
	 */
	public Expr setLabel(String label) {
		this.label = label;
		return this;
	}

	/**
	 * Return the label(or name) of the expression
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set a string key for sorting the terms in the expression
	 * @param sortKey
	 * @return
	 */
	public Expr setSortKey(String sortKey) {
		this.sortKey = sortKey;
		return this;
	}

	/**
	 * Get the string key used to sort the terms in the expression
	 * @param sortKey
	 * @return
	 */
	public String getSortKey() {
		return sortKey;
	}

	/**
	 * Count number of operations for simplification
	 * @return
	 */
	public int getSimplifyOps() {
		return simplifyOpNum;
	}
	public Expr setSimplifyOps(int n) {
		//Make no sense, call setAsSimplified() explicitly
		//if(n > simplifyOpNum)
		//	isSimplified = true;
		simplifyOpNum = n;
		return this;
	}
	public Expr incSimplifyOps(int n) {
		simplifyOpNum += n;
		isSimplified = true;
		return this;
	}
	public Expr setAsSimplified() {
		isSimplified = true;
		return this;
	}

	/**
	 * Operator overloading support:
	 * Expr a = 5;
	 *
	 * @param v
	 * @return
	 */
	public static Expr valueOf(int v) {
		return new SymInteger(v);
	}
	public static Expr valueOf(long v) {
		return new SymLong(v);
	}
	public static Expr valueOf(float v) {
		return new SymFloat(v);
	}
	public static Expr valueOf(double v) {
		return new SymDouble(v);
	}
	/**
	 * Operator overloading support:
	 * a+b
	 * @param other
	 * @return
	 */
	public Expr add(Expr other) {
		return Add.simplifiedIns(this, other);
	}
	public Expr add(int other) {
		return Add.simplifiedIns(this, new SymInteger(other));
	}
	public Expr addRev(int other) {
		return Add.simplifiedIns(new SymInteger(other), this);
	}
	public Expr add(long other) {
		return Add.simplifiedIns(this, new SymLong(other));
	}
	public Expr addRev(long other) {
		return Add.simplifiedIns(new SymLong(other), this);
	}
	public Expr add(float other) {
		return Add.simplifiedIns(this, new SymFloat(other));
	}
	public Expr addRev(float other) {
		return Add.simplifiedIns(new SymFloat(other), this);
	}
	public Expr add(double other) {
		return Add.simplifiedIns(this, new SymDouble(other));
	}
	public Expr addRev(double other) {
		return Add.simplifiedIns(new SymDouble(other), this);
	}
	/**
	 * Operator overload support for Groovy:
	 * a+b
	 * @param other
	 * @return
	 */
	public Expr plus(Expr other) {
		return Add.simplifiedIns(this, other);
	}
	public Expr plus(int other) {
		return Add.simplifiedIns(this, new SymInteger(other));
	}
	public Expr plus(long other) {
		return Add.simplifiedIns(this, new SymLong(other));
	}
	public Expr plus(float other) {
		return Add.simplifiedIns(this, new SymFloat(other));
	}
	public Expr plus(double other) {
		return Add.simplifiedIns(this, new SymDouble(other));
	}
	/**
	 * Operator overloading support:
	 * a-b
	 * @param other
	 * @return
	 */
	public Expr subtract(Expr other) {
		return Subtract.simplifiedIns(this, other);
	}
	public Expr subtract(int other) {
		return Subtract.simplifiedIns(this, new SymInteger(other));
	}
	public Expr subtractRev(int other) {
		return Subtract.simplifiedIns(new SymInteger(other), this);
	}
	public Expr subtract(long other) {
		return Subtract.simplifiedIns(this, new SymLong(other));
	}
	public Expr subtractRev(long other) {
		return Subtract.simplifiedIns(new SymLong(other), this);
	}
	public Expr subtract(float other) {
		return Subtract.simplifiedIns(this, new SymFloat(other));
	}
	public Expr subtractRev(float other) {
		return Subtract.simplifiedIns(new SymFloat(other), this);
	}
	public Expr subtract(double other) {
		return Subtract.simplifiedIns(this, new SymDouble(other));
	}
	public Expr subtractRev(double other) {
		return Subtract.simplifiedIns(new SymDouble(other), this);
	}
	/**
	 * Operator overload support for Groovy:
	 * a-b
	 * @param other
	 * @return
	 */
	public Expr minus(Expr other) {
		return Subtract.simplifiedIns(this, other);
	}
	public Expr minus(int other) {
		return Subtract.simplifiedIns(this, new SymInteger(other));
	}
	public Expr minus(long other) {
		return Subtract.simplifiedIns(this, new SymLong(other));
	}
	public Expr minus(float other) {
		return Subtract.simplifiedIns(this, new SymFloat(other));
	}
	public Expr minus(double other) {
		return Subtract.simplifiedIns(this, new SymDouble(other));
	}
	/**
	 * Operator overloading support:
	 * a*b
	 * @param other
	 * @return
	 */
	public Expr multiply(Expr other) {
		return Multiply.simplifiedIns(this, other);
	}
	public Expr multiply(int other) {
		return Multiply.simplifiedIns(this, new SymInteger(other));
	}
	public Expr multiplyRev(int other) {
		return Multiply.simplifiedIns(new SymInteger(other), this);
	}
	public Expr multiply(long other) {
		return Multiply.simplifiedIns(this, new SymLong(other));
	}
	public Expr multiplyRev(long other) {
		return Multiply.simplifiedIns(new SymLong(other), this);
	}
	public Expr multiply(float other) {
		return Multiply.simplifiedIns(this, new SymFloat(other));
	}
	public Expr multiplyRev(float other) {
		return Multiply.simplifiedIns(new SymFloat(other), this);
	}
	public Expr multiply(double other) {
		return Multiply.simplifiedIns(this, new SymDouble(other));
	}
	public Expr multiplyRev(double other) {
		return Multiply.simplifiedIns(new SymDouble(other), this);
	}

	/**
	 * Operator overloading support:
	 * a/b
	 * @param other
	 * @return
	 */
	public Expr divide(Expr other) {
		return Divide.simplifiedIns(this, other);
	}
	public Expr divide(int other) {
		return Divide.simplifiedIns(this, new SymInteger(other));
	}
	public Expr divideRev(int other) {
		return Divide.simplifiedIns(new SymInteger(other), this);
	}
	public Expr divide(long other) {
		return Divide.simplifiedIns(this, new SymLong(other));
	}
	public Expr divideRev(long other) {
		return Divide.simplifiedIns(new SymLong(other), this);
	}
	public Expr divide(float other) {
		return Divide.simplifiedIns(this, new SymFloat(other));
	}
	public Expr divideRev(float other) {
		return Divide.simplifiedIns(new SymFloat(other), this);
	}
	public Expr divide(double other) {
		return Divide.simplifiedIns(this, new SymDouble(other));
	}
	public Expr divideRev(double other) {
		return Divide.simplifiedIns(new SymDouble(other), this);
	}
	/**
	 * Operator overload support for Groovy:
	 * a/b
	 * @param other
	 * @return
	 */
	public Expr div(Expr other) {
		return Divide.simplifiedIns(this, other);
	}
	public Expr divi(int other) {
		return Divide.simplifiedIns(this, new SymInteger(other));
	}
	public Expr div(long other) {
		return Divide.simplifiedIns(this, new SymLong(other));
	}
	public Expr div(float other) {
		return Divide.simplifiedIns(this, new SymFloat(other));
	}
	public Expr div(double other) {
		return Divide.simplifiedIns(this, new SymDouble(other));
	}
	/**
	 * Operator overloading support:
	 * -a
	 *
	 */
	public Expr negate() {
		if(this instanceof SymReal<?>) {
			SymReal<?> dd = (SymReal<?>)this;
			double dv = dd.getValue().doubleValue();
			if(dv == 0)
				return Symbol.C0;
			return new SymDouble(-dv);
		}
		return Negate.simplifiedIns(this);
	};

	/**
	 * x%y
	 * @return
	 */
	public Expr remainder(Expr other) {
		return new Remainder(this, other);
	}

	/*
	 * !x
	 */
	public Expr not() {
		return Not.simplifiedIns(this);
	}

	/**
	 * x&y
	 * @param other
	 * @return
	 */
	public Expr and(Expr other) {
		return And.simplifiedIns(this, other);
	}

	/**
	 * x|y
	 * @param other
	 * @return
	 */
	public Expr or(Expr other) {
		return Or.simplifiedIns(this, other);
	}

	/**
	 * x^y
	 * @param other
	 * @return
	 */
	public Expr xor(Expr other) {
		return Xor.simplifiedIns(this, other);
	}

//	/**
//	 * TODO We cannot use the comparison Operator overloading in java-oo for our use case
//	 * @param other
//	 * @return
//	 */
//	public int compareTo(Expr other) {
//		if(Ge.stackTop == null)
//			Ge.stackTop = Ge.apply(this, other); //fix: use push()
//		else
//			Ge.stackTop = Ge.apply(Ge.stackTop, other);
//		return -1;
//	}


	/**
	 * Substitution
	 *
	 * @param from
	 * @param to
	 * @return
	 */
	public Expr subs(Expr from, Expr to) {
		if(Utils.symCompare(this, from)) {
			return to;
		}
		return this;
	}

	public Expr subs(Expr from, int to) {
		return subs(from, new SymInteger(to));
	}
	public Expr subs(Expr from, long to) {
		return subs(from, new SymLong(to));
	}
	public Expr subs(Expr from, float to) {
		return subs(from, new SymFloat(to));
	}
	public Expr subs(Expr from, double to) {
		return subs(from, new SymDouble(to));
	}

	public Expr clone() {
		try {
			return (Expr) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * If the labels of two expressions are the same they are considered as
	 * the same symbolic expressions
	 */
    @Override
    public int hashCode() {
        return this.label.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
       return this.label.equals(((Expr)obj).label);
    }

////////////////////////////////////////////////////////////////////////////
//	/**
//	 * Assign the result of evaluating an expression to a local variable
//	 * when compiling. The local variable must be a symbol which is declared
//	 * as a local variable.
//	 * <p>
//	 * The call of this function can be understand as
//	 * Symbol symLocal; //Declared somewhere
//	 * symLocal = this;
//	 *
//	 * <p>
//	 * Note: The name of a symbol is a global name. Make sure you don't have
//	 * two symbols with the same name, otherwise they are treated as the same
//	 * symbol or local variable in compiled code.
//	 *
//	 * @param symLocal A symbol declared as a local variable
//	 * @return An instance of operator OPAsign
//	 */
//	public Expr assignTo(Symbol symLocal) {
//		return new OPAsign(symLocal, this);
//	}

	public InstructionHandle bytecodeGen(String clsName, MethodGen mg,
			ConstantPoolGen cp, InstructionFactory factory,
			InstructionList il, Map<String, Integer> argsMap, int argsStartPos,
			Map<Expr, Integer> funcRefsMap) {
		throw new UnsupportedOperationException();//il.append(InstructionConstants.NOP);
	}


	/**
	 * Reset the compile flags for an expression.
	 *
	 * For example, a matrix is defined and stored to new a local variable when it is referenced
	 * at the first time. The following references of the matrix just load the local variable.
	 * By calling this function, the state is reset to define a new local variable.
	 */
	public void bytecodeGenReset() {

	}

	public enum TYPE {INT, LONG, FLOAT, DOUBLE, BOOLEAN, BYTE, CHAR, SHORT, VOID,
		MATRIX, VECTOR, TENSOR};

	public abstract TypeInfo getTypeInfo();

	public TYPE getType() {
		return getTypeInfo().type;
	}

//	public Expr assign(Expr expr) {
//		return new LCAssign(this, expr);
//	}
//
//	public Expr assign(double val) {
//		return new LCAssign(this, Expr.valueOf(val));
//	}
//
//	public Expr assign(int val) {
//		return new LCAssign(this, Expr.valueOf(val));
//	}

/////////////////////////////////////////////////////////////////////////

//	protected LCDevice device = null;
//	public Expr runOn(LCDevice dev) {
//		device = dev;
//		return this;
//	}
//
//	public LCDevice getDevice() {
//		return device;
//	}

	/**
	 * Set the argument of the current expression
	 * Depends on the specific implementation
	 *
	 * @param index The position of the argument
	 * @param arg The expression of the argument
	 * @return
	 */
	public Expr setArg(int index, Expr arg) {
		throw new UnsupportedOperationException();
	}

	abstract public void updateLabel();

	/**
	 * Parent expression, for example: a sub-matrix or sub-vector has a parent
	 */
	public Expr getParent() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the dimension of a vector
	 * @return
	 */
	public int dim() {
		throw new UnsupportedOperationException();
	}

	/**
	 * return the dimension of a matrix or tensor
	 * @return
	 */
	public int[] dims() {
		throw new UnsupportedOperationException();
	}

	/**
	 * return the element at index
	 * @param index
	 * @return
	 */
	public Expr get(int index) {
		throw new UnsupportedOperationException();
	}
}

