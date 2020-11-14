package sym4j;


import static org.sym4j.math.SymMath.* ;
import static org.sym4j.symbolic.symbols.Symbol.* ;

import org.sym4j.bytecode.BytecodeFunc;
import org.sym4j.latex.LatexBinary;
import org.sym4j.latex.LatexBracket;
import org.sym4j.latex.LatexUnary;
import org.sym4j.matrix.ExprVector;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.Func;
import org.sym4j.symbolic.symbols.GreekSymbol;
import org.sym4j.symbolic.symbols.Symbol;



public class Test2 {

	public static void test1() {
		Expr e1 = sinh(x+3) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLatex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));
	}

	public static void test2() {
		Expr e1 = cosh(x+3) + sinh(2*x)*cos(x) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLatex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));

	}

	public static void test3() {
		Expr e1 = j*cosh(x) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLatex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));
	}

	public static void test4() {
		Expr e1 = tanh(x).diff(x) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLatex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));
	}

	public static void test5() {
		Expr e1 = sinh(x*x+tanh(x)).diff(x) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLatex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));
	}

	public static void test6() {
//		Expr e1 = sqrt(x+1, 2*x) ;
		Expr e1 = pow(x+1, 2*x) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
	}

	public static void test7() {
		Expr e1 = sec(x) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLatex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));
	}

	public static void test8() {
		Expr e1 = csc(x) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLatex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));
	}

	public static void test9() {
		Expr e1 = cot(x).diff(x) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLatex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));
	}

	public static void test10() {
		ExprVector e1 = new ExprVector(new Expr[] {x+2, sin(x)*tan(y), tanh(GreekSymbol.α)}) ;
		Expr e2 = e1.trans() ;
		System.out.println(e2.toLatex());
	}

	public static void test11() {
		Expr e1 = LatexBracket.leftbrace ;
		System.out.println(e1.getLatexLabel());
		System.out.println(e1);
	}

	public static void test12() {
		Expr e1 = arcsin(tan(x+3.1)) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Expr e2 = e1.diff(x) ;
		System.out.println(e2);
		System.out.println(e2.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.diff(x).getLatexLabel());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(0.999999));
	}

	public static void test13() {
		Expr e1 = LatexUnary.overrightarrow(x+tan(y)*GreekSymbol.α) + LatexUnary.overleftarrow(sin(x)) ;
		System.out.println(e1.getLatexLabel());
		System.out.println(e1);
	}

	public static void test14() {
		Symbol a1 = new Symbol("a_1") ;
		Symbol a2 = new Symbol("a_2") ;
		Expr e3 = LatexBinary.overbrace(a2+a1+a1, x) ;
		System.out.println(e3);
		System.out.println(e3.toLatex());
		String folder = "/Users/meisam/Desktop/test1" ;
		e3.latexRender(folder, "test4");
	}


	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
//		test4() ;
//		test5() ;
//		test6() ;
//		test7() ;
//		test8() ;
//		test9() ;
//		test10() ;
//		test11() ;
//		test12() ;
//		test13() ;
		test14() ;
	}

}
