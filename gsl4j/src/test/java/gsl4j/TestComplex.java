package gsl4j;

import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealBuilder;

public class TestComplex {

	public static void test1() {
		Real r1 = 2.3 ;
		Real r2 = 2 * r1 ;
		System.out.println(r2);
		System.out.println(r2.getClass());
		RealBuilder r3 = r2.getBuilder() - 3.0 ;
		System.out.println(r3);
		System.out.println(r3.getClass());
		r3 = r3 * 3.1 - 1.0 ;
		System.out.println(r3);
	}

	public static void main(String[] args) {
		test1() ;
	}

}
