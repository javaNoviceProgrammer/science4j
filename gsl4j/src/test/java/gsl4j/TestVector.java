package gsl4j;

import static org.gsl4j.complex.Complex.j;

import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.matrix.AlgebraVector;
import org.gsl4j.matrix.RealVector;
import org.gsl4j.util.MathUtils;


public class TestVector {

	public static void test1() {
		Complex.debug = true ;
		ComplexBuilder.debug = true ;
		AlgebraVector v1 = new RealVector(MathUtils.linspace(-10.0, 10.0, 100)) ;
		System.out.println(v1);
		System.out.println(v1.getClass());
		System.out.println(v1.size());
		AlgebraVector v2 = v1.applyReal(t -> -t*2.0) ;
		System.out.println(v2);
		System.out.println(v2[0]);
		System.out.println(v2.toList());
		System.out.println(v2.getClass());
		Complex z = -(2+j) * j ;
		AlgebraVector v3 = v2.apply(t ->  z.getBuilder() * t) ;
		System.out.println(v3);
		System.out.println(v3.getClass());
//		System.out.println(v3[1]);
	}

	public static void main(String[] args) {
		test1() ;
	}

}
