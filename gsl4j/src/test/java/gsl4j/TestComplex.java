package gsl4j;

import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealBuilder;
import static org.gsl4j.complex.Complex.* ;

import org.gsl4j.complex.Complex;
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

	public static void main(String[] args) {
		test1() ;
		test2() ;
	}

}
