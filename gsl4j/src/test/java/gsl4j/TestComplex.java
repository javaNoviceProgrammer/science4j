package gsl4j;

import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealBuilder;
import org.gsl4j.complex.RealNumber;

import static org.gsl4j.complex.Complex.* ;

import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexMath;
import org.gsl4j.complex.ComplexNumber;



public class TestComplex {

	public static void test1() {
		Real r1 = 2.3 ;
		Real r2 = 2 * r1 ;
		System.out.println(r2);
		System.out.println(r2.getClass());
		RealBuilder r3 = r2.getBuilder() - 3.0 ;
		System.out.println(r3);
		System.out.println(r3.getClass());
		r3 = r3 + 3.1 - 1.0 ;
		System.out.println(r3);
	}

	public static void test2() {
		Complex.debug = true ;
		Real.debug = true ;
		Real r1 = 5.1 ;
		System.out.println(r1);
		System.out.println(r1.getClass());
		ComplexNumber c1 = 2.0*j.getBuilder()-1.0 ;
		System.out.println(c1);
		System.out.println(c1.getClass());
		ComplexNumber c2 = c1.getBuilder().multiply(-1.0, 1.0) ;
		System.out.println(c2);
		System.out.println(c2.getClass());
		System.out.println(c1);
		System.out.println(c1==c2);
		ComplexNumber c3 = (c2+1.1).multiply(2.0).divide(j) ;
		System.out.println(c2);
		System.out.println(c2.getClass());
		System.out.println(c3);
		System.out.println(c3.getClass());
		System.out.println(c2==c3);
		System.out.println(c1==c3);
		System.out.println(c1);
	}

	public static void test3() {
		Complex.debug = true ;
		Complex.setDisplayAccuracy(6);
		ComplexNumber w1 = 1.0+j ;
		System.out.println(w1);
		System.out.println(w1.getClass());
		ComplexNumber w2 = ComplexMath.pow(w1, j) ;
		System.out.println(w2);
		System.out.println(w2.getClass());
		ComplexNumber logw2 = ComplexMath.logb(w2, j) ;
		System.out.println(logw2);
		System.out.println(logw2.getClass());
	}

	public static void test4() {
		Complex.debug = true ;
		Complex.setDisplayAccuracy(6);
		ComplexNumber w1 = Real.of(-1.1) ;
		System.out.println("w1 = " + w1);
		System.out.println(w1.getClass());
		ComplexNumber w2 = ComplexMath.inverse(w1.getBuilder()) ;
		System.out.println("w2 = " + w2);
		System.out.println(w2.getClass());
		System.out.println(w1.conjugate());
		System.out.println(w1.conjugate()==w1);
		System.out.println(ComplexMath.conjugate(w2));
		System.out.println(w2);
		System.out.println(ComplexMath.inverse(w2));
		System.out.println(w2);
		ComplexNumber w3 = ComplexMath.sqrt(w1) ;
		System.out.println(w3);
		System.out.println(w3.getClass());
		System.out.println(ComplexMath.powReal(w3, 2));
		System.out.println(ComplexMath.exp(w3));
		System.out.println(ComplexMath.log(w3));
		System.out.println(ComplexMath.sin(w3));
		System.out.println(ComplexMath.arcsin(w3));
		System.out.println(ComplexMath.arctan(w3));
	}

	public static void test5() {
		RealNumber a = Real.of(2.3).getBuilder() ;
		System.out.println(a);
		System.out.println(a.getClass());
		ComplexNumber b = a * j ;
		System.out.println(b);
		System.out.println(b.getClass());
		ComplexNumber c = b.getBuilder() * j ;
		System.out.println(c);
		System.out.println(c.getClass());
		ComplexNumber d = (c+3.1)/(2.0+j) ;
		System.out.println(d);
		System.out.println(c==d);
	}

	public static void test6() {
		Real r1 = Double.valueOf(2.3) ;
		System.out.println(r1);
	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
//		test4() ;
//		test5() ;
		test6() ;
	}

}
