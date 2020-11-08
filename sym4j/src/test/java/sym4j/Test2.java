package sym4j;


import static org.sym4j.symbolic.symbols.Symbol.*;

import org.sym4j.bytecode.BytecodeFunc;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.Func;
import static org.sym4j.math.SymMath.* ;



public class Test2 {

	public static void test1() {
		Expr e1 = sinh(x+3) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLaTex());
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
		System.out.println(f1.toLaTex());
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
		System.out.println(f1.toLaTex());
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
		System.out.println(f1.toLaTex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));
	}

	public static void test5() {
		Expr e1 = coth(x) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLaTex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));
	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
//		test4() ;
		test5() ;
	}

}
