package gsl4j;

import java.util.Arrays;

import org.gsl4j.function.MathFunction;
import org.gsl4j.function.MultiVariateMathFunction;
import org.gsl4j.function.Vector3DCartesianMathFunction;
import org.gsl4j.function.VectorMathFunction;
import org.gsl4j.plot.style.Color;
import org.gsl4j.plot.xy.XYPlot;
import org.gsl4j.util.MathUtils;

public class TestFunction {

	public static void test1() {
		MathFunction f1 = x -> Math.sin(x*x) ;
		MathFunction f1Deriv = f1.deriv() ;
		MathFunction f1Deriv2 = f1.deriv2() ;

		// plotting
		XYPlot plt = new XYPlot("test1") ;
		double[] x = MathUtils.linspace(-3.0*Math.PI, 3.0*Math.PI, 1000) ;
		double[] y1 = f1.value(x) ;
		double[] y1deriv = f1Deriv.value(x) ;
		double[] y1deriv2 = f1Deriv2.value(x) ;

		plt.plot(x, y1).color(Color.blue).linewidth(2).label("f1(x)") ;
		plt.plot(x, y1deriv).color(Color.red).linewidth(2).label("f1deriv(x)") ;
		plt.plot(x, y1deriv2).color(Color.green).linewidth(2).label("f1deriv2(x)") ;

		plt.grid(true) ;
		plt.legend(true) ;
		plt.show();
	}

	public static void test2() {
		MultiVariateMathFunction func = xy -> {
			double x = xy[0], y = xy[1] ;
			return x*y*y ;
		}; // x*y^2

		MultiVariateMathFunction dfunc = func.deriv(0).deriv(1) ;
		System.out.println(dfunc.value(1.0, -2.0));

		MultiVariateMathFunction dfunc2 = func.deriv(1).deriv(0) ;
		System.out.println(dfunc2.value(1.0, -2.0));

	}

	public static void test3() {
		VectorMathFunction func = xyz -> {
			double x = xyz[0], y = xyz[1], z = xyz[2] ;
			return new double[] {x*y, z-x, y+z} ;
		} ;

		MultiVariateMathFunction elem = func[0]  ;
		System.out.println(elem.value(3.0, 2.0, 3.0)) ;
	}

	public static void test4() {
		// v = [x*z+y, z*x*y, -y] --> div(v) = 1 + 0 + 0 = 1
		Vector3DCartesianMathFunction vec = xyz -> {
			double x = xyz[0], y = xyz[1], z = xyz[2] ;
			return new double[] {x*z+y, z*x*y, -y} ;
		} ;

		MultiVariateMathFunction divV = vec.div() ;
		System.out.println(divV.value(1.0, 2.0, -5.0)) ;

		// curl(v) = [-1-x*y, x-0, y*z-1]
		Vector3DCartesianMathFunction curlV = vec.curl() ;
		System.out.println(Arrays.toString(curlV.value(-1, 2, -5)));

		// div(curl(v)) == 0 Always true
		MultiVariateMathFunction divCurlV = curlV.div() ;
		System.out.println(divCurlV.value(-10.1, 2.2, -5.5));

	}

	public static void test5() {
		MathFunction f1 = x -> 2.0*x ;
		MathFunction f2 = x -> x + 1.0 ;
		MathFunction f3 = f1 + f2 ; // 3x+1
		System.out.println(f3.value(2.0));
		System.out.println(f3.getClass());

		MathFunction f4 = f1 - f2 ; // x-1
		System.out.println(f4.value(2.0));
		System.out.println(f4.getClass());

		MathFunction f5 = f1 * f2 ; // 2x(x+1)
		System.out.println(f5.value(2.0));
		System.out.println(f5.getClass());

		MathFunction f6 = f1 / f2 ; // 2x/(x+1)
		System.out.println(f6.value(2.0));
		System.out.println(f6.getClass());

		MathFunction f7 = -f1 ; // -2x
		System.out.println(f7.value(2.0));
		System.out.println(f7.getClass());

		MathFunction f8 = f2 - f3 * 2.0 ;
		System.out.println(f8.value(2.0));
	}

	public static void test6() {
		// f1 = x + y
		MultiVariateMathFunction f1 = v -> {double x = v[0], y = v[1]; return x+y;} ;
		// f2 = x*y
		MultiVariateMathFunction f2 = v -> {double x = v[0], y=v[1]; return x*y;} ;
		// f3 = f1 + f2 = x + y + x*y
		MultiVariateMathFunction f3 = f1 + f2 ;
		System.out.println(f3.value(1.1, 2.2)); // 2.42 + 3.3

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
