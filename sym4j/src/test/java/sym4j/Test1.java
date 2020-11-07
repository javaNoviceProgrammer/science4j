package sym4j;

import static org.sym4j.symbolic.Symbol.* ;
import static org.sym4j.math.SymMath.* ;

import org.sym4j.bytecode.BytecodeFunc;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.Func;


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

	public static void main(String[] args) {
		test1() ;
	}

}
