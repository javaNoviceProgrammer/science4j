package gsl4j;

import static org.gsl4j.complex.Complex.j;

import java.util.function.Function;

import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexMath;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealBuilder;
import org.gsl4j.matrix.AlgebraVector;
import org.gsl4j.matrix.ComplexVectorBuilder;
import org.gsl4j.matrix.RealVector;
import org.gsl4j.matrix.VectorBuilder;
import org.gsl4j.util.MathUtils;
import org.gsl4j.util.Timer;


public class TestVector {

	public static void test1() {
//		Complex.debug = true ;
//		Real.debug = true ;
//		RealBuilder.debug = true ;
//		ComplexBuilder.debug = true ;

		Timer timer = new Timer() ;
		timer.start();

		AlgebraVector v1 = new RealVector(MathUtils.linspace(-10.0, 10.0, 5_000_000)) ;
//		System.out.println(v1);
//		System.out.println(v1.getClass());
//		System.out.println(v1.size());
		AlgebraVector v2 = v1.applyReal(t -> -t*2.0) ;
//		System.out.println(v2);
//		System.out.println(v2[0]);
//		System.out.println(v2.toList());
//		System.out.println(v2.getClass());
//		ComplexNumber z = -(2+j) * j ;
//		System.out.println(z);
//		System.out.println(-(2.0+j.cachedBuilder()) * j);

		ComplexBuilder z = -(2.0+j.cachedBuilder()) ;
		z.set(z*j).set(z*(j+1.0)).set(z*j);
		ComplexNumber w = z.toComplex() ;

//		System.out.println(z);
//		System.out.println(-(2.0+j) * j * (j+1.0) * j);

		AlgebraVector v3 = v2.apply(t -> w.cachedBuilder() *t) ;

//		System.out.println(w);

//		AlgebraVector v3 = v2.apply(t -> -(2.0+j.cachedBuilder()) * j * (j+1.0) * j *t) ;
//		AlgebraVector v3 = v2.apply(t -> -(2.0+j) * j * (j+1.0) * j *t) ;


		timer.stop();
		timer.show();

//		System.out.println(v3);
//		System.out.println(v3);
//		System.out.println(v3.getClass());
//		System.out.println(v3[1]);
//		VectorBuilder vb = new ComplexVectorBuilder(v3) ;
//		System.out.println(vb[0]);
	}

	public static void test2() {

	}

	public static void main(String[] args) {
		test1() ;
//		test2() ;
	}

}
