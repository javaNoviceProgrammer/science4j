package gsl4j;

import static org.gsl4j.complex.Complex.j;

import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.matrix.AlgebraVector;
import org.gsl4j.matrix.RealVector;


public class TestVector {

	public static void test1() {
		Complex.debug = true ;
		ComplexBuilder.debug = true ;
		AlgebraVector v1 = new RealVector(1.1, 2.2, 5.0, 10.0, -2.0) ;
		System.out.println(v1);
		System.out.println(v1.getClass());
		System.out.println(v1.size());
		AlgebraVector v2 = v1.applyReal(t -> -t*2.0) ;
		System.out.println(v2);
		System.out.println(v2[0]);
		System.out.println(v2.toList());
		AlgebraVector v3 = v2.apply(t -> -j.getBuilder() * t) ;
		System.out.println(v3);
		System.out.println(v3[1]);
	}

	public static void main(String[] args) {
		test1() ;
	}

}
