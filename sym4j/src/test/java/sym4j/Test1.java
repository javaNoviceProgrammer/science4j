package sym4j;

import static org.sym4j.symbolic.Symbol.* ;
import static org.sym4j.symbolic.GreekSymbol.* ;
import static org.sym4j.math.SymMath.* ;

import org.sym4j.bytecode.BytecodeFunc;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.Func;
import org.sym4j.symbolic.GreekSymbol;
import org.sym4j.symbolic.utils.JIT;


public class Test1 {

	public static void test1() {
		Expr e1 = sin(x*x)*x+2.0 ;
		System.out.println(e1);
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLaTex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());

		int N = 1_000_000 ;
		double[] vals = new double[N] ;

		long start = System.currentTimeMillis() ;

		for(int i=0; i<N; i++) {
			vals[i] = f1bytecode.apply(i/(100.0) + 3.0) ;
		}

		long end = System.currentTimeMillis() ;
		System.out.println("Time (msec) = " + (end-start));

	}

	public static void test2() {
		Expr e1 = sin(x*x)*x+2.0 ;
		System.out.println(e1);
		Func f1 = new Func("f1", e1) ;
		System.out.println(f1);
		System.out.println(f1.toLaTex());
		BytecodeFunc f1bytecode = f1.toBytecodeFunc() ;
		System.out.println(f1bytecode.getClass());
		System.out.println(f1bytecode.apply(2.1));

		Expr e2 = e1.diff(x) ;
		System.out.println(e2);
		System.out.println(JIT.compile(e2).apply(1.1));
	}

	public static void test3() {
		Expr e1 = GreekSymbol.β ;
		System.out.println(e1.toLaTex());
		System.out.println(e1);

		Expr e2 = 3.1*β+2.1;
		System.out.println(e2.toLaTex());
		System.out.println(e2);
	}

	public static void test4() {
		String folder = "/Users/meisam/Desktop/test1" ;
		Expr e2 = 3.1 * β + 5.1 - α*α + γ + 2.1 * ξ * θ + sin(δ);
		System.out.println(e2.toLaTex());
		System.out.println(e2);
		e2.latexRender(folder, "test4");
	}

	public static void test5() {
		Expr e2 = 3.1 * β + 5.1 - α*α + γ + 2.1 * ξ * θ + sin(δ*cos(x)*tan(x+y));
		System.out.println(e2);
		System.out.println(e2.toLaTex());
		Expr e3 = e2.diff(x) ;
		System.out.println(e3);
		System.out.println(e3.toLaTex());
		String folder = "/Users/meisam/Desktop/test1" ;
		e3.latexRender(folder, "test4");
	}

	public static void test6() {
		Expr e1 = sqrt(x+5.0, 3) + 7.1 ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		BytecodeFunc f = JIT.compile(e1) ;
		System.out.println(f.apply(2.1));
	}

	public static void test7() {
		Expr e1 = x*7.1 ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		BytecodeFunc f = JIT.compile(e1) ;
		System.out.println(f.apply(2.1));
	}

	public static void test8() {
		Expr e1 = -x ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		BytecodeFunc f = JIT.compile(e1) ;
		System.out.println(f.apply(2.1));
	}

	public static void test9() {
		Expr e1 = 2-x ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		BytecodeFunc f = JIT.compile(e1) ;
		System.out.println(f.apply(2.1));
	}

	public static void test10() {
		Expr e1 = (2/(3+x))*5 ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		System.out.println(e1.toLaTex());
		BytecodeFunc f = JIT.compile(e1) ;
		System.out.println(f.apply(2.1, 5.0));
	}

	public static void test11() {
		Expr e1 = pow(x, 3) ;
		System.out.println(e1);
		System.out.println(e1.getLatexLabel());
		System.out.println(e1.toLaTex());
		BytecodeFunc f = JIT.compile(e1) ;
		System.out.println(f.apply(2.1, 5.0));
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
		test11() ;
	}

}
