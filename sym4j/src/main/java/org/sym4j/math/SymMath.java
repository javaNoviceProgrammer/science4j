package org.sym4j.math;

import org.sym4j.matrix.ExprMatrix;
import org.sym4j.matrix.ExprVector;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.Vector;
import org.sym4j.symbolic.funcs.Abs;
import org.sym4j.symbolic.funcs.Cos;
import org.sym4j.symbolic.funcs.Cosh;
import org.sym4j.symbolic.funcs.Cot;
import org.sym4j.symbolic.funcs.Coth;
import org.sym4j.symbolic.funcs.Csc;
import org.sym4j.symbolic.funcs.Exp;
import org.sym4j.symbolic.funcs.Log;
import org.sym4j.symbolic.funcs.Log10;
import org.sym4j.symbolic.funcs.Log2;
import org.sym4j.symbolic.funcs.Pow;
import org.sym4j.symbolic.funcs.Sec;
import org.sym4j.symbolic.funcs.Sin;
import org.sym4j.symbolic.funcs.Sinh;
import org.sym4j.symbolic.funcs.Sqrt;
import org.sym4j.symbolic.funcs.Tan;
import org.sym4j.symbolic.funcs.Tanh;
import org.sym4j.symbolic.symbols.SymComplex;
import org.sym4j.symbolic.symbols.SymConst;
import org.sym4j.symbolic.symbols.SymRandom;
import org.sym4j.symbolic.utils.Utils;

public class SymMath {
	/**
	 * Pre defined constant symbols
	 */
	public static SymConst PI = new SymConst("pi", Math.PI);
	public static SymConst PI2 = new SymConst("2*pi", 2*Math.PI);
	public static SymConst E = new SymConst("e", Math.E);

	public static SymComplex j = new SymComplex(C(0.0), C(1.0)) ;

	/**
	 * A quick way to define constant real number symbols
	 * @param v
	 * @return
	 */
	public static Expr C(double v) {
		return Expr.valueOf(v);
	}
	public static Expr C(float v) {
		return Expr.valueOf(v);
	}
	public static Expr C(int v) {
		return Expr.valueOf(v);
	}
	public static Expr C(long v) {
		return Expr.valueOf(v);
	}

	public static Expr pow(Expr base, double exponent) {
		return Pow.simplifiedIns(base, Expr.valueOf(exponent));
	}
	public static Expr pow(double base, Expr exponent) {
		return Pow.simplifiedIns(Expr.valueOf(base), exponent);
	}

	public static Expr pow(Expr base, Expr exponent) {
		return Pow.simplifiedIns(base, exponent);
	}

	public static Expr sqrt(Expr arg) {
		return new Sqrt(arg);
	}

	public static Expr sqrt(Expr arg, double root) {
		return new Sqrt(arg, Expr.valueOf(root));
	}

	public static Expr sqrt(Expr arg, int root) {
		return new Sqrt(arg, Expr.valueOf(root));
	}

	public static Expr sqrt(Expr arg, Expr root) {
		return Sqrt.simplifiedIns(arg, root);
	}

	public static Expr exp(Expr x) {
		return Exp.simplifiedIns(x);
	}

	public static Expr exp(double x) {
		return Exp.simplifiedIns(Expr.valueOf(x));
	}

	public static Expr log(Expr x) {
		return Log.simplifiedIns(x);
	}

	public static Expr log(Expr base, double expr) {
		return Log.simplifiedIns(base, Expr.valueOf(expr));
	}

	public static Expr log(double base, Expr expr) {
		return Log.simplifiedIns(Expr.valueOf(base), expr);
	}

	public static Expr log(Expr base, Expr expr) {
		return Log.simplifiedIns(base, expr);
	}

	public static Expr log10(Expr x) {
		return Log10.simplifiedIns(x);
	}

	public static Expr log2(Expr x) {
		return Log2.simplifiedIns(x);
	}

	///////////////////////////////////////////////////////////////

	public static Expr sin(Expr x) {
		return Sin.simplifiedIns(x);
	}

	public static Expr cos(Expr x) {
		return Cos.simplifiedIns(x);
	}

	public static Expr tan(Expr x) {
		return Tan.simplifiedIns(x);
	}

	public static Expr cot(Expr x) {
		return Cot.simplifiedIns(x) ;
	}

	public static Expr sec(Expr x) {
		return Sec.simplifiedIns(x) ;
	}

	public static Expr csc(Expr x) {
		return Csc.simplifiedIns(x) ;
	}

	///////////////////////////////////////////////////////////////

	public static Expr sinh(Expr x) {
		return Sinh.simplifiedIns(x) ;
	}

	public static Expr cosh(Expr x) {
		return Cosh.simplifiedIns(x) ;
	}

	public static Expr tanh(Expr x) {
		return Tanh.simplifiedIns(x) ;
	}

	public static Expr coth(Expr x) {
		return Coth.simplifiedIns(x) ;
	}

	//////////////////////////////////////////////////////////////

	public static Expr random() {
		return new SymRandom();
	}

	public static Expr dot(Vector l, Vector r) {
		return Dot.apply(l, r);
	}

	public static Expr dot(ExprVector l, ExprVector r) {
		return Dot.apply(l, r);
	}

	public static Expr dot(double[] l, ExprVector r) {
		ExprVector v = new ExprVector(l, 0, r.dim());
		return Dot.apply(v, r);
	}

	public static Expr dot(ExprVector l, double[] r) {
		ExprVector v = new ExprVector(r, 0, l.dim());
		return Dot.apply(l, v);
	}

	////////////////////////////////////////////////////////////////
	public static Expr abs(Expr x) {
		return Abs.simplifiedIns(x);
	}



//	public static Expr dot(double[] l, double[] r) {
//		double sum = 0.0;
//		for(int i=0; i<l.length; i++)
//			sum += l[i]*r[i];
//		return sum;
//	}
//	???
//	Exception in thread "main" java.lang.VerifyError: Bad type on operand stack
//	Exception Details:
//	  Location:
//	    symjava/math/SymMath.dot([D[D)Lsymjava/symbolic/Expr; @34: dreturn
//	  Reason:
//	    Type 'symjava/symbolic/Expr' (current frame, stack[0]) is not assignable to double_2nd
//	  Current Frame:
//	    bci: @34
//	    flags: { }
//	    locals: { '[D', '[D', double, double_2nd, integer }
//	    stack: { 'symjava/symbolic/Expr' }
//	  Bytecode:
//	    0000000: 0e49 0336 04a7 0012 282a 1504 312b 1504
//	    0000010: 316b 6349 8404 0115 042a bea1 ffed 28b8
//	    0000020: 002c af
//	  Stackmap Table:
//	    append_frame(@8,Double,Integer)
//	    same_frame(@23)
//
//		at symjava.examples.SVM.main(SVM.java:11)

	/**
	 * Return Gradient of f
	 * @param f
	 * @return
	 */
	public static ExprVector grad(Expr f) {
		return Grad.apply(f);
	}

	/**
	 * Return Hessian Matrix of f
	 * @param f
	 * @return
	 */
	public static ExprMatrix hess(Expr f) {
		Expr[] args = Utils.extractSymbols(f).toArray(new Expr[0]);
		ExprVector grad = Grad.apply(f, args);
		ExprMatrix mat = new ExprMatrix();
		for(Expr e : grad) {
			mat.append(Grad.apply(e, args));
		}
		return mat;
	}

}
