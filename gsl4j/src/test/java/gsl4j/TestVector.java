package gsl4j;

import static org.gsl4j.complex.Complex.j;

import java.util.function.Function;

import org.gsl4j.complex.Complex;
import org.gsl4j.complex.ComplexBuilder;
import org.gsl4j.complex.ComplexMath;
import org.gsl4j.complex.ComplexNumber;
import org.gsl4j.complex.Real;
import org.gsl4j.complex.RealBuilder;
import org.gsl4j.util.MathUtils;
import org.gsl4j.util.Timer;
import org.gsl4j.vector.AlgebraVector;
import org.gsl4j.vector.ComplexVector;
import org.gsl4j.vector.ComplexVectorBuilder;
import org.gsl4j.vector.RealAlgebraVector;
import org.gsl4j.vector.RealVector;
import org.gsl4j.vector.RealVectorBuilder;
import org.gsl4j.vector.VectorBuilder;


public class TestVector {

	static {
		Complex.debug = true ;
		Real.debug = true ;
		RealBuilder.debug = true ;
		ComplexBuilder.debug = true ;

		RealVector.debug = true ;
		RealVectorBuilder.debug = true ;
		ComplexVector.debug = true ;
		ComplexVectorBuilder.debug = true ;
	}

	public static void test1() {


		Timer timer = new Timer() ;
		timer.start();

		AlgebraVector v1 = new RealVector(MathUtils.linspace(-10.0, 10.0, 50)) ;
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
//		RealVector v1 = new RealVector(1.1, 2.2, 3.5) ;
		RealVector v1 = new double[] {1.1, 2.2, 3.5} ;
		System.out.println(v1);
		v1.re()[0] = -5.0 ;
		System.out.println(v1);
		VectorBuilder v2 = v1.getBuilder() ;
		System.out.println(v2.getClass());
		v2.set(0, -5.0);
		System.out.println(v2);
		AlgebraVector v3 = v1 - j ;
		System.out.println(v3);
		System.out.println(v3.getClass());

	}

	public static void test3() {
		RealVector v1 = new RealVector(MathUtils.linspace(-10.0, 10.0, 5)) ;
		System.out.println(v1);
		System.out.println(v1.getClass());
		AlgebraVector v2 = v1 / (1.0+j) ;
		System.out.println(v2);
		System.out.println(v2.getClass());

	}

	public static void test4() {
		RealAlgebraVector v1 = new RealVector(-10.0, -5.0, 1.0, 6.0, 10.0) ;
		System.out.println(v1);
		System.out.println(v1.getClass());
		AlgebraVector v2 = v1 / 2.0 * (j+1.0) ;
		System.out.println(v2);
		System.out.println(v2.getClass());

	}

	// test real vector and real vector builder
	public static void test5() {
		RealVector v1 = new RealVector(-10.0, -5.0, 1.0, 6.0, 10.0) ;
		System.out.println(v1);
		System.out.println(v1.getClass());
		RealVectorBuilder v2 = v1.getBuilder() ;
		System.out.println(v2);
		System.out.println(v2.getClass());
		VectorBuilder v3 = (2.0*v2-1.0) * v1 + 3.5 ;
		System.out.println(v3);
		System.out.println(v3.getClass());
		v3 = v3 / 2.5 ;
		System.out.println(v3);
		System.out.println(v2==v3);
		v3 = v3.applyReal(t -> t*2.5) ;
		System.out.println(v3);
	}

	public static void test6() {
		Timer timer = new Timer() ;
		timer.start();
		RealVector v1 = MathUtils.linspace(-100.0, 100.0, 1_000_000) ;
		RealVectorBuilder v2 = v1.getBuilder() ;
		v2 = v2.applyReal(t -> 3.0*t-1.0) ;
//		v2 = 3.0*v2-1.0 ;
		timer.stop();
		System.out.println(timer);
		System.out.println(v2.atIndex(0));
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
