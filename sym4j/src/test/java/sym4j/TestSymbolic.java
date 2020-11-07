package sym4j;

import org.sym4j.bytecode.BytecodeFunc;
import org.sym4j.domains.Domain;
import org.sym4j.domains.Domain2D;
import org.sym4j.domains.Interval;
import org.sym4j.math.Div;
import org.sym4j.math.Dot;
import org.sym4j.math.Grad;
import org.sym4j.matrix.ExprVector;
import org.sym4j.numeric.NumVector;
import org.sym4j.relational.Ge;
import org.sym4j.relational.Gt;
import org.sym4j.relational.Le;
import org.sym4j.relational.Lt;
import org.sym4j.relational.Neq;
import org.sym4j.symbolic.Expr;
import org.sym4j.symbolic.Func;
import org.sym4j.symbolic.Integrate;
import org.sym4j.symbolic.Reciprocal;
import org.sym4j.symbolic.Sum;
import org.sym4j.symbolic.SymDouble;
import org.sym4j.symbolic.SymFloat;
import org.sym4j.symbolic.SymInteger;
import org.sym4j.symbolic.SymLong;
import org.sym4j.symbolic.SymRandom;
import org.sym4j.symbolic.SymReal;
import org.sym4j.symbolic.Symbol;
import org.sym4j.symbolic.Symbols;
import org.sym4j.symbolic.utils.JIT;
import static org.sym4j.symbolic.Symbol.* ;
import static org.sym4j.math.SymMath.* ;


public class TestSymbolic {
	public static void checkResult(String expect, Expr expr) {
		if( expect.equals(expr.toString()) )
			System.out.println(true);
		else
			System.out.println("FAIL: " + expect +" != " + expr);
	}
	public static void checkResult(Expr expr1, Expr expr2) {
		if( expr1.symEquals(expr2) )
			System.out.println(true);
		else
			System.out.println("FAIL: " + expr1 +" != " + expr2);
	}
	public static void checkResult(double d1, double d2, Expr expr) {
		if( Math.abs(d1 - d2) < 1e-10 )
			System.out.println(true);
		else {
			if(expr != null)
				System.out.println("FAIL: " + expr + " => " + d1 +" != " + d2);
			else
				System.out.println("FAIL: " + d1 +" != " + d2);
		}
	}
	public static void checkResult(String s1, String s2) {
		if( s1.equals(s2) )
			System.out.println(true);
		else {
			System.out.println("FAIL: " + s1 +" != " + s2);
		}
	}

	public static void testBasic() {

		System.out.println("--------------testBasic-----------------");
		checkResult(new Symbol("s1"), new Symbol("s1"));

		checkResult("1 + x",1 + x);
		checkResult("2*x",2*x);

		checkResult("x + y", x + y);
		checkResult("x - y",x - y);
		checkResult("x*y",x * y);
		checkResult("x/y",x / y);
		checkResult("-x",- x);
		checkResult("x^2", pow(x,2));

		checkResult("0", Symbol.Cm1 + Symbol.C1);
		checkResult("0", x + (-x));
		checkResult("-1", Symbol.Cm1 * Symbol.C1);
		checkResult("-x", x * -1);
		checkResult("0", -Symbol.C0);
		checkResult("1", -Symbol.C0 + 1);

		checkResult("1/x", new Reciprocal(x));
		checkResult("1/x", Symbol.C1 / x);
		checkResult("x/y", x * new Reciprocal(y));
		checkResult("1/(x*y)", new Reciprocal(x) * new Reciprocal(y));

		checkResult("r + x + y + z", (x + y) + (z + r));
		checkResult("r*x*y*z", (x * y) * (z * r));

		checkResult("x/(y*z)", x / (y * z));
		checkResult("x/(y*z)", x / y / z);
		checkResult("x + y/z", x + y / z);

		checkResult("r*x + s/y + t - z", r * x + s / y + t - z);

	}
	public static void testPrint() {
		System.out.println("--------------testPrint-----------------");
		checkResult("x*y + x*z", (x*(y+z)));
		checkResult("x/(y + z)", (x/(y+z)));
		checkResult("(y + z)^2", ((y+z)*(y+z)));
		//checkResult("y^2 + 2*y*z + z^2", ((y+z)*(y+z)));
		checkResult("-(y + z)", (-(y+z)));
	}
	public static void testSimplify() {

		System.out.println("--------------testSimplify-----------------");
		Expr expr = null;

		SymInteger n1 = new SymInteger(1);
		SymLong n2 = new SymLong(2);
		SymFloat n3 = new SymFloat(3.0f);
		SymDouble n4 = new SymDouble(4.0);
		expr = n1 + n2 + n3 + n4;
		checkResult("10.0",expr);
		expr = n1 * n2 * n3 * n4;
		checkResult("24.0",expr);
		expr = n4 - n2;
		checkResult("2.0",expr);
		expr = n4 / n2;
		checkResult("2.0",expr);
		expr = n2 * (n1 + n3) / n4;
		checkResult("2.0",expr);

		checkResult(x + y + z, x + z + y);
		checkResult(x + y + z, y + x + z);
		checkResult(x + y + z, y + z + x);
		checkResult(x + y + z, z + x + y);
		checkResult(x + y + z, z + y + x);

		checkResult(x * y * z, x * z * y);
		checkResult(x * y * z, y * x * z);
		checkResult(x * y * z, y * z * x);
		checkResult(x * y * z, z * x * y);
		checkResult(x * y * z, z * y * x);

		Expr ry = new Reciprocal(y);
		checkResult("2/y",ry + ry);
		checkResult("1/y^2",ry * ry);
		Expr ny = -y;
		checkResult("-2*y",ny + ny);
		checkResult("y^2",ny * ny);
		checkResult(y*y, ny * ny);

		checkResult("x^5*y*z", x * y * x * z * x * pow(x,2));

		checkResult("x^3*y*z + 2*(y*z)^2", (x * y * x * z * x) + (y * z * y * z) + (z * y * z * y));

		checkResult("x^2*y + x^2*z + x*y^2 + 2*x*y*z + x*z^2 + y^2*z + y*z^2", (x + y) * (y + z) * (z + x) );

		checkResult("x^2*y + x*z^2 + y^2*z", (x * y * x) + (y * z * y) + (z * x * z) );


		checkResult("x^2 - y^2", (x + y) * (x - y));
		checkResult("r*s*x*z + r*s*y*z", ((x + y) * z) * (r * s));
		checkResult(((x + y) * z) * (r * s), x*z*r*s + y*z*r*s);

		checkResult(x * y * z + r + s + t, r + s + t + z * y * x);

		expr = x + y + z;
		Expr sub_expr = expr.subs(x, 1).subs(y, 2L).subs(z, 3.0d);
		checkResult("1 + 2 + 3.0", sub_expr);
		checkResult("6.0", sub_expr.simplify());

		expr = (x + 1) + 2;
		checkResult("3 + x", expr);

		expr = 2 + (x + 1);
		checkResult("3 + x", expr);

		expr = (y + z) + (y + 1);
		checkResult("1 + 2*y + z", expr);

		expr = (y * z) * (y * 2);
		checkResult("2*y^2*z", expr);

		expr = x + y + z;
		Expr yz= y + z;
		sub_expr = expr.subs(x, yz);//.simplify();
		checkResult("y + z + y + z", sub_expr);
		checkResult(sub_expr, (z + y) + (y + z));
		checkResult(sub_expr, (z + y)*2.0);

		checkResult(r * x + s / y + t - z, s / y + r * x + t - z);
		checkResult(r * x + s / y + t - z, r * x + t + s / y - z);
		checkResult(r * x + s / y + t - z, r * x + s / y - z + t);
		checkResult(r * x + s / y + t - z, t + r * x + s / y - z);

		checkResult(x * -(y + z), -x*y - x*z);
	}

	public static void testSummation() {

		System.out.println("--------------testSummation-----------------");
		Expr sum = new Sum( x*x, x, 1, 5);
		checkResult("\\Sigma_{x=1}^5{x^2}", sum);
		checkResult("55.0", sum.simplify());
		checkResult("\\Sigma_{x=1}^5{4.0}", sum.subs(x, 2));
		checkResult("20.0", sum.subs(x, 2).simplify());
		checkResult("0", sum.diff(x));
		checkResult("5*x^2", new Sum(x*x, y, 1, 5).simplify());
//
		Symbol i = new Symbol("i");
//		Symbols xi = new Symbols("x", i);
//		checkResult("x_i", xi);
//		checkResult("x_2", xi.get(2));
//
//		Sum sum2 = new Sum( xi*xi, i, 1, 5);
//		checkResult("\\Sigma_{i=1}^5{(x_i)^2}",sum2);
//		checkResult("2*x_1",sum2.diff(xi.get(1)));
//
//		for(int j=sum2.start; j<sum2.end; j++) {
//			checkResult("x_"+j+"^2", sum2.getSummand(j));
//		}
//		Expr summand2 = sum2.getSummand(2).subs(xi.get(2), y);
//		checkResult("y^2",summand2);
//
//		int n = 100;
//		Expr sum3 = new Sum(new Reciprocal((x+3.5)*(x+8)), x, 1, n);
//		checkResult("\\Sigma_{x=1}^100{1/(28.0 + 11.5*x + x^2)}", sum3);
//
//		checkResult("\\Sigma_{y^2=1}^5{x}", Sum.apply(x, y*y, 1, 5));

		Symbol j = new Symbol("j");
		Symbols yj = new Symbols("y", j);
		Symbols yjm1 = new Symbols("y", j-1);
		Symbols yjmi = new Symbols("y", j-i);
		checkResult("y_{j-i}",yjmi);

		checkResult("",yj.get(1));
		checkResult("",yjm1.get(1));
		checkResult("",yjmi.get(i,1));
		checkResult("",yjmi.get(j,2));

		Sum sum4 = Sum.apply(yj+yjm1, j, 1, 5);
		checkResult("\\Sigma_{y^2=1}^5{x}", sum4);
		checkResult("\\Sigma_{y^2=1}^5{x}", sum4.getSummand(1));
		checkResult("\\Sigma_{y^2=1}^5{x}", sum4.getSummand(2));
	}

	public static void testToBytecodeFunc() {

		System.out.println("--------------testToBytecodeFunc-----------------");
		Expr expr = pow(x + y * z, 2);
		checkResult("(x + y*z)^2", expr);

//		List<Expr> list = new ArrayList<Expr>();
//		BytecodeUtils.post_order(expr, list);
//		for(Expr e : list)
//			System.out.println(e.getClass());

		Func f = new Func("test_fun1",expr);
		checkResult("(x + y*z)^2",f);

		BytecodeFunc func = f.toBytecodeFunc();
		checkResult(49.0, func.apply(1,2,3), null);

		Func c = new Func("test_const", new SymInteger(8));
		func = c.toBytecodeFunc();
		checkResult(8.0, func.apply(0.0), null);

		Reciprocal rec = new Reciprocal(x + y);

		checkResult(0.5, new Func("test_reciprocal", rec).toBytecodeFunc().apply(1,1), rec);

	}

	public static void testDiff() {

		System.out.println("--------------testDiff-----------------");
		Expr expr = x*x*2.0 + x + 1.0;
		checkResult("1 + 4.0*x", expr.diff(x));

		Expr expr2 = -(x*x + y*y);
		checkResult("-2*x", expr2.diff(x));
		checkResult("-2*y", expr2.diff(y));

		Func F = new Func("F", x*x*x*y+x*x*y+z);
		checkResult("x^2*y + x^3*y + z",F);
		checkResult("3*x^2*y + 2*x*y",F.diff(x));
		checkResult("6*x*y + 2*y",F.diff(x).diff(x));
		Expr diff1 = F.diff(x).diff(x).diff(y);
		checkResult("2 + 6*x",diff1);

		Func f = new Func("f", x,y,z);
		checkResult("f(x,y,z)*x",f*x);
		checkResult("DfDxxy(x,y,z)",f.diff(x).diff(x).diff(y));
		checkResult("DfDx(x,y,z)*x + f(x,y,z)",(f*x).diff(x));
		checkResult("2*DfDx(x,y,z) + DfDxx(x,y,z)*x",(f*x).diff(x).diff(x));
		checkResult("DfDxxy(x,y,z)*x + 2*DfDxy(x,y,z)",(f*x).diff(x).diff(x).diff(y));
		Func test_fun2 = new Func("test_fun2",expr2);
		checkResult("-(x^2 + y^2)", test_fun2);
		//checkResult(-13.0, test_fun2.toBytecodeFunc().apply(2,3), null);
	}

	public static void testAbstract() {

		System.out.println("--------------testAbstract-----------------");
		//Test for functional derivative
		Func u = new Func("u", x,y,z);
		Func v = new Func("v", x,y,z);
		Func L = new Func("L", u * u);
		Symbol alp = new Symbol("a");
		Func du = new Func("du", x,y,z);
		Expr Lu = L.subs(u, u + alp * du).diff(alp);
		checkResult("2*a*(du(x,y,z))^2 + 2*du(x,y,z)*u(x,y,z)", Lu);
		checkResult("2*du(x,y,z)*u(x,y,z)", Lu.subs(alp, Symbol.C0).simplify());

		Grad gu = new Grad(u);
		checkResult("\\nabla{u(x,y,z)}",gu.toString());
		Grad gv = new Grad(v);
		checkResult("\\nabla{u(x,y,z)} \\cdot \\nabla{v(x,y,z)}",new Dot(gu, gv));
		checkResult("3",Dot.apply(new Grad(x+y+z), new Grad(x+y+z)));

		Func w = new Func("w", x, y, x);
		checkResult("\\nabla{w(x,y,x)} \\cdot \\nabla{v(x,y,z)}", new Dot(gu, gv).fdiff(u,w));

		Func f = new Func("F");
		ExprVector grad = Grad.apply(f);
		Div div = new Div(grad);
		//checkResult("\\nabla \\cdot \\nabla{F}", div);
		checkResult("div(\\nabla{F})", div);

		Expr expr = x*x + y*y +z*z;
		checkResult("div(\\nabla{F})", Div.apply(Grad.apply(expr)));
	}

	public static void testIntegration() {
		Domain I = Interval.apply(-oo, 1.0);
		Expr t1 = Integrate.apply(x, I);
		checkResult("x + \\int_{-oo}^{1.0}{x}dx", t1 + x);

		Domain I2 = Interval.apply(-oo, x);
		Expr t2 = Integrate.apply(pow(e,r), I2);
		checkResult("\\int_{-oo}^{x}{e^r}dx", t2);

		System.out.println(t2.toLaTex());

		checkResult("e^r", t2.diff(x));

		Domain D = new Domain2D("D",x,y);
		checkResult("\\int_{D}{0.5*x^2 + 0.5*y^2}dxdy", Integrate.apply(0.5*(x*x+y*y), D));
	}

	public static void testPower() {
		Expr expr = pow(x, 2.1) * pow(x,2);
		checkResult("x^4.1", expr);
		checkResult("x^0.5", pow(x,0.5));

		checkResult("\\sqrt{x}", sqrt(x));
		checkResult("\\sqrt[3]{x}", sqrt(x,3));

		checkResult("e^x", exp(x));
		checkResult("e^x", exp(x).diff(x));
		checkResult("e^x^2*2*x", exp(x*x).diff(x));
		checkResult("e^2", exp(2));


		Func fun = new Func("fexpr",pow(x,3));
		BytecodeFunc bfun = fun.toBytecodeFunc();
		checkResult("8.0",String.valueOf(bfun.apply(2)));

		Func fun2 = new Func("fexpr",pow(x,0.5));
		BytecodeFunc bfun2 = fun2.toBytecodeFunc();
		checkResult("2.0",String.valueOf(bfun2.apply(4)));

		checkResult(1.0,JIT.compile(sin(x)).apply(Math.PI/2), sin(x));
		checkResult(0.0,JIT.compile(sin(x)).apply(0), sin(x));

		checkResult(0.0,JIT.compile(cos(x)).apply(Math.PI/2), cos(x));
		checkResult(1.0,JIT.compile(cos(x)).apply(0), cos(x));

		checkResult(Math.tan(Math.PI/4),JIT.compile(tan(x)).apply(Math.PI/4), tan(x));
		checkResult(0.0,JIT.compile(tan(x)).apply(0), tan(x));

		checkResult(0.0,JIT.compile(log(x)).apply(1), log(x));
		checkResult(0.0,JIT.compile(log10(x)).apply(1), log10(x));
		checkResult(0.0,JIT.compile(log2(x)).apply(1), log2(x));

		checkResult(1.0,JIT.compile(log(x)).apply(Math.E), log(x));
		checkResult(1.0,JIT.compile(log10(x)).apply(10), log10(x));
		checkResult(1.0,JIT.compile(log2(x)).apply(2), log2(x));

		checkResult(3.0,JIT.compile(log(x,y)).apply(3,27), log(x,y));
		checkResult(3.0,JIT.compile(log10(x)).apply(1000), log10(x));
		checkResult(3.0,JIT.compile(log2(x)).apply(8), log2(x));

		checkResult(Math.E*Math.E,JIT.compile(exp(x)).apply(2), exp(x));
		checkResult(1.0,JIT.compile(exp(x)).apply(0), exp(x));

		checkResult(1.0,JIT.compile(exp(-0.5*pow(z,2))).apply(2), exp(-0.5*pow(z,2)));

		Domain I = Interval.apply(-oo, x, z);
		Expr cdf = Integrate.apply(exp(-0.5*pow(z,2)), I)/sqrt(PI2);
		checkResult("1/\\sqrt{2\\pi}*\\int_{-oo}^{x}{e^{-0.5*z^2}}dz",cdf);
		Domain I2 = Interval.apply(-10, x, z).setStepSize(0.001);
		Expr cdf2 = Integrate.apply(exp(-0.5*pow(z,2)), I2)/sqrt(PI2);
		checkResult(1.0,JIT.compile(cdf2).apply(10), cdf);
	}

	public static void testSinCosTan() {

		checkResult("sin(x)",sin(x));
		checkResult("cos(x)",sin(x).diff(x));
		checkResult("cos(x)",cos(x));
		checkResult("-sin(x)",cos(x).diff(x));
		checkResult("tan(x)",tan(x));
		checkResult("1 + (tan(x))^2",tan(x).diff(x));

		checkResult("cos(cos(x))*-sin(x)",sin(cos(x)).diff(x));
		checkResult("-sin(x)*cos(x)",cos(sin(x)).diff(x));
		checkResult("cos(x)*(1 + (tan(sin(x)))^2)",tan(sin(x)).diff(x));

	}

	public static void testSymReal() {
		SymReal<Double> a = new SymReal<Double>(0.0);
		SymReal<Double> aa = new SymReal<Double>(-0.0);
		SymReal<Long> b = new SymReal<Long>(0L);
		System.out.println(a.symEquals(b));
		System.out.println(aa.symEquals(b));
		System.out.println(a.symEquals(aa));
	}

	public static void testLogic() {
		System.out.println(x & y);
		System.out.println(x | y);
		System.out.println(x ^ y);
		System.out.println(~x);
		//System.out.println(x < y);

		System.out.println(Gt.apply(x, y) | Gt.apply(x, z));
		System.out.println(Ge.apply(x, y) | Ge.apply(x, z));
		System.out.println(Le.apply(x, y) | Le.apply(x, z));
		System.out.println(Lt.apply(x, y) | Lt.apply(x, z));
		System.out.println(Neq.apply(x, y) | Neq.apply(x, z));

//		Func f1 = new Func("fun1", Gt.apply(x, y));
//		Func f1 = new Func("fun1", Ge.apply(x, y));
//		Func f1 = new Func("fun1", Lt.apply(x, y));
//		Func f1 = new Func("fun1", Le.apply(x, y));
//		Func f1 = new Func("fun1", Eq.apply(x, y));
//		Func f1 = new Func("fun1", Neq.apply(x, y));
//		Func f1 = new Func("fun1", Gt.apply(x, y) & Gt.apply(x, z));
//		Func f1 = new Func("fun1", Gt.apply(x, y) | Gt.apply(x, z));
//		Func f1 = new Func("fun1", Gt.apply(x, y) ^ Gt.apply(x, z));
		Func f1 = new Func("fun1", ~Gt.apply(x, y));
//		Func f1 = new Func("fun1", ~x); //NOT Supported
		BytecodeFunc ff1 = f1.toBytecodeFunc();
		System.out.println(ff1.apply(5,3));

	}

	public static void testJITVectorized() {
		ExprVector v = new ExprVector();
		for(int i=0; i<101; i++)
			v[i] = x*x + 1;
		NumVector nv = v.toNumVector(new Expr[]{x});
		double[] outAry = new double[nv.dim()];
		for(int j=0; j<100000; j++)
			nv.eval(outAry, 2.0);
		for(int i=0; i<outAry.length; i++) {
			System.out.println(outAry[i]);
		}
	}
	public static void testMatrixVector() {

	}

	public static void testSymRandom() {
		BytecodeFunc f = JIT.compile(new SymRandom());
		System.out.println(f.apply());
	}


	public static double eps = 1e-5;
	public static void check(String info, double d1, double d2) {
		if(Math.abs(d1-d2) < eps) {
			System.out.println("pass");
		} else {
			System.out.println("!!!FAIL!!!   "+d1+"!="+d2+" "+info);
		}
	}

	public static void testMathematicalFunction() {
		//TODO compile use bytecoeGen()
		check(sqrt(x).toString(), JIT.compile(sqrt(x)).apply(0.1), Math.sqrt(0.1));
	}

	public static void main(String[] args) {
//		testBasic();
//		testPrint();
//		testSimplify();
//		testSummation();
//		testToBytecodeFunc();
//		testDiff();
//		testAbstract();
		testIntegration();
//		testPower();
//		testSymReal();
//		testSinCosTan();
//		testLogic();

		//set vm parameters: -XX:+PrintCompilation
		//testJITVectorized();
		//testSymRandom();
//		testMathematicalFunction();
	}
}
